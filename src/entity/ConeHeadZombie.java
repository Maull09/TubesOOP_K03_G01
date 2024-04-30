package src.entity;

public class ConeHeadZombie extends Zombie implements Attackable{

    public ConeHeadZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Cone Head Zombie", 250, 100, 1, false);
    }

    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - this.getAttackDamage());
    }
}