package dragonsOfMugloar;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class EncounterData {
    JsonNode knight() throws UnirestException {
        return Unirest.get("http://www.dragonsofmugloar.com/api/game")
                .header("accept", "application/json")
                .asJson().getBody();
    }
}
