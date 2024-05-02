package src.main.java.entity.zombie;

import src.main.java.entity.plant.Plant;

public class BucketHeadZombie extends Zombie{
    public BucketHeadZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Bucket Head Zombie", 300, 100, 1, false);
    }
    
    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - this.getAttackDamage());
    }
}
