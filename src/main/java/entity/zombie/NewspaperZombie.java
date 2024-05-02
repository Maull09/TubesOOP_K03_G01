package src.main.java.entity.zombie;

import src.main.java.entity.plant.Plant;

public class NewspaperZombie extends Zombie{

    public NewspaperZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Newspaper Zombie", 150, 100, 2, false);
    }

    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - this.getAttackDamage());
    }
}
