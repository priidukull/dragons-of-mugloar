package dragonsOfMugloar;

import com.fasterxml.jackson.databind.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

public class Knight {
    int attack;
    int armor;
    int agility;
    int endurance;
    String name;
    int encounterId;

    Knight(EncounterDAO encounterDAO) throws UnirestException, IOException {
        JsonNode data = encounterDAO.knight();
        JsonNode knight = data.get("knight");
        this.attack = knight.get("attack").asInt();
        this.armor = knight.get("armor").asInt();
        this.agility = knight.get("agility").asInt();
        this.endurance = knight.get("endurance").asInt();
        this.name = knight.get("name").asText();
        this.encounterId = data.get("gameId").asInt();
    }
}
