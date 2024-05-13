package entity.plant;

import javax.swing.ImageIcon;

import entity.zombie.Zombie;

public class Wallnut extends Plant{
    public Wallnut() {
        super("Wallnut", 50, 1000, 0, 0, 0, 20, new ImageIcon("/resources/images/plant/Wall-nut.png"));
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }




    
}