package dragonsOfMugloar.encounter.outcome;

public class Reason {
    private String description;

    Reason(String description) {
        this.description = description;
    }

    public String asText() {
        return description;
    }
}
