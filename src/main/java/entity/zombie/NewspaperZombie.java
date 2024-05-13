package entity.zombie;

import javax.swing.ImageIcon;

import entity.plant.Plant;

public class NewspaperZombie extends Zombie{
    private boolean hasNewspaper;

    public NewspaperZombie() {
        super("Newspaper Zombie", 150, 100, 1, false, new ImageIcon("/resources/images/zombie/Newspaper_Zombie.png"));
    }

    public boolean getHasNewspaper() {
        return hasNewspaper;
    }

    public void setHasNewspaper(boolean hasNewspaper){
        this.hasNewspaper = hasNewspaper;
        if (hasNewspaper == false) {
            super.setAttackSpeed(getAttackSpeed() * 2);
        }
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}
