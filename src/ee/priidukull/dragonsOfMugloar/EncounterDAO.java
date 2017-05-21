package dragonsOfMugloar;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;


public class EncounterDAO {
    JsonNode knight() throws UnirestException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(
                Unirest.get("http://www.dragonsofmugloar.com/api/game")
                .header("accept", "application/json")
                .asString().getBody()
        );
    }
    JsonNode weather(int gameId) throws UnirestException, IOException {
        XmlMapper mapper = new XmlMapper();
        return mapper.readTree(
                Unirest.get("http://www.dragonsofmugloar.com/weather/api/report/" + gameId)
                .header("accept", "application/json")
                .asString().getBody()
        );
    }
}
