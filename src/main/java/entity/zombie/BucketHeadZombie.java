package entity.zombie;

import entity.plant.Plant;

public class BucketHeadZombie extends Zombie{
    private boolean hasBucket;

    public BucketHeadZombie(int row, int col) {
        super("BucketHeadZombie", 300, 100, 1, false, row, col, true);
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
