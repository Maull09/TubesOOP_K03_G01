package entity.zombie;

import entity.plant.Plant;

public class FlagZombie extends Zombie{
    private boolean hasFlag;

    public FlagZombie(int row, int col) {
        super("FlagZombie", 125, 105, 1, false, row, col, true);
    }

    public boolean getHasFlag() {
        return hasFlag;
    }

    public void setHasFlag(boolean hasFlag) {
        this.hasFlag = hasFlag;
        if (this.hasFlag == false) {
            this.setAttackDamage(100);
        }
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }

}
