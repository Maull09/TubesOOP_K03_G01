package entity.plant;

import javax.swing.ImageIcon;

import entity.zombie.Zombie;

public class Repeater extends Plant{
    public Repeater() {
        super("Repeater", 200, 100, 25, 2, 0, 10, new ImageIcon("/resources/images/plant/Repeater.png"));
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }





}