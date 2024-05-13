package entity.zombie;

import javax.swing.ImageIcon;

import entity.plant.Plant;

public class BucketHeadZombie extends Zombie{
    private boolean hasBucket;

    public BucketHeadZombie() {
        super("Bucket Head Zombie", 300, 100, 1, false, new ImageIcon("/resources/images/zombie/BucketheadZombie.png"));
    }
    
    public boolean getHasBucket() {
        return hasBucket;
    }

    public void setHasBucket(boolean hasBucket){
        this.hasBucket = hasBucket;
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }
}
