package entity.zombie;

import entity.plant.Plant;

public class PoleVaultingZombie extends Zombie{

    public PoleVaultingZombie(int col, int row) {
        super("PoleVaultingZombie", 175, 100, 1, false, row, col, true, true, false);
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}