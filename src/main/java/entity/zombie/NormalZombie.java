package entity.zombie;

import entity.plant.Plant;

public class NormalZombie extends Zombie{

    public NormalZombie(int row, int col) {
        super("NormalZombie", 125, 100, 1, false, row, col, true, false, false);
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }

}
