package src.main.java.entity.zombie;

import src.main.java.entity.plant.Plant;

public class ConeHeadZombie extends Zombie{

    public ConeHeadZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Cone Head Zombie", 250, 100, 1, false);
    }

    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - this.getAttackDamage());
    }
}