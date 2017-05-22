package dragonsOfMugloar.encounter.dragon;

import dragonsOfMugloar.encounter.knight.CorrespondingDragonStrengthNotFound;
import dragonsOfMugloar.encounter.knight.Knight;
import dragonsOfMugloar.encounter.knight.KnightAttribute;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class Dragon {
    int scaleThickness;
    int clawSharpness;
    int wingStrength;
    int fireBreath;
    private List<KnightAttribute> opponentAttributes;

    public Dragon(Knight opposingKnight) throws IllegalAccessException, CorrespondingDragonStrengthNotFound, NoSuchFieldException {
        this.opponentAttributes = opposingKnight.attributesInDescendingOrder();
        buildDragon();
    }

    private void buildDragon() throws CorrespondingDragonStrengthNotFound, NoSuchFieldException, IllegalAccessException {
        decideStrength(0, 2);
        decideStrength(3, -1);
        decideStrength(2, 0);
        decideFinal(1);
    }

    private void decideStrength(int priority, int modifier) throws NoSuchFieldException, CorrespondingDragonStrengthNotFound, IllegalAccessException {
        KnightAttribute attr = this.opponentAttributes.get(priority);
        Field dragonStrength = getClass().getDeclaredField(attr.matchingDragonStrength());
        int dragonStrengthLevel = Math.max(attr.value() + modifier, 0);
        dragonStrength.set(this, dragonStrengthLevel);
    }

    private void decideFinal(int priority) throws CorrespondingDragonStrengthNotFound, NoSuchFieldException, IllegalAccessException {
        KnightAttribute attr = this.opponentAttributes.get(priority);
        int value = 20 - (this.scaleThickness + this.clawSharpness + this.wingStrength + this.fireBreath);
        Field dragonStrength = getClass().getDeclaredField(attr.matchingDragonStrength());
        dragonStrength.set(this, value);
    }

    public String payload() throws IOException {
        String template = "{\"dragon\": {\"scaleThickness\": %d, \"clawSharpness\": %d, " +
                "\"wingStrength\": %d, \"fireBreath\": %d}}";
        return String.format(template, this.scaleThickness, this.clawSharpness,
                this.wingStrength, this.fireBreath);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dragon)) return false;

        Dragon dragon = (Dragon) o;

        if (scaleThickness != dragon.scaleThickness) return false;
        if (clawSharpness != dragon.clawSharpness) return false;
        if (wingStrength != dragon.wingStrength) return false;
        return fireBreath == dragon.fireBreath;

    }

    @Override
    public int hashCode() {
        int result = scaleThickness;
        result = 31 * result + clawSharpness;
        result = 31 * result + wingStrength;
        result = 31 * result + fireBreath;
        return result;
    }
}
