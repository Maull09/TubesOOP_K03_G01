package entity.zombie;


import entity.plant.Plant;

public class DoorZombie extends Zombie{
    private boolean hasArmor;

    public DoorZombie(int row, int col) {
        super("DoorZombie", 300, 100, 1, false, row, col, true);
    }

    public boolean getHasDoor() {
        return hasArmor;
    }

    public void setHasDoor(boolean hasArmor){
        this.hasArmor = hasArmor;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}