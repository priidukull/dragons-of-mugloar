package dragonsOfMugloar.encounter;

public class Attribute implements Comparable<Attribute> {
    private String name;
    private int value;
    boolean isPrimary = false;

    Attribute(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int value() {
        return this.value;
    }


    public void markAsPrimary() {
        this.isPrimary = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attribute)) return false;

        Attribute attribute = (Attribute) o;

        if (value != attribute.value) return false;
        if (isPrimary != attribute.isPrimary) return false;
        return name.equals(attribute.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + value;
        result = 31 * result + (isPrimary ? 1 : 0);
        return result;
    }

    @Override
    public int compareTo(Attribute o) {
        return o.value > this.value ? 1 : o.value == this.value ? 0 : -1;
    }
}
