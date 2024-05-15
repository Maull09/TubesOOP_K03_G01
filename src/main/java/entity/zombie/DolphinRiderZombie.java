package entity.zombie;

import entity.plant.Plant;

public class DolphinRiderZombie extends Zombie{
    private boolean hasArmor;

    public DolphinRiderZombie(int row, int col) {
        super("DolphinRiderZombie", 175, 100, 1, true, row, col, true, true, false);
    } 

    public boolean getHasDolphin() {
        return hasArmor;
    }

    public void setHasDolphin(boolean hasArmor) {
        this.hasArmor = hasArmor;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}
