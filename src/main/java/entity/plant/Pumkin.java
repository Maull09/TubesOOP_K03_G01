package src.main.java.entity.plant;

// package src.entity;

public class Pumkin extends Plant{
    public Pumkin() {
        super("Pumkin", 125, 4000, 0, 0, 0, 20);
    }

    public void attack(Zombie zombie)
    {
        zombie.setHealth(getHealth()-getAttackDamage());
    }





}