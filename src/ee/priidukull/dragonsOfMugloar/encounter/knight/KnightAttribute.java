package dragonsOfMugloar.encounter.knight;

public class KnightAttribute implements Comparable<KnightAttribute> {
    private String name;
    private int value;

    KnightAttribute(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    public String matchingDragonStrength() throws CorrespondingDragonStrengthNotFound {
        if (this.name.equals("attack")) {
            return "scaleThickness";
        } else if (this.name.equals("armor")) {
            return "clawSharpness";
        } else if (this.name.equals("agility")) {
            return "wingStrength";
        } else if (this.name.equals("endurance")) {
            return "fireBreath";
        } else {
            String message = "Could not determine the corresponding dragon strength";
            throw new CorrespondingDragonStrengthNotFound(message);
        }
    }

    @Override
    public int compareTo(KnightAttribute o) {
        return o.value > this.value ? 1 : o.value == this.value ? 0 : -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KnightAttribute)) return false;

        KnightAttribute that = (KnightAttribute) o;

        if (value != that.value) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + value;
        return result;
    }
}
