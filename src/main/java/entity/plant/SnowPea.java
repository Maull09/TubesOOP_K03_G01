package src.main.java.entity.plant;

import src.main.java.entity.zombie.Zombie;

public class SnowPea extends Plant{
    public SnowPea() {
        super("SnowPea", 175, 100, 25, 4, -1, 10);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }


    
}