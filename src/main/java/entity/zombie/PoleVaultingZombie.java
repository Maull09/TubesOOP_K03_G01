package src.main.java.entity.zombie;

import src.main.java.entity.plant.Plant;

public class PoleVaultingZombie extends Zombie{
    private boolean hasPole;

    public PoleVaultingZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Pole Vaulting Zombie", 175, 100, 1, false);
    }

    public boolean getHasPole() {
        return hasPole;
    }

    public void setHasPole(boolean hasPole){
        this.hasPole = hasPole;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}