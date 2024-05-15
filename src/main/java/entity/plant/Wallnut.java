package entity.plant;

import javax.swing.ImageIcon;

import entity.zombie.Zombie;

public class Wallnut extends Plant{
    public Wallnut(int row, int col) {
        super("Wallnut", 50, 1000, 0, 0, 0, 20, row, col);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }

    
}