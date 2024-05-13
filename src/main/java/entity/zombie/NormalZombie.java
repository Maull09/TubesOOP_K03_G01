package entity.zombie;

import javax.swing.ImageIcon;

import entity.plant.Plant;

public class NormalZombie extends Zombie{

    public NormalZombie() {
        super("Normal Zombie", 125, 100, 1, false, new ImageIcon("/resources/images/zombie/NormalZombie.png"));
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }

}
