package dragonsOfMugloar.encounter;

class Attribute implements Comparable<Attribute> {
    private String name;
    private int value;
    Rank rank;

    Attribute(String name, int value) {
        this.name = name;
        this.value = value;
    }

    int value() {
        return this.value;
    }

    boolean isPrimaryAttribute() {
        return rank == Rank.PRIMARY;
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
        if (!(o instanceof Attribute)) return false;

        Attribute attribute = (Attribute) o;

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
    public int compareTo(Attribute o) {
        return o.value > this.value ? 1 : o.value == this.value ? 0 : -1;
    }

    private enum Rank {
        PRIMARY,
        SECONDARY,
        TERTIARY,
        QUATERNARY;
    }
}
