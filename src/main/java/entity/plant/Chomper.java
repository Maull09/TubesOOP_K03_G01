package entity.plant;

import entity.zombie.Zombie;

public class Chomper extends Plant{
    public Chomper() {
        super("Chomper", 150, 100, 5000, 10, 1, 20);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }

    







}