package dragonsOfMugloar.encounter.dragon;

import dragonsOfMugloar.encounter.Encounter;
import dragonsOfMugloar.encounter.Weather;
import dragonsOfMugloar.encounter.knight.KnightAttribute;
import dragonsOfMugloar.encounter.knight.MatchingDragonStrengthNotFound;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class Dragon {
    private int scaleThickness;
    private int clawSharpness;
    private int wingStrength;
    private int fireBreath;
    private List<KnightAttribute> opponentAttributes;
    private Weather weather;

    public Dragon(Encounter encounter) throws IllegalAccessException, MatchingDragonStrengthNotFound, NoSuchFieldException {
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

    private void assignValueToField(String fieldName, int newValue) throws NoSuchFieldException, IllegalAccessException {
        Field field = getClass().getDeclaredField(fieldName);
        field.set(this, newValue);
    }

    private void buildZenDragon() throws NoSuchFieldException, IllegalAccessException {
        List<String> fields = Arrays.asList("scaleThickness", "clawSharpness", "wingStrength", "fireBreath");
        for (String fieldName : fields) {
            assignValueToField(fieldName, 5);
        }
    }

    private void buildDragonToDestroyTheUmbrellaBoats() throws NoSuchFieldException, IllegalAccessException {
        assignValueToField("clawSharpness", 10);
        assignValueToField("fireBreath", 0);
        assignValueToField("wingStrength", 5);
        assignValueToField("scaleThickness", 5);
    }

    private void decideStrength(int priority, int modifier) throws NoSuchFieldException, MatchingDragonStrengthNotFound, IllegalAccessException {
        KnightAttribute attributeToCounter = this.opponentAttributes.get(priority);
        int newValue = Math.max(attributeToCounter.value() + modifier, 0);
        assignValueToField(attributeToCounter.matchingDragonStrength(), newValue);
    }

    private void decideFinal(int priority) throws MatchingDragonStrengthNotFound, NoSuchFieldException, IllegalAccessException {
        KnightAttribute attr = this.opponentAttributes.get(priority);
        int newValue = 20 - (this.scaleThickness + this.clawSharpness + this.wingStrength + this.fireBreath);
        assignValueToField(attr.matchingDragonStrength(), newValue);
    }

    private void buildNormalDragon() throws NoSuchFieldException, MatchingDragonStrengthNotFound, IllegalAccessException {
        decideStrength(0, 2);
        decideStrength(3, -1);
        decideStrength(2, 0);
        decideFinal(1);
    }

    private void buildDragon() throws MatchingDragonStrengthNotFound, NoSuchFieldException, IllegalAccessException {
        if (this.weather == Weather.HOT) {
            buildZenDragon();
        } else if (this.weather == Weather.RAINY) {
            buildDragonToDestroyTheUmbrellaBoats();
        } else {
            buildNormalDragon();
        }
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
