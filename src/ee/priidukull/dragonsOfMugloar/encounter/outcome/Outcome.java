package dragonsOfMugloar.encounter.outcome;

import com.fasterxml.jackson.databind.JsonNode;

public class Outcome {
    private Result result;
    private Reason reason;

    public Outcome(JsonNode outcome) throws UnexpectedResult {
        if (outcome.get("status").asText().equals("Victory")) {
            this.result = Result.VICTORY;
        } else if (outcome.get("status").asText().equals("Defeat")) {
            this.result = Result.DEFEAT;
        } else {
            throw new UnexpectedResult("Did not expect result " + outcome.get("status").asText());
        }
        this.reason = new Reason(outcome.get("message").asText());
    }

    public Result result() {
        return this.result;
    }

    public Reason reason() {
        return this.reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Outcome)) return false;

        Outcome outcome = (Outcome) o;

        if (result != outcome.result) return false;
        return reason.equals(outcome.reason);

    }

    @Override
    public int hashCode() {
        int result1 = result.hashCode();
        result1 = 31 * result1 + reason.hashCode();
        return result1;
    }
}

