package src.entity;

public class NewspaperZombie extends Zombie implements Attackable{

    public NewspaperZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Newspaper Zombie", 150, 100, 2, false);
    }

    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - this.getAttackDamage());
    }
}
