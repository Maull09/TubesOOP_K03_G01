package entity.plant;

import data.TimeKeeper;
import entity.zombie.Zombie;
import manager.GameMap;

public class Wallnut extends Plant{
    public Wallnut(int row, int col) {
        super("Wallnut", 50, 1000, 0, 0, 0, 20, row, col);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }

    public void attackZombies() {}
}