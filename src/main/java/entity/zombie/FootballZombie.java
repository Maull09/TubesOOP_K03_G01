package src.main.java.entity.zombie;

import src.main.java.entity.plant.Plant;

public class FootballZombie extends Zombie{
    
    public FootballZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Football Zombie", 350, 100, 2, false);
    }

    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - this.getAttackDamage());
    }

}
