package dragonsOfMugloar;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class EncounterTest {
    @Test
    public void newEncounterTest() throws IOException, UnirestException {
        Encounter encounter = new Encounter(new MockEncounterDAO());
        assertEquals(1, encounter.knight.armor);
        assertEquals(4, encounter.knight.attack);
        assertEquals(7, encounter.knight.agility);
        assertEquals(8, encounter.knight.endurance);
        assertEquals("Sir. Wesley Fitzgerald of Quebec", encounter.knight.name);
        assertEquals(1548178, encounter.id);
        assertEquals(Weather.NORMAL, encounter.weather);
    }
}
