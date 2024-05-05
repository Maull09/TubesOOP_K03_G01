package entity.plant;

import entity.zombie.Zombie;

public class Lilypad extends Plant{
    public Lilypad() {
        super("Lilypad", 25, 100, 0, 0, 0, 10);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }





}