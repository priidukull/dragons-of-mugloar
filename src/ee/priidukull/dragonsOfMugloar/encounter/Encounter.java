package dragonsOfMugloar.encounter;

import com.fasterxml.jackson.databind.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import dragonsOfMugloar.dao.EncounterDAO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Encounter {
    EncounterDAO dao;
    Knight knight;
    Weather weather;
    int id;

    public Encounter(EncounterDAO encounterDAO) throws IOException, UnirestException {
        this.dao = encounterDAO;
        this.knight = new Knight(encounterDAO);
        this.id = this.knight.encounterId;
        forecastWeather();
    }

    private void forecastWeather() throws IOException, UnirestException {
        JsonNode data = dao.weather(this.id);
        Map<String, Weather> codeAsWeather = new HashMap<>();
        codeAsWeather.put("NMR", Weather.NORMAL);
        codeAsWeather.put("SRO", Weather.WINDY);
        codeAsWeather.put("FUNDEFINEDG", Weather.FOGGY);
        codeAsWeather.put("T E", Weather.HOT);
        codeAsWeather.put("HVA", Weather.RAINY);
        String weatherCode = data.get("code").asText();
        this.weather = codeAsWeather.get(weatherCode);
    }
}
