# Solving Dragons of Mugloar
### Initial thoughts
##### API research
API research has revealed that:
1) For research purposes it is possible to fight the same knight for an infinite amout of times. Simply use the knight's ID for that purpose
2) Weather is dependent on the ID of the knight
3) About 9 times out of 10, we have normal weather
4) In order to win a battle, the dragon needs to successfully counter the characteristics of a dragon
5) The API response about the battle result reveals, which characteristic of the knight was not countered successfully

##### Design
During initial analysis of the problem I came up with the following interfaces:


    interface Game {
        Knight knight;
        Weather weather;
        int id;
        Game() {}
        interface Knight {
            int attack;
            int armor;
            int agility;
            int endurance;
            String name;
        }
        interface Weather {
            String type;
            Weather(String soap) {}
        }
    }

    interface Dragon {
        int scaleThickness;
        int clawSharpness;
        int wingStrength;
        int fireBreath;
        Dragon dragon(Knight knight) {}
    }

    interface Solution {
        Result result;
        Reason reason;
        int gameId;
        Solution(Dragon dragon, Game game) {}
        interface Result {
            boolean isWon();
        }
        interface Reason {
            String asText();
        }
    }

### On Test-Driven Development
There will be two kinds of tests that drive this solution:
1) The acceptance test: dragons fighting against 100 knights with a success rate of 60% or better.
2) A unit test, where fight against a specific knight is simulated. Each unit test will initially be losing and I will improve the code until that unit test passes (and none of the other unit tests fail).

While it is generally a good practice, to avoid that unit tests make calls to external API's, I will have my unit tests do exactly that, because this project will not need to be maintained and mocking EVERYTHING would be a bore.

After each test I will run the acceptance test to measure total process.

### Development
##### First test
I will randomly choose a knight who comes with normal weather, make a test case of it and solve the test case.