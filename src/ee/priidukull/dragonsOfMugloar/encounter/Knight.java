package dragonsOfMugloar.encounter;

import com.fasterxml.jackson.databind.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import dragonsOfMugloar.dao.EncounterDAO;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Knight {
    Attribute attack;
    Attribute armor;
    Attribute agility;
    Attribute endurance;
    String name;
    int encounterId;

    Knight() {}

    Knight(EncounterDAO encounterDAO) throws UnirestException, IOException {
        JsonNode data = encounterDAO.knight();
        System.out.println("Encounter: " + data);
        JsonNode knight = data.get("knight");
        this.attack = new Attribute("attack", knight.get("attack").asInt());
        this.armor = new Attribute("armor", knight.get("armor").asInt());
        this.agility = new Attribute("agility", knight.get("agility").asInt());
        this.endurance = new Attribute("endurance", knight.get("endurance").asInt());
        this.name = knight.get("name").asText();
        this.encounterId = data.get("gameId").asInt();
        rankAttributes();
    }

    void rankAttributes() {
        List<Attribute> attrs = Arrays.asList(this.attack, this.armor, this.agility, this.endurance);
        Comparator<Attribute> comparator = Attribute::compareTo;
        attrs.sort(comparator);
        Attribute primary = attrs.get(0);
        primary.markAsPrimary();
    }
}
