package dragonsOfMugloar.encounter.outcome;

import com.fasterxml.jackson.databind.JsonNode;

public class Outcome {
    public Result result;
    public Reason reason;

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
}

