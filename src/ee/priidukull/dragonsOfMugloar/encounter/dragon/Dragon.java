package dragonsOfMugloar.encounter.dragon;

import dragonsOfMugloar.encounter.knight.CorrespondingDragonAttributeNotFound;
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
    Knight opposingKnight;

    public Dragon(Knight opposingKnight) throws IllegalAccessException, CorrespondingDragonAttributeNotFound, NoSuchFieldException {
        this.opposingKnight = opposingKnight;
        addAttributes();
    }

    private void addAttributes() throws CorrespondingDragonAttributeNotFound, NoSuchFieldException, IllegalAccessException {
        List<KnightAttribute> knightAttributes = this.opposingKnight.attributesInDescendingOrder();
        KnightAttribute primary = knightAttributes.get(0);
        Field dragonPrimary = getClass().getDeclaredField(primary.matchingDragonAttributeName());
        int dragonPrimaryValue = primary.value() + 2;
        dragonPrimary.set(this, dragonPrimaryValue);
        KnightAttribute quaternary = knightAttributes.get(3);
        Field dragonQuaternary = getClass().getDeclaredField(quaternary.matchingDragonAttributeName());
        int dragonQuaternaryValue = Math.max(quaternary.value() - 1, 0);
        dragonQuaternary.set(this, dragonQuaternaryValue);
        KnightAttribute tertiary = knightAttributes.get(2);
        Field dragonTertiary = getClass().getDeclaredField(tertiary.matchingDragonAttributeName());
        int dragonTertiaryValue = tertiary.value();
        dragonTertiary.set(this, dragonTertiaryValue);
        KnightAttribute secondary = knightAttributes.get(1);
        Field dragonSecondary = getClass().getDeclaredField(secondary.matchingDragonAttributeName());
        int dragonSecondaryValue = 20 - dragonPrimaryValue - dragonTertiaryValue - dragonQuaternaryValue;
        dragonSecondary.set(this, dragonSecondaryValue);
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
