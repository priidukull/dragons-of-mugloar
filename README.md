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

##### Third test - newOutcomeTest()
Next I extend newEncounterTest() to also resolve the encounter. Thus new assertions are added to the test.

After that I create newOutcomeTest() to test for generating an Outcome where the Dragon is defeated.

##### Fourth test - winEncounterTest()
We have finally arrived at the point where I can test for Dragons having managed to win an Encounter. I will randomly choose an encounter with a normal weather and write code so that such Encounter would be victorious.

##### Acceptance test - InvastionOfKnights::main()
To see how far are we with learning to choose the right Dragons to fight Knights, I created an acceptance test in the main method of InvasionOfKnights. Currently the main method will run 10 fights and print out the outcome. Once we get to 5 wins out of 10, we will run the acceptance test with a greater number of test cases.

The first run of acceptance test resulted in 1/10 wins.

##### The fifth (unit-)test - winEncounterTwoTest()
Randomly picked a Knight and will now create a unittest where the goal is to defeat that Knight.

After solving this test, the acceptance test scored 6 wins of 10. Therefore it became necessary to run the acceptance test for more than 10 Encounters. Will try 100.

Increased the number of Encounters to 100 and the result was 64 wins out of 36.

##### The sixth (unit-)test - winEncounterThreeTest()
Randomly picked another Knight which used to defeat the Dragon which my algoritm chose, made a unit test for fighting against that Knight, made the unit test pass.

After making that test green, Dragons won 88 Encounters out of 100.

##### The seventh (unit-)test - winEncounterFourTest()
Wrote a test to deal with the case of a Dragon having anorexia. And made it pass.

After making that test green, Dragons won 85 Encounters out of 100. Appearently there was no overall improvement in the performance of dragons.

Next I tried a different way to fix the failing test, which gave a success rate of 89/100.

##### The eigth (unit-)test - winEncounterWhenItsHotTest()
Appearently Dragons can now defeat all the knights in normal weather conditions, but there are some problems in more extreme weather conditions. I will create and try to solve a test where the weather is hot.

After changing our Dragon building algorithms to build only very balanced and Zen dragons when the Weather is hot, we stopped losing dragons with hot Weather. 97/100 Encounters were successful. 

##### The nine (unit-)test - winEncounterWhenItsRainyTest()
We are still losing Dragons when it's rainy. Will write a test to address that weather.

Wrote the test case and solved it. Now we have come to the point where we only lose Dragons to storm about which we can do nothing.

##### Dealing with Exceptions
I have decided to deal with Exceptions in the most robust way possible. All Exceptions will be thrown and caught in the main method at the end of processing each Encounter. 

In principle, no exceptions should occur when running this software (assuming API uptime of 100%). However, if there is a freak incident which causes processing of an Encounter to fail, I would still like to see other Encounters processed as usual. 


