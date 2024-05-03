package src.main.java.entity.zombie;

import src.main.java.entity.plant.Plant;

public class BucketHeadZombie extends Zombie{
    private boolean hasBucket;

    public BucketHeadZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Bucket Head Zombie", 300, 100, 1, false);
    }
    
    public boolean getHasBucket() {
        return hasBucket;
    }

    public void setHasBucket(boolean hasBucket){
        this.hasBucket = hasBucket;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}
