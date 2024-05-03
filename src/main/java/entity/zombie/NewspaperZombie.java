package src.main.java.entity.zombie;

import src.main.java.entity.plant.Plant;

public class NewspaperZombie extends Zombie{
    private boolean hasNewspaper;

    public NewspaperZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Newspaper Zombie", 150, 100, 1, false);
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
