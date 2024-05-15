package entity.zombie;

import entity.plant.Plant;

public class ConeHeadZombie extends Zombie{

    public ConeHeadZombie(int row, int col) {
        super("ConeHeadZombie", 250, 100, 1, false, row, col, true, true, false);
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}