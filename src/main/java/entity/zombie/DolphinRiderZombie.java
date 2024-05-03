package src.main.java.entity.zombie;

import src.main.java.entity.plant.Plant;

public class DolphinRiderZombie extends Zombie{
    private boolean hasDolphin;

    public DolphinRiderZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Dolphin Rider Zombie", 175, 100, 1, true);
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
