package dragonsOfMugloar.encounter.knight;

public class KnightAttribute implements Comparable<KnightAttribute> {
    private String name;
    private int value;
    Rank rank;

    KnightAttribute(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    public boolean isPrimaryAttribute() {
        return rank == Rank.PRIMARY;
    }

    public boolean isSecondaryAttribute() {
        return rank == Rank.SECONDARY;
    }

    public String matchingDragonAttributeName() throws CorrespondingDragonAttributeNotFound {
        if (this.name.equals("attack")) {
            return "scaleThickness";
        } else if (this.name.equals("armor")) {
            return "clawSharpness";
        } else if (this.name.equals("agility")) {
            return "wingStrength";
        } else if (this.name.equals("endurance")) {
            return "fireBreath";
        } else {
            String message = "Could not determine the corresponding dragon attribute";
            throw new CorrespondingDragonAttributeNotFound(message);
        }
    }

    public void giveRank(int i) throws CouldNotRank {
        if (i == 0) {
            this.rank = Rank.PRIMARY;
        } else if (i == 1) {
            this.rank = Rank.SECONDARY;
        } else if (i == 2) {
            this.rank = Rank.TERTIARY;
        } else if (i == 3) {
            this.rank = Rank.QUATERNARY;
        } else {
            throw new CouldNotRank("There is no ranking available for the attribute with the index " + i);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KnightAttribute)) return false;

        KnightAttribute attribute = (KnightAttribute) o;

        if (value != attribute.value) return false;
        if (!name.equals(attribute.name)) return false;
        return rank == attribute.rank;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + value;
        result = 31 * result + rank.hashCode();
        return result;
    }

    @Override
    public int compareTo(KnightAttribute o) {
        return o.value > this.value ? 1 : o.value == this.value ? 0 : -1;
    }

    private enum Rank {
        PRIMARY,
        SECONDARY,
        TERTIARY,
        QUATERNARY;
    }
}
