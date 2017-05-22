package dragonsOfMugloar.encounter;

import java.io.IOException;

class Dragon {
    int scaleThickness;
    int clawSharpness;
    int wingStrength;
    int fireBreath;
    Knight opposingKnight;

    Dragon(Knight opposingKnight) {
        this.opposingKnight = opposingKnight;
        addAttributes();
    }

    private void addAttributes() {
        if (this.opposingKnight.attack.isPrimary) {
            this.scaleThickness = this.opposingKnight.attack.value() + 2;
        } else {
            this.scaleThickness = this.opposingKnight.attack.value();
        }
        if (this.opposingKnight.armor.isPrimary) {
            this.clawSharpness = this.opposingKnight.armor.value() + 2;
        } else {
            this.clawSharpness = Math.max(this.opposingKnight.armor.value() - 1, 0);
        }
        if (this.opposingKnight.agility.isPrimary) {
            this.wingStrength = this.opposingKnight.agility.value() + 2;
        } else {
            this.wingStrength = Math.max(this.opposingKnight.agility.value() -1, 0);
        }
        this.fireBreath = Math.max(20 - this.scaleThickness - this.clawSharpness - this.wingStrength, 0);
    }


    String payload() throws IOException {
        String template = "{\"dragon\": {\"scaleThickness\": %d, \"clawSharpness\": %d, " +
                "\"wingStrength\": %d, \"fireBreath\": %d}}";
        return String.format(template, this.scaleThickness, this.clawSharpness,
                this.wingStrength, this.fireBreath);
    }
}
