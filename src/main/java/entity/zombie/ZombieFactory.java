package entity.zombie;

public class ZombieFactory {
    public static Zombie createZombie(String type) {
        switch (type) {
            case "Normal":
                return new NormalZombie();
            case "ConeHead":
                return new ConeHeadZombie();
            case "Pole Vaulting":
                return new PoleVaultingZombie();
            case "Bucket Head":
                return new BucketHeadZombie();
            case "Ducky Tube":
                return new DuckyTubeZombie();
            case "Dolphin Rider":
                return new DolphinRiderZombie();
            case "Door":
                return new DoorZombie();
            case "Football":
                return new FootballZombie();
            case "Flag":
                return new FlagZombie();
            case "Newspaper":
                return new NewspaperZombie();
            default:
                throw new IllegalArgumentException("Unknown zombie type: " + type);
        }
    }
}
