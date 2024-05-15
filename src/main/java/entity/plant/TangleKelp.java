package entity.plant;

import javax.swing.ImageIcon;

import entity.zombie.Zombie;

public class TangleKelp extends Plant{
    public TangleKelp(int row, int col) {
        super("TangleKelp", 25, 100, 5000, 0, 1, 20, new ImageIcon("/resources/images/plant/Tangle-kelp.png"), row, col);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }

    public void HasAttack()
    {
        super.setIsAlive(false);
    }


}