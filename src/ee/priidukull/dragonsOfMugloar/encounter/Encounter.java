package dragonsOfMugloar.encounter;

import com.fasterxml.jackson.databind.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import dragonsOfMugloar.dao.EncounterDAO;
import dragonsOfMugloar.encounter.outcome.Outcome;
import dragonsOfMugloar.encounter.outcome.UnexpectedResult;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Encounter {
    EncounterDAO dao;
    Knight knight;
    Weather weather;
    int id;
    Outcome outcome;

    public Encounter(EncounterDAO encounterDAO) throws IOException, UnirestException, UnexpectedResult {
        this.dao = encounterDAO;
        this.knight = new Knight(encounterDAO);
        this.id = this.knight.encounterId;
        forecastWeather();
        resolve();
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

    private void resolve() throws IOException, UnirestException, UnexpectedResult {
        Dragon dragon = new Dragon();
        JsonNode data = dao.outcome(id, dragon.payload());
        this.outcome = new Outcome(data);
    }
}
