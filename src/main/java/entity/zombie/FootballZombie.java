package entity.zombie;

import entity.plant.Plant;

public class FootballZombie extends Zombie{

    public FootballZombie(int row, int col) {
        super("FootballZombie", 350, 100, 2, false, row, col, true, true, false);
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }

}
