package entity.plant;

import entity.zombie.Zombie;

public class TangleKelp extends Plant{
    public TangleKelp() {
        super("TangleKelp", 25, 100, 5000, 0, 1, 20);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }

    public boolean getHasAttack()
    {
        return this.hasAttack;
    }

    public void setHasAttack(boolean hasAttack)
    {
        super.setIsAlive(hasAttack);
    }

}