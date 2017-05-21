package dragonsOfMugloar;

import com.fasterxml.jackson.databind.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Encounter {
    Knight knight;
    Weather weather;
    int id;

    public Encounter(EncounterDAO encounterDAO) throws IOException, UnirestException {
        this.knight = new Knight(encounterDAO);
        this.id = this.knight.encounterId;
        JsonNode weatherData = encounterDAO.weather(this.id);
        Map<String, Weather> codeAsWeather = new HashMap<>();
        codeAsWeather.put("NMR", Weather.NORMAL);
        codeAsWeather.put("SRO", Weather.WINDY);
        codeAsWeather.put("FUNDEFINEDG", Weather.FOGGY);
        codeAsWeather.put("T E", Weather.HOT);
        codeAsWeather.put("HVA", Weather.RAINY);
        String weatherCode = weatherData.get("code").asText();
        this.weather = codeAsWeather.get(weatherCode);
    }
}
