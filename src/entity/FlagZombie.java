package src.entity;

public class FlagZombie extends Zombie implements Attackable{

    public FlagZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic, boolean has_flag) {
        super("Flag Zombie", 125, 100, 1, false);
    }

    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - this.getAttackDamage());
    }

}
