package entity.plant;

import entity.Entity;
import util.ListOf;

public class Plant extends Entity{
    private int cost;
    private int range;
    private int cooldown;

    public Plant(String name, int cost, int health, int attackDamage, int attackSpeed, int range, int cooldown) {
        super(name,health,attackDamage,attackSpeed);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range){
        this.range = range;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown){
        this.cooldown = cooldown;
    }
    @Override
    public void attack(){}
    public void attack(ListOf<Entity> tile){}
}