package dragonsOfMugloar.encounter;

import com.mashape.unirest.http.exceptions.UnirestException;
import dragonsOfMugloar.dao.EncounterDAO;
import dragonsOfMugloar.encounter.outcome.Result;
import dragonsOfMugloar.encounter.outcome.UnexpectedResult;

import java.io.IOException;

public class InvasionOfKnights {

    public static void main(String[] args) throws UnirestException, IOException, UnexpectedResult {
        int wins = 0;
        int losses = 0;
        for (int i = 0; i < 10; i++) {
            Encounter encounter = new Encounter(new EncounterDAO());
            System.out.println(encounter.outcome.result);
            System.out.println(encounter.outcome.reason.asText());
            if (encounter.outcome.result.equals(Result.VICTORY)) {
                wins++;
            } else {
                losses++;
            }
        }
        System.out.println("Wins: " + wins + " Losses: " + losses);
    }
}