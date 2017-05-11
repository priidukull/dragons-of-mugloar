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

    interface Knight {
        int attack;
        int armor;
        int agility;
        int endurance;
        String name;
        Weather weather;
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

    interface Outcome {
        Result result;
        String reason;
        Outcome(Response response) {}
        interface Result {
            boolean isWon();
        }
    }

    interface Repository {
        Knight get(int id);
        Knight getAny();
    }
