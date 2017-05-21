package dragonsOfMugloar.encounter;

import java.io.IOException;

class Dragon {
    int scaleThickness;
    int clawSharpness;
    int wingStrength;
    int fireBreath;

    Dragon() {
        this.scaleThickness = 10;
        this.clawSharpness = 5;
        this.wingStrength = 4;
        this.fireBreath = 1;
    }

    String payload() throws IOException {
        return "{\"dragon\": {\"scaleThickness\": 9, \"clawSharpness\": 1, \"wingStrength\": 4, \"fireBreath\": 6}}";
    }
}
