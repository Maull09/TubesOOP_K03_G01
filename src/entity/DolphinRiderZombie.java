package src.entity;

public class DolphinRiderZombie extends Zombie implements Attackable{

    public DolphinRiderZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Dolphin Rider Zombie", 175, 100, 1, true);
    } 

    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - this.getAttackDamage());
    }
}
