package entity.zombie;

import entity.plant.Plant;

public class DoorZombie extends Zombie{
    private boolean hasDoor;

    public DoorZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Door Zombie", 300, 100, 1, false);
    }

    public boolean getHasDoor() {
        return hasDoor;
    }

    public void setHasDoor(boolean hasDoor){
        this.hasDoor = hasDoor;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}