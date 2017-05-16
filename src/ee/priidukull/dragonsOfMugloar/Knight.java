package dragonsOfMugloar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

public class Knight {
    int attack;
    int armor;
    int agility;
    int endurance;
    String name;
    int gameId;
    Knight(EncounterData encounterData) throws UnirestException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode data = encounterData.knight();
        com.fasterxml.jackson.databind.JsonNode tree = mapper.readTree(String.valueOf(data));
        com.fasterxml.jackson.databind.JsonNode knight = tree.get("knight");
        this.attack = knight.get("attack").asInt();
        this.armor = knight.get("armor").asInt();
        this.agility = knight.get("agility").asInt();
        this.endurance = knight.get("endurance").asInt();
        this.name = knight.get("name").asText();
        this.gameId = tree.get("gameId").asInt();
    }
}
