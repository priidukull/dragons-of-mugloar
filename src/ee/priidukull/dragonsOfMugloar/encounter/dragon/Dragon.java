package dragonsOfMugloar.encounter.dragon;

import dragonsOfMugloar.encounter.Encounter;
import dragonsOfMugloar.encounter.Weather;
import dragonsOfMugloar.encounter.knight.CorrespondingDragonStrengthNotFound;
import dragonsOfMugloar.encounter.knight.KnightAttribute;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class Dragon {
    int scaleThickness;
    int clawSharpness;
    int wingStrength;
    int fireBreath;
    private List<KnightAttribute> opponentAttributes;
    private Weather weather;

    public Dragon(Encounter encounter) throws IllegalAccessException, CorrespondingDragonStrengthNotFound, NoSuchFieldException {
        this.opponentAttributes = encounter.knight().attributesInDescendingOrder();
        this.weather = encounter.weather();
        buildDragon();
    }

    public String payload() throws IOException {
        String template = "{\"dragon\": {\"scaleThickness\": %d, \"clawSharpness\": %d, " +
                "\"wingStrength\": %d, \"fireBreath\": %d}}";
        return String.format(template, this.scaleThickness, this.clawSharpness,
                this.wingStrength, this.fireBreath);
    }

    private void buildDragon() throws CorrespondingDragonStrengthNotFound, NoSuchFieldException, IllegalAccessException {
        if (this.weather == Weather.HOT) {
            buildZenDragon();
        } else if (this.weather == Weather.RAINY) {
            buildDragonToDestroyTheUmbrellaBoats();
        } else {
            buildNormalDragon();
        }
    }

    private void buildZenDragon() throws NoSuchFieldException, IllegalAccessException {
        List<String> fields = Arrays.asList("scaleThickness", "clawSharpness", "wingStrength", "fireBreath");
        for (String fieldName : fields) {
            Field field = getClass().getDeclaredField(fieldName);
            field.set(this, 5);
        }
    }

    private void buildDragonToDestroyTheUmbrellaBoats() throws NoSuchFieldException, IllegalAccessException {
        Field clawSharpnessField = getClass().getDeclaredField("clawSharpness");
        clawSharpnessField.set(this, 10);
        Field fireBreathField = getClass().getDeclaredField("fireBreath");
        fireBreathField.set(this, 0);
        Field wingStrengthField = getClass().getDeclaredField("wingStrength");
        wingStrengthField.set(this, 5);
        Field scaleThicknessField = getClass().getDeclaredField("scaleThickness");
        scaleThicknessField.set(this, 5);
    }

    private void buildNormalDragon() throws NoSuchFieldException, CorrespondingDragonStrengthNotFound, IllegalAccessException {
        decideStrength(0, 2);
        decideStrength(3, -1);
        decideStrength(2, 0);
        decideFinal(1);
    }

    private void decideStrength(int priority, int modifier) throws NoSuchFieldException, CorrespondingDragonStrengthNotFound, IllegalAccessException {
        KnightAttribute oppAttribute = this.opponentAttributes.get(priority);
        Field field = getClass().getDeclaredField(oppAttribute.matchingDragonStrength());
        int level = Math.max(oppAttribute.value() + modifier, 0);
        field.set(this, level);
    }

    private void decideFinal(int priority) throws CorrespondingDragonStrengthNotFound, NoSuchFieldException, IllegalAccessException {
        KnightAttribute attr = this.opponentAttributes.get(priority);
        int value = 20 - (this.scaleThickness + this.clawSharpness + this.wingStrength + this.fireBreath);
        Field dragonStrength = getClass().getDeclaredField(attr.matchingDragonStrength());
        dragonStrength.set(this, value);
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
