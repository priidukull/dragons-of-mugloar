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
        if (this.opposingKnight.attack.isPrimaryAttribute()) {
            this.scaleThickness = this.opposingKnight.attack.value() + 2;
        } else {
            this.scaleThickness = this.opposingKnight.attack.value();
        }
        if (this.opposingKnight.armor.isPrimaryAttribute()) {
            this.clawSharpness = this.opposingKnight.armor.value() + 2;
        } else {
            this.clawSharpness = this.opposingKnight.armor.value() - 1;
        }
        if (this.opposingKnight.agility.isPrimaryAttribute()) {
            this.wingStrength = this.opposingKnight.agility.value() + 2;
        } else {
            this.wingStrength = this.opposingKnight.agility.value() -1;
        }
        this.fireBreath = 20 - this.scaleThickness - this.clawSharpness - this.wingStrength;
    }


    String payload() throws IOException {
        String template = "{\"dragon\": {\"scaleThickness\": %d, \"clawSharpness\": %d, " +
                "\"wingStrength\": %d, \"fireBreath\": %d}}";
        return String.format(template, this.scaleThickness, this.clawSharpness,
                this.wingStrength, this.fireBreath);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dragon)) return false;

        Dragon dragon = (Dragon) o;

        if (scaleThickness != dragon.scaleThickness) return false;
        if (clawSharpness != dragon.clawSharpness) return false;
        if (wingStrength != dragon.wingStrength) return false;
        return fireBreath == dragon.fireBreath;

    }

    @Override
    public int hashCode() {
        int result = scaleThickness;
        result = 31 * result + clawSharpness;
        result = 31 * result + wingStrength;
        result = 31 * result + fireBreath;
        return result;
    }
}
