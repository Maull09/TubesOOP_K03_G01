package entity.zombie;

import javax.swing.ImageIcon;

import entity.plant.Plant;

public class DuckyTubeZombie extends Zombie {
    public DuckyTubeZombie() {
        super("DuckyTubeZombie", 100, 100, 1, true, new ImageIcon("/resources/images/zombie/duckytubezombie.png"));
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }

}
