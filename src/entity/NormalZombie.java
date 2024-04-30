package src.entity;

public class NormalZombie extends Zombie implements Attackable{

    public NormalZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Normal Zombie", 125, 100, 1, false);
    }

    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - this.getAttackDamage());
    }

}
