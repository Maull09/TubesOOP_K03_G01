package entity.zombie;

import javax.swing.ImageIcon;

import entity.plant.Plant;

public class ConeHeadZombie extends Zombie{
    private boolean hasCone;

    public ConeHeadZombie() {
        super("ConeHeadZombie", 250, 100, 1, false, new ImageIcon("/resources/images/zombie/ConeHatZombie.png"));
    }

    public boolean getHasCone() {
        return hasCone;
    }

    public void setHasCone(boolean hasCone) {
        this.hasCone = hasCone;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}