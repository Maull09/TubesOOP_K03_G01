package entity.plant;

public class PlantFactory {
    public static Plant createPlant(String type) {
        switch (type) {
            case "Chomper":
                return new Chomper();
            case "Lilypad":
                return new Lilypad();
            case "Peashooter":
                return new Peashooter();
            case "Pumkin":
                return new Pumkin();
            case "Repeater":
                return new Repeater();
            case "SnowPea":
                return new SnowPea();
            case "Squash":
                return new Squash();
            case "Sunflower":
                return new Sunflower();
            case "TangleKelp":
                return new TangleKelp();
            case "Wallnut":
                return new Wallnut();
            default:
                throw new IllegalArgumentException("Unknown plant type: " + type);
        }
    }
}