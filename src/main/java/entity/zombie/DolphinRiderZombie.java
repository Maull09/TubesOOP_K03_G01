package entity.zombie;

import entity.plant.Plant;

public class DolphinRiderZombie extends Zombie{
    private boolean hasDolphin;

    public DolphinRiderZombie(int row, int col) {
        super("DolphinRiderZombie", 175, 100, 1, true, row, col, true);
    } 

    public boolean getHasDolphin() {
        return hasDolphin;
    }

    public void setHasDolphin(boolean hasDolphin) {
        this.hasDolphin = hasDolphin;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}
