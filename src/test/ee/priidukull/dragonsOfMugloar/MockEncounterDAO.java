package dragonsOfMugloar;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

public class MockEncounterDAO extends EncounterDAO {
    @Override
    JsonNode knight() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree("{\"gameId\":1548178,\"knight\":{\"armor\":1,\"attack\":4,\"name\":\"Sir. Wesley Fitzgerald of Quebec\",\"agility\":7,\"endurance\":8}}");
    }
    @Override
    JsonNode weather(int gameId) throws IOException {
        XmlMapper mapper = new XmlMapper();
        return mapper.readTree("<?xml version=\"1.0\" encoding=\"UTF-8\"?><report><time/><coords><x>3916.234</x><y>169.914</y><z>6.33</z></coords><code>NMR</code><message>Another day of everyday normal regular weather, business as usual, unless it’s going to be like the time of the Great Paprika Mayonnaise Incident of 2014, that was some pretty nasty stuff.</message><varX-Rating>8</varX-Rating></report>");
    }
}