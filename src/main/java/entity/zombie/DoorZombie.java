package entity.zombie;


import entity.plant.Plant;

public class DoorZombie extends Zombie{
    private boolean hasDoor;

    public DoorZombie(int row, int col) {
        super("DoorZombie", 300, 100, 1, false, row, col, true);
    }

    public boolean getHasDoor() {
        return hasDoor;
    }

    public void setHasDoor(boolean hasDoor){
        this.hasDoor = hasDoor;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}