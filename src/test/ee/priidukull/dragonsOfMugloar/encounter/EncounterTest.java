package dragonsOfMugloar.encounter;

import dragonsOfMugloar.dao.MockEncounterDAO;
import dragonsOfMugloar.encounter.outcome.Result;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EncounterTest {
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
        Knight knight = new Knight();
        knight.encounterId = 8396126;
        knight.attack = new Attribute("attack", 7);
        knight.armor = new Attribute("armor", 2);
        knight.agility = new Attribute("agility", 6);
        knight.endurance = new Attribute("endurance", 5);
        knight.rankAttributes();
        Encounter encounter = new Encounter(knight, Weather.NORMAL);
        System.out.println(encounter.outcome.reason.asText());
        System.out.println(encounter.dragon.payload());
        assertEquals(Result.VICTORY, encounter.outcome.result);
    }

    @Test
    public void winEncounterTwoTest() throws Exception {
        Knight knight = new Knight();
        knight.encounterId = 7214513;
        knight.attack = new Attribute("attack", 0);
        knight.armor = new Attribute("armor", 7);
        knight.agility = new Attribute("agility", 5);
        knight.endurance = new Attribute("attack", 8);
        knight.rankAttributes();
        Encounter encounter = new Encounter(knight, Weather.NORMAL);
        System.out.println(encounter.outcome.reason.asText());
        System.out.println(encounter.dragon.payload());
        assertEquals(Result.VICTORY, encounter.outcome.result);
    }
}
