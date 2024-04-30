package src.entity;

public class PoleVaultingZombie extends Zombie implements Attackable{
    public PoleVaultingZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super("Pole Vaulting Zombie", 175, 100, 1, false);
    }

    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - this.getAttackDamage());
    }
}