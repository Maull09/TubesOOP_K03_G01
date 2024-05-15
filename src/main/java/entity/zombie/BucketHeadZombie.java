package entity.zombie;

import entity.plant.Plant;

public class BucketHeadZombie extends Zombie{

    public BucketHeadZombie(int row, int col) {
        super("BucketHeadZombie", 300, 100, 1, false, row, col, true, true, false);
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}
