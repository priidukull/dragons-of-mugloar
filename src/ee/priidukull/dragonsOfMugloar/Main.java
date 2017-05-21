package dragonsOfMugloar;

import com.mashape.unirest.http.exceptions.UnirestException;
import dragonsOfMugloar.dao.EncounterDAO;
import dragonsOfMugloar.encounter.Encounter;
import dragonsOfMugloar.encounter.outcome.UnexpectedResult;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws UnirestException, IOException, UnexpectedResult {
        Encounter encounter = new Encounter(new EncounterDAO());
    }
}
