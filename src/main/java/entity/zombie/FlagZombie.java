package entity.zombie;

import entity.plant.Plant;

public class FlagZombie extends Zombie{
    private boolean hasArmor;

    public FlagZombie(int row, int col) {
        super("FlagZombie", 125, 105, 1, false, row, col, true);
    }

    public boolean getHasFlag() {
        return hasArmor;
    }

    public void setHasFlag(boolean hasArmor) {
        this.hasArmor = hasArmor;
        if (this.hasArmor == false) {
            this.setAttackDamage(100);
        }
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }

}
