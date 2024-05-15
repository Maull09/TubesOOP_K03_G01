package entity.zombie;

import entity.plant.Plant;

public class FootballZombie extends Zombie{
    private boolean hasArmor;

    public FootballZombie(int row, int col) {
        super("FootballZombie", 350, 100, 2, false, row, col, true);
    }

    public boolean getHasHelmet() {
        return hasArmor;
    }

    public void setHasHelmet(boolean hasArmor) {
        this.hasArmor = hasArmor;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }

}
