package entity.plant;

import entity.Entity;
import util.ListOf;

import javax.swing.ImageIcon;

import data.TimeKeeper;
import entity.zombie.Zombie;

public class Plant extends Entity{
    private int cost;
    private int range;
    private int cooldown;

    public Plant(String name, int cost, int health, int attackDamage, int attackSpeed, int range, int cooldown, ImageIcon icon, int row, int col) {
        super(name,health,attackDamage,attackSpeed,icon, row, col, true);
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

    public void die() {
        super.setIsAlive(false);
        System.out.println(this.getName() + " died!");
    }


    @Override
    public void attack(){}
    public void attack(ListOf<Entity> tile, TimeKeeper timeKeeper){
        // attack all zombies in the tile continues with duration of cooldown is attackSpeed
        if (timeKeeper.getCurrentTime() % this.getAttackSpeed() == 0){
            for (int i = 0; i < tile.size(); i++) {
                if (tile.get(i) instanceof Zombie) {
                    Zombie zombie = (Zombie) tile.get(i);
                    if (zombie.getHealth() > 0) {
                        zombie.setHealth(zombie.getHealth() - this.getAttackDamage());
                        System.out.println(this.getName() + " attacked " + zombie.getName() + " for " + this.getAttackDamage() + " damage");
                    }
                    if (zombie.getHealth() <= 0) {
                        zombie.setIsAlive(false);
                        zombie.die();
                    }
                }
            }
        }
    }
}