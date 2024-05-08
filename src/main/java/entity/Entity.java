package entity;

import util.*;

public class Entity implements Attackable{
    private String name;
    private int health;
    private int attackDamage;
    private int attackSpeed;
    private boolean isAlive;

    public Entity(String name, int health, int attackDamage, int attackSpeed)
    {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage){
        this.attackDamage = attackDamage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed){
        this.attackSpeed = attackSpeed;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive){
        this.isAlive = isAlive;
    }

    @Override
    public void attack(Entity entity) 
    {
        entity.setHealth(getHealth() - this.getAttackDamage());
    }
}
