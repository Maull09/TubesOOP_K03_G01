package entity.zombie;

import entity.plant.Plant;

public class BucketHeadZombie extends Zombie{
    private boolean hasArmor;

    public BucketHeadZombie(int row, int col) {
        super("BucketHeadZombie", 300, 100, 1, false, row, col, true);
    }
    
    public boolean getHasArmor() {
        return hasArmor;
    }

    public void setHasArmor(boolean hasArmor){
        this.hasArmor = hasArmor;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}
