package entity.zombie;

import data.GameState;
import data.TimeKeeper;
import entity.Projectile;
import entity.plant.Plant;
import manager.Tile;

public class NormalZombie extends Zombie{

    public NormalZombie(int row, int col) {
        super("NormalZombie", 125, 100, 1, false, row, col, true, false, false);
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}
