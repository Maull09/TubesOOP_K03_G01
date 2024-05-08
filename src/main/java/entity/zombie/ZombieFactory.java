package entity.zombie;

public class ZombieFactory {
    public static Zombie createZombie(String type) {
        switch (type) {
            case "Normal":
                return new NormalZombie("Normal Zombie", 125, 100, 1, false);
            case "ConeHead":
                return new ConeHeadZombie("Cone Head Zombie", 250, 100, 1, false);
            case "Pole Vaulting":
                return new PoleVaultingZombie("Pole Vaulting Zombie", 175, 100, 1, false);
            case "Bucket Head":
                return new BucketHeadZombie("Bucket Head Zombie", 300, 100, 1, false);
            case "Ducky Tube":
                return new DuckyTubeZombie("Ducky Tube Zombie", 100, 100, 1, true);
            case "Dolphin Rider":
                return new DolphinRiderZombie("Dolphin Rider Zombie", 175, 100, 1, true);
            case "Door":
                return new DoorZombie("Door Zombie", 300, 100, 1, false);
            case "Football":
                return new FootballZombie("Football Zombie", 350, 100, 2, false);
            case "Flag":
                return new Zombie("Flag Zombie", 125, 105, 1, false);
            case "Newspaper":
                return new NewspaperZombie("Newspaper Zombie", 150, 100, 1, false);
            default:
                throw new IllegalArgumentException("Unknown zombie type: " + type);
        }
    }
}
