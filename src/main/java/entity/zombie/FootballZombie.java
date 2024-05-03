package src.main.java.entity.zombie;

import src.main.java.entity.plant.Plant;

public class FootballZombie extends Zombie{
    private boolean hasHelmet;

    public FootballZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Football Zombie", 350, 100, 2, false);
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
