package entity.zombie;


import entity.plant.Plant;

public class DoorZombie extends Zombie{

    public DoorZombie(int row, int col) {
        super("DoorZombie", 300, 100, 1, false, row, col, true, true, false);
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}