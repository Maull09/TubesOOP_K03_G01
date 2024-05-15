package entity.zombie;

import entity.plant.Plant;

public class ConeHeadZombie extends Zombie{
    private boolean hasCone;

    public ConeHeadZombie(int row, int col) {
        super("ConeHeadZombie", 250, 100, 1, false, row, col, true);
    }

    public boolean getHasCone() {
        return hasCone;
    }

    public void setHasCone(boolean hasCone) {
        this.hasCone = hasCone;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}