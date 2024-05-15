package entity.zombie;

import javax.swing.ImageIcon;

import entity.plant.Plant;

public class DolphinRiderZombie extends Zombie{
    private boolean hasDolphin;

    public DolphinRiderZombie() {
        super("DolphinRiderZombie", 175, 100, 1, true, new ImageIcon("/resources/images/zombie/Dolphinzombie.png"));
    } 

    public boolean getHasDolphin() {
        return hasDolphin;
    }

    public void setHasDolphin(boolean hasDolphin) {
        this.hasDolphin = hasDolphin;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}
