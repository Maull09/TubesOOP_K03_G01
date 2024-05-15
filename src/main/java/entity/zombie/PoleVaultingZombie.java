package entity.zombie;

import entity.plant.Plant;

public class PoleVaultingZombie extends Zombie{
    private boolean hasArmor;

    public PoleVaultingZombie(int col, int row) {
        super("PoleVaultingZombie", 175, 100, 1, false, row, col, true);
    }

    public boolean getHasPole() {
        return hasArmor;
    }

    public void setHasPole(boolean hasArmor){
        this.hasArmor = hasArmor;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}