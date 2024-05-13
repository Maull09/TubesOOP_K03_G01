package entity.plant;

import javax.swing.ImageIcon;

import entity.zombie.Zombie;

public class Peashooter extends Plant {
    public Peashooter() {
        super("Peashooter", 100, 100, 25, 4, -1, 10, new ImageIcon("/resources/images/plant/Peashooter.png"));
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }

}
