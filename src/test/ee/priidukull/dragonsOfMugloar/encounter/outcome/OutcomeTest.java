package dragonsOfMugloar.encounter.outcome;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OutcomeTest {
    @Test
    public void newOutcomeTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode data = mapper.readTree("{\"status\": \"Defeat\", \"message\": \"Dragon could not compete with knights epic attack\"}");
        Outcome outcome = new Outcome(data);
        assertEquals(Result.DEFEAT, outcome.result());
    }
}
