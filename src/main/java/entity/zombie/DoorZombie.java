package src.main.java.entity.zombie;

import src.main.java.entity.plant.Plant;

public class DoorZombie extends Zombie{

    public DoorZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Door Zombie", 300, 100, 1, false);
    }

    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - this.getAttackDamage());
    }
}