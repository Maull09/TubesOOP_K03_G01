package entity.plant;

import entity.zombie.Zombie;

public class Sunflower extends Plant{
    public Sunflower() {
        super("Sunflower", 50, 100, 0, 0, 0, 10);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }

    // public void produceSun()
    // {
    //     addSun();
    // }

    
}