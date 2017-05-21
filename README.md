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


    interface Dragon {
        int scaleThickness;
        int clawSharpness;
        int wingStrength;
        int fireBreath;
        Dragon dragon(Knight knight) {}
    }

    interface Battle {
        Result result;
        Reason reason;
        int gameId;
        Battle(Dragon dragon, Encounter encounter) {}
        interface Result {
            boolean isWon();
        }
        interface Reason {
            String asText();
        }
    }
    
    interface Encounter {
        Knight knight;
        Weather weather;
        Encounter() {
            this.knight = Knight();
            this.weather = Weather();
        }
        interface Knight {
            int attack;
            int armor;
            int agility;
            int endurance;
            String name;
            Knight Knight() {}
        }
        interface Weather {
            String type;
            Weather(String soap) {}
        }
    }

### On Test-Driven Development
There will be two kinds of tests that drive this solution:
1) The acceptance test: dragons fighting against 100 knights with a success rate of 60% or better.
2) A unit test, where fight against a specific knight is simulated. Each unit test will initially be losing and I will improve the code until that unit test passes (and none of the other unit tests fail).

While it is generally a good practice, to avoid that unit tests make calls to external API's, I will have my unit tests do exactly that, because this project will not need to be maintained and mocking EVERYTHING would be a bore.

After each test I will run the acceptance test to measure total process.

### Development
##### Setting up the Data Access Layer
Before getting to solving the game, I needed to set up how data about encounters with Knights could be accessed. I settled on creating a DAO, which returns JsonNode representations of both Knight and Weather. Return values returned by the DAO have the same return type and can be mocked when writing the unit tests.

##### First test - newEncounterTest()
The first test I wrote was for constructing an Encounter.

##### Second test - forecastWeatherTest()
newEncounterTest() ensures that Weather is forecasted in the correct manner when it is normal Weather. However, to ensure that the DAO functions correctly, new tests need to be added to ensure that the Weather will be determined in the correct manner in case of the four other possible weather types. 

As the second test I created forecastWeatherTest(). To run that test with all possible weather types, I modified MockEncounterDao to give out data that is different depending on how many times the DAO object has been accessed. 

##### Third test 
Next I extend newEncounterTest() to also resolve the encounter. Thus new assertions are added to the test.

After that I create newOutcomeTest() to test for generating an Outcome where the Dragon is defeated.

##### Nth test
I will randomly choose a knight who comes with normal weather, make a test case of it and solve the test case.

