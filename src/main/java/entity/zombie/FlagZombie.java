package entity.zombie;

import javax.swing.ImageIcon;

import entity.plant.Plant;

public class FlagZombie extends Zombie{
    private boolean hasFlag;

    public FlagZombie() {
        super("Flag Zombie", 125, 105, 1, false, new ImageIcon("/resources/images/zombie/FlagZombie.png"));
    }

    public boolean getHasFlag() {
        return hasFlag;
    }

    public void setHasFlag(boolean hasFlag) {
        this.hasFlag = hasFlag;
        if (this.hasFlag == false) {
            this.setAttackDamage(100);
        }
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }

}
