package entity.zombie;

import entity.plant.Plant;

public class PoleVaultingZombie extends Zombie{
    private boolean hasPole;

    public PoleVaultingZombie(int col, int row) {
        super("PoleVaultingZombie", 175, 100, 1, false, row, col, true);
    }

    public boolean getHasPole() {
        return hasPole;
    }

    public void setHasPole(boolean hasPole){
        this.hasPole = hasPole;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}