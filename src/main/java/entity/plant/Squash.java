package entity.plant;

import javax.swing.ImageIcon;

import entity.zombie.Zombie;

public class Squash extends Plant{
    // private boolean hasAttack = true;

    public Squash() {
        super("Squash", 50, 100, 5000, 0, 1, 20, new ImageIcon("/resources/images/plant/Squash.png"));
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }

    public void HasAttack()
    {
        super.setIsAlive(false);
    }




    
}