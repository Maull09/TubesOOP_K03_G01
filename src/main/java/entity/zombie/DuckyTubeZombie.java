package entity.zombie;

import entity.plant.Plant;

public class DuckyTubeZombie extends Zombie {
    public DuckyTubeZombie(int row, int col) {
        super("DuckyTubeZombie", 100, 100, 1, true, row, col, true, false, false);
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }

}
