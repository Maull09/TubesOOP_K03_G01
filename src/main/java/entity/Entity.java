package entity;

import util.*;
import entity.zombie.Zombie;
import entity.plant.Plant;
import javax.swing.ImageIcon;

public class Entity implements Attackable{
    private String name;
    private int health;
    private int attackDamage;
    private int attackSpeed;
    private boolean isAlive;
    private ImageIcon icon;

    public Entity(String name, int health, int attackDamage, int attackSpeed, ImageIcon icon)
    {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.icon = icon;
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

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    } 

    @Override
    public void attack(Entity entity) 
    {
        entity.setHealth(getHealth() - this.getAttackDamage());
    }

    @Override
    public void attack() 
    {
        System.out.println("Attacking...");
    }

    @Override
    public void attack(Zombie zombie) 
    {
        zombie.setHealth(zombie.getHealth() - this.getAttackDamage());
    }

    @Override
    public void attack(Plant plant) 
    {
        plant.setHealth(plant.getHealth() - this.getAttackDamage());
    }

    @Override
    public void attack(ListOf<Entity> tile) 
    {
        for (int i = 0; i < tile.size(); i++) {
            tile.get(i).setHealth(tile.get(i).getHealth() - this.getAttackDamage());
        }
    }
}
