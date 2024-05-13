package entity.zombie;

import javax.swing.ImageIcon;

import entity.plant.Plant;

public class PoleVaultingZombie extends Zombie{
    private boolean hasPole;

    public PoleVaultingZombie() {
        super("Pole Vaulting Zombie", 175, 100, 1, false, new ImageIcon("/resources/images/zombie/Polevaulterzombie.png"));
    }

    public boolean getHasPole() {
        return hasPole;
    }

    public void setHasPole(boolean hasPole){
        this.hasPole = hasPole;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}