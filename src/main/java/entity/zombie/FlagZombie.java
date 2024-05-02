package src.main.java.entity.zombie;

import src.main.java.entity.plant.Plant;

public class FlagZombie extends Zombie{

    public FlagZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic, boolean has_flag) {
        super("Flag Zombie", 125, 100, 1, false);
    }

    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - this.getAttackDamage());
    }

}
