package entity.plant;

import entity.zombie.Zombie;

import javax.swing.ImageIcon;

import entity.*;

public class Sunflower extends Plant{
    public Sunflower(int row, int col) {
        super("Sunflower", 50, 100, 0, 0, 0, 10, new ImageIcon("/resources/images/plant/Sunflower.png"), row, col);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }

    public void produceSun()
    {
        Sun.addSun();
    }

}