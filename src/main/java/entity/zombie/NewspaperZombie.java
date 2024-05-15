package entity.zombie;

import entity.plant.Plant;

public class NewspaperZombie extends Zombie{
    private boolean hasNewspaper;

    public NewspaperZombie(int row, int col) {
        super("NewspaperZombie", 150, 100, 1, false, row, col, true);
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
