package entity.plant;

import data.TimeKeeper;
import entity.zombie.Zombie;
import manager.GameMap;
import manager.Tile;

public class Lilypad extends Plant{
    public Lilypad(int row, int col) {
        super("Lilypad", 25, 100, 0, 0, 0, 10, row, col);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }

    public void attackZombies() {
    }
}