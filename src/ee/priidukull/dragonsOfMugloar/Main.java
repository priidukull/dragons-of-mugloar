package dragonsOfMugloar;

import com.mashape.unirest.http.exceptions.UnirestException;
import dragonsOfMugloar.dao.EncounterDAO;
import dragonsOfMugloar.encounter.Encounter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws UnirestException, IOException {
        Encounter encounter = new Encounter(new EncounterDAO());
    }
}
