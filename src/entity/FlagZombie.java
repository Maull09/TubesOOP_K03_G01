package src.entity;

public class FlagZombie extends Zombie{

    public FlagZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic, boolean has_flag) {
        super("Flag Zombie", 125, 100, 1, false);
    }
}
