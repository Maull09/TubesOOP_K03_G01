package entity.zombie;

import javax.swing.ImageIcon;

import entity.plant.Plant;

public class FootballZombie extends Zombie{
    private boolean hasHelmet;

    public FootballZombie() {
        super("FootballZombie", 350, 100, 2, false, new ImageIcon("/resources/images/zombie/FootballZombie.png"));
    }

    public boolean getHasHelmet() {
        return hasHelmet;
    }

    public void setHasHelmet(boolean hasHelmet) {
        this.hasHelmet = hasHelmet;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }

}
