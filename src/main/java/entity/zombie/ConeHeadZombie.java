package src.main.java.entity.zombie;

import src.main.java.entity.plant.Plant;

public class ConeHeadZombie extends Zombie{
    private boolean hasCone;

    public ConeHeadZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Cone Head Zombie", 250, 100, 1, false);
    }

    public boolean getHasCone() {
        return hasCone;
    }

    public void setHasCone(boolean hasCone) {
        this.hasCone = hasCone;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}