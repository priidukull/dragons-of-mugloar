package dragonsOfMugloar.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

public class MockEncounterDAO extends EncounterDAO {
    private int timesAccessed = 0;
    @Override
    public JsonNode knight() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode knight;
        if (timesAccessed == 0) {
            knight = mapper.readTree("{\"gameId\":1548178,\"knight\":{\"armor\":1,\"attack\":4,\"name\":\"Sir. Wesley Fitzgerald of Quebec\",\"agility\":7,\"endurance\":8}}");
        } else {
            knight = mapper.readTree("{\"gameId\":" + timesAccessed + ",\"knight\":{\"armor\":1,\"attack\":4,\"name\":\"Sir. Wesley Fitzgerald of Quebec\",\"agility\":7,\"endurance\":8}}");
        }
        timesAccessed++;
        return knight;
    }
    @Override
    public JsonNode weather(int gameId) throws IOException {
        XmlMapper mapper = new XmlMapper();
        if (gameId == 1) {
            return mapper.readTree("<?xml version=\"1.0\" encoding=\"UTF-8\"?><report><time/><coords><x>3916.234</x><y>169.914</y><z>6.33</z></coords><code>SRO</code><message>Looks like it’s going to be one of those days when the kingdom feels a bittersweet longing for the Above Average Impressive Frog Rain of 2012. The clouds are gathering and swirling themselves up for a storm. It would probably wise to not go out as the winds are gathering speed, you would want to land as close as possible to where your house ends up as the sky clears up again.</message><varX-Rating>2.86</varX-Rating></report>");
        } else if (gameId == 2) {
            return mapper.readTree("<?xml version=\"1.0\" encoding=\"UTF-8\"?><report><time/><coords><x>3916.234</x><y>169.914</y><z>6.33</z></coords><code>FUNDEFINEDG</code><message>It’s very difficult to foresee what the day will bring, it’s mostly because of the fog that is going to be dominant all over the Kingdom, seriously affecting overall visibility and usefulness of seeing eye dogs. All business and governing activities have been postponed due to transparency issues and also due to some pretty serious corruption, which is unrelated to the weather conditions.</message><varX-Rating>1.71</varX-Rating></report>");
        } else if (gameId == 3) {
            return mapper.readTree("<?xml version=\"1.0\" encoding=\"UTF-8\"?><report><time/><coords><x>3916.234</x><y>169.914</y><z>6.33</z></coords><code>T E</code><message>We are going to be facing one of the rare days when the two citizens who have installed air conditioning can say that they told everyone so. Forecast is that The Long Dry should still be shorter than the Kingdom’s longest water hoses, so everything should be under control, at least mathematically.</message><varX-Rating>6.86</varX-Rating></report>");
        } else if (gameId == 4) {
            return mapper.readTree("<?xml version=\"1.0\" encoding=\"UTF-8\"?><report><time/><coords><x>3916.234</x><y>169.914</y><z>6.33</z></coords><code>HVA</code><message> If your sign is Aquarius, you’re in for a treat today, at first the water is going to fall on the ground and then it will also stick around for a while. Leaving the house raftless will result in mandatory swim class. The King announces that 10 most impressive swimmers will be representing the Kingdom in the International Olympic Games. Practice is every Tuesday and Thursday or whenever there’s a flood</message><varX-Rating>12</varX-Rating></report>");
        } else {
            return mapper.readTree("<?xml version=\"1.0\" encoding=\"UTF-8\"?><report><time/><coords><x>3916.234</x><y>169.914</y><z>6.33</z></coords><code>NMR</code><message>Another day of everyday normal regular weather, business as usual, unless it’s going to be like the time of the Great Paprika Mayonnaise Incident of 2014, that was some pretty nasty stuff.</message><varX-Rating>8</varX-Rating></report>");
        }
    }
    @Override
    public JsonNode outcome(int gameId, String payload) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree("{\"status\":\"Victory\",\"message\":\"Knight was useless in the fog.\"}");
    }
}