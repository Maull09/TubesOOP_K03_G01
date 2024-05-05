package entity.plant;

import entity.zombie.Zombie;

public class Squash extends Plant{
    public Squash() {
        super("Squash", 50, 100, 5000, 0, 1, 20);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }




    
}