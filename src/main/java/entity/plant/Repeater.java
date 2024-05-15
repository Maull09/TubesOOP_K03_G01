package entity.plant;

import javax.swing.ImageIcon;

import entity.zombie.Zombie;

public class Repeater extends Plant{
    public Repeater(int row, int col) {
        super("Repeater", 200, 100, 25, 2, 0, 10, row, col);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }





}