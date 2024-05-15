package entity.plant;

import javax.swing.ImageIcon;

import entity.zombie.Zombie;

public class Chomper extends Plant{
    public Chomper(int row, int col) {
        super("Chomper", 150, 100, 5000, 10, 1, 20, row, col);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }


    







}