package dragonsOfMugloar.encounter;

public enum Weather {
    NORMAL("It is a normal weather"),
    WINDY("It is a windy weather"),
    FOGGY("It is a foggy weather"),
    HOT("It is a hot weather"),
    RAINY("It is a rainy weather");

    private String text;

    Weather(String text) {
        this.text = text;
    }

    String asText() {
        return this.text;
    }

}
