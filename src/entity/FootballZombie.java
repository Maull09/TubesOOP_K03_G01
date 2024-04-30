package src.entity;

public class FootballZombie extends Zombie implements Attackable{
    
    public FootballZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Football Zombie", 350, 100, 2, false);
    }

    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - this.getAttackDamage());
    }

}
