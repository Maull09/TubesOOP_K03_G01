package src.entity;

public class BucketHeadZombie extends Zombie implements Attackable{
    public BucketHeadZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Bucket Head Zombie", 300, 100, 1, false);
    }
    
    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - this.getAttackDamage());
    }
}
