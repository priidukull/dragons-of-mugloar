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

    Knight(EncounterDAO encounterDAO) throws UnirestException, IOException, NoSuchFieldException, IllegalAccessException, CouldNotRank {
        JsonNode data = encounterDAO.knight();
        System.out.println("Encounter: " + data);
        JsonNode knight = data.get("knight");
        this.name = knight.get("name").asText();
        this.encounterId = data.get("gameId").asInt();
        addAttributes(knight);
    }

    void addAttributes(JsonNode knight) throws NoSuchFieldException, IllegalAccessException, CouldNotRank {
        String[] attributes = {"attack", "armor", "agility", "endurance"};
        for (String fieldName : attributes) {
            Field field = getClass().getDeclaredField(fieldName);
            int value = knight.get(fieldName).asInt();
            field.set(this, new Attribute(fieldName, value));
        }
        rankAttributes();
    }

    private void rankAttributes() throws CouldNotRank {
        List<Attribute> attrs = Arrays.asList(this.attack, this.armor, this.agility, this.endurance);
        Comparator<Attribute> comparator = Attribute::compareTo;
        attrs.sort(comparator);
        for (int i = 0; i < attrs.size(); i++) {
            Attribute attr = attrs.get(i);
            attr.giveRank(i);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Knight)) return false;

        Knight knight = (Knight) o;

        if (encounterId != knight.encounterId) return false;
        if (!attack.equals(knight.attack)) return false;
        if (!armor.equals(knight.armor)) return false;
        if (!agility.equals(knight.agility)) return false;
        if (!endurance.equals(knight.endurance)) return false;
        return name.equals(knight.name);

    }

    @Override
    public int hashCode() {
        int result = attack.hashCode();
        result = 31 * result + armor.hashCode();
        result = 31 * result + agility.hashCode();
        result = 31 * result + endurance.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + encounterId;
        return result;
    }
}
