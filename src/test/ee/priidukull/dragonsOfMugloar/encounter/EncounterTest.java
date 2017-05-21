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
        assertEquals(1, encounter.knight.armor);
        assertEquals(4, encounter.knight.attack);
        assertEquals(7, encounter.knight.agility);
        assertEquals(8, encounter.knight.endurance);
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
}
