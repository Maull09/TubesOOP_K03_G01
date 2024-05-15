package entity.zombie;

import entity.plant.Plant;

public class ConeHeadZombie extends Zombie{
    private boolean hasArmor;

    public ConeHeadZombie(int row, int col) {
        super("ConeHeadZombie", 250, 100, 1, false, row, col, true);
    }

    public boolean getHasCone() {
        return hasArmor;
    }

    public void setHasCone(boolean hasArmor) {
        this.hasArmor = hasArmor;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}