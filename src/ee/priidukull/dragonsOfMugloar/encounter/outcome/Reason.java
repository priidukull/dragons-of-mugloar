package dragonsOfMugloar.encounter.outcome;

public class Reason {
    private String description;

    Reason(String description) {
        this.description = description;
    }

    public String asText() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reason)) return false;

        Reason reason = (Reason) o;

        return description.equals(reason.description);

    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
