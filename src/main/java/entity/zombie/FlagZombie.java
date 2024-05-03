package src.main.java.entity.zombie;

import src.main.java.entity.plant.Plant;

public class FlagZombie extends Zombie{
    private boolean hasFlag;

    public FlagZombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic, boolean has_flag) {
        super("Flag Zombie", 125, 105, 1, false);
    }

    public boolean getHasFlag() {
        return hasFlag;
    }

    public void setHasFlag(boolean hasFlag) {
        this.hasFlag = hasFlag;
        if (this.hasFlag == false) {
            this.setAttackDamage(100);
        }
    }

    @Override
    public void attack(Plant plant) {
        super.attack(plant);
    }

}
