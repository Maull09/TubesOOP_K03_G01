package src.main.java.entity.plant;

import src.main.java.entity.zombie.Zombie;

public class Wallnut extends Plant{
    public Wallnut() {
        super("Wallnut", 50, 1000, 0, 0, 0, 20);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }




    
}