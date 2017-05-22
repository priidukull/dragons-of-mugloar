package dragonsOfMugloar.encounter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import dragonsOfMugloar.dao.MockEncounterDAO;
import dragonsOfMugloar.encounter.outcome.Result;
import dragonsOfMugloar.encounter.outcome.UnexpectedResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class EncounterTest {
    ObjectNode attributes;
    Knight knight;

    @Before
    public void setUp() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        this.attributes = mapper.createObjectNode();
        this.knight = new Knight();
    }

    @Test
    public void newEncounterTest() throws Exception {
        Encounter encounter = new Encounter(new MockEncounterDAO());
        Attribute expectedEndurance = new Attribute("endurance", 8);
        expectedEndurance.markAsPrimary();
        assertEquals(new Attribute("armor", 1), encounter.knight.armor);
        assertEquals(new Attribute("attack", 4), encounter.knight.attack);
        assertEquals(new Attribute("agility", 7), encounter.knight.agility);
        assertEquals(expectedEndurance, encounter.knight.endurance);
        assertEquals("Sir. Wesley Fitzgerald of Quebec", encounter.knight.name);
        assertEquals(1548178, encounter.id);
        Assert.assertEquals(Weather.NORMAL, encounter.weather);
        Assert.assertEquals(Result.VICTORY, encounter.outcome.result);
        Assert.assertEquals("Knight was useless in the fog.", encounter.outcome.reason.asText());
    }

    @Test
    public void forecastWeatherTest() throws Exception {
        MockEncounterDAO mockDao = new MockEncounterDAO();
        Encounter encounter1 = new Encounter(mockDao);
        Encounter encounter2 = new Encounter(mockDao);
        Encounter encounter3 = new Encounter(mockDao);
        Encounter encounter4 = new Encounter(mockDao);
        Encounter encounter5 = new Encounter(mockDao);
        Assert.assertEquals(Weather.NORMAL, encounter1.weather);
        Assert.assertEquals(Weather.WINDY, encounter2.weather);
        Assert.assertEquals(Weather.FOGGY, encounter3.weather);
        Assert.assertEquals(Weather.HOT, encounter4.weather);
        Assert.assertEquals(Weather.RAINY, encounter5.weather);
    }

    @Test
    public void winEncounterTest() throws Exception {
        attributes.put("attack", 7);
        attributes.put("armor", 2);
        attributes.put("agility", 6);
        attributes.put("endurance", 5);
        Encounter encounter = encounter(attributes, 8396126);
        assertEquals(Result.VICTORY, encounter.outcome.result);
    }

    @Test
    public void winEncounterTwoTest() throws Exception {
        attributes.put("attack", 0);
        attributes.put("armor", 7);
        attributes.put("agility", 5);
        attributes.put("endurance", 8);
        Encounter encounter = encounter(attributes, 7214513);
        assertEquals(Result.VICTORY, encounter.outcome.result);
    }

    @Test
    public void winEncounterThreeTest() throws Exception {
        attributes.put("attack", 0);
        attributes.put("armor", 5);
        attributes.put("agility", 8);
        attributes.put("endurance", 7);
        Encounter encounter = encounter(attributes, 8252096);
        assertEquals(Result.VICTORY, encounter.outcome.result);
    }

    private Encounter encounter(ObjectNode attributes, int encounterId) throws NoSuchFieldException, IllegalAccessException, UnexpectedResult, UnirestException, IOException {
        knight.addAttributes(attributes);
        knight.encounterId = encounterId;
        Encounter encounter = new Encounter(knight, Weather.NORMAL);
        System.out.println(encounter.outcome.reason.asText());
        System.out.println(encounter.dragon.payload());
        return encounter;
    }
}
