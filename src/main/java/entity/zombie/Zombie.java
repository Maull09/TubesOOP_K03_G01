package entity.zombie;

import util.ListOf;
import util.Moveable;

import entity.Entity;
import entity.plant.Plant;

public class Zombie extends Entity implements Moveable{
    private boolean is_aquatic;
    private boolean isFaster;
    private boolean isSlowed;

    public Zombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic, int row, int col, boolean isAlive, boolean isFaster, boolean isSlowed) {
        super(name, health, attackDamage, attackSpeed, row, col, true);
        this.is_aquatic = is_aquatic;
        this.isFaster = isFaster;
        this.isSlowed = false;
    }

    public boolean getIsAquatic() {
        return is_aquatic;
    }

    public void setIsAquatic(boolean is_aquatic) {
        this.is_aquatic = is_aquatic;
    }

    public boolean getIsFaster() {
        return isFaster;
    }

    public void setIsFaster(boolean isFaster) {
        this.isFaster = isFaster;
    }

    public boolean getIsSlowed() {
        return isSlowed;
    }

    public void setIsSlowed(boolean isSlowed) {
        this.isSlowed = isSlowed;
    }

    public void die() {
        System.out.println(this.getName() + " died!");
    }

    @Override
    public void move(Zombie zombie) {}
    public void attack(Plant plant){}
    public void attack(ListOf<Entity> tile){}
}

