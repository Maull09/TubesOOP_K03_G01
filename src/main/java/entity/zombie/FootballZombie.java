package entity.zombie;

import entity.plant.Plant;

public class FootballZombie extends Zombie{
    private boolean hasHelmet;

    public FootballZombie(int row, int col) {
        super("FootballZombie", 350, 100, 2, false, row, col, true);
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
