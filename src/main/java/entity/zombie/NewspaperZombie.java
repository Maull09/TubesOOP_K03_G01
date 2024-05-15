package entity.zombie;

import entity.plant.Plant;

public class NewspaperZombie extends Zombie{

    public NewspaperZombie(int row, int col) {
        super("NewspaperZombie", 150, 100, 1, false, row, col, true, true, false);
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}
