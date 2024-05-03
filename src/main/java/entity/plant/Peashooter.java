package src.main.java.entity.plant;

import src.main.java.entity.zombie.Zombie;

public class Peashooter extends Plant {
    public Peashooter() {
        super("Peashooter", 100, 100, 25, 4, -1, 10);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }

}
