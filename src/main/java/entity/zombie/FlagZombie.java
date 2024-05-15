package entity.zombie;

import entity.plant.Plant;

public class FlagZombie extends Zombie{

    public FlagZombie(int row, int col) {
        super("FlagZombie", 125, 105, 1, false, row, col, true, true, false);
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }

}
