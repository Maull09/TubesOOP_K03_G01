package entity.plant;

import javax.swing.ImageIcon;

import entity.zombie.Zombie;

public class Chomper extends Plant{
    public Chomper() {
        super("Chomper", 150, 100, 5000, 10, 1, 20, new ImageIcon("/resources/images/plant/Chomper.png"));
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }

    







}