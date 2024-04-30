package src.entity;

public class DoorZombie extends Zombie{

    public DoorZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Door Zombie", 300, 100, 1, false);
    }
}