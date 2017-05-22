package dragonsOfMugloar.encounter;

import com.fasterxml.jackson.databind.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import dragonsOfMugloar.dao.EncounterDAO;

import java.io.IOException;
import java.lang.reflect.Field;
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

    Knight(EncounterDAO encounterDAO) throws UnirestException, IOException, NoSuchFieldException, IllegalAccessException {
        JsonNode data = encounterDAO.knight();
        System.out.println("Encounter: " + data);
        JsonNode knight = data.get("knight");
        this.name = knight.get("name").asText();
        this.encounterId = data.get("gameId").asInt();
        addAttributes(knight);
    }

    void addAttributes(JsonNode knight) throws NoSuchFieldException, IllegalAccessException {
        String[] attributes = {"attack", "armor", "agility", "endurance"};
        for (String fieldName : attributes) {
            Field field = getClass().getDeclaredField(fieldName);
            int value = knight.get(fieldName).asInt();
            field.set(this, new Attribute(fieldName, value));
        }
        rankAttributes();
    }

    private void rankAttributes() {
        List<Attribute> attrs = Arrays.asList(this.attack, this.armor, this.agility, this.endurance);
        Comparator<Attribute> comparator = Attribute::compareTo;
        attrs.sort(comparator);
        Attribute primary = attrs.get(0);
        primary.markAsPrimary();
    }
}
