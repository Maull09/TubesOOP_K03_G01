package entity.zombie;

import javax.swing.ImageIcon;

import entity.plant.Plant;

public class DoorZombie extends Zombie{
    private boolean hasDoor;

    public DoorZombie() {
        super("Door Zombie", 300, 100, 1, false, new ImageIcon("/resources/images/zombie/DoorZombie.png"));
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