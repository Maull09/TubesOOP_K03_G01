package src.main.java.entity.zombie;

import src.main.java.entity.plant.Plant;

public class NormalZombie extends Zombie{

    public NormalZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Normal Zombie", 125, 100, 1, false);
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }

}
