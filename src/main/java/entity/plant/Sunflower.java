package entity.plant;

import entity.zombie.Zombie;
import manager.GameMap;
import data.TimeKeeper;
import entity.*;

public class Sunflower extends Plant{
    public Sunflower(int row, int col) {
        super("Sunflower", 50, 100, 0, 0, 0, 10, row, col);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }

    public void produceSun()
    {
        Sun.addSun();
    }

    public void attackZombies(GameMap gameMap, TimeKeeper timeKeeper) {
        if (timeKeeper.getCurrentTime() % 3 == 0) {
            produceSun();
        }
    }

}