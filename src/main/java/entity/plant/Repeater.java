package entity.plant;

import entity.zombie.Zombie;

public class Repeater extends Plant{
    public Repeater() {
        super("Repeater", 200, 100, 25, 2, 0, 10);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }





}