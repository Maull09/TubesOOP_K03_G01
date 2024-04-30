package src.entity;

public class DuckyTubeZombie extends Zombie implements Attackable{
    public DuckyTubeZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Ducky Tube Zombie", 100, 100, 1, true);
    }

    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - this.getAttackDamage());
    }

}
