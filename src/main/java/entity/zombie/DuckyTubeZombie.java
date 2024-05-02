package src.main.java.entity.zombie;

import src.main.java.entity.plant.Plant;

public class DuckyTubeZombie extends Zombie {
    public DuckyTubeZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Ducky Tube Zombie", 100, 100, 1, true);
    }

    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - this.getAttackDamage());
    }

}
