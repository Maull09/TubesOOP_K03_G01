package entity;

import util.*;
import entity.zombie.Zombie;
import manager.Tile;
import entity.plant.Plant;

public class Entity implements Attackable{
    private String name;
    private int health;
    private int attackDamage;
    private int attackSpeed;
    private int row;
    private int col;
    private boolean isAlive;
    private int lastAttackTime;

    public Entity(String name, int health, int attackDamage, int attackSpeed, int row, int col, boolean isAlive)
    {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.row = row;
        this.col = col;
        this.isAlive = isAlive;
        this.lastAttackTime = 0;
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

    public int getRow() {
        return row;
    }

    public void setRow(int row){
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col){
        this.col = col;
    }

    public void takeDamage(int damage){
        this.health -= damage;
    }

    public int getlastAttackTime(){
        return lastAttackTime;
    }

    public int setlastAttackTime(int lastAttackTime){
        return this.lastAttackTime = lastAttackTime;
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

    public void attackPlants(Tile tile){
        for (int i = 0; i < tile.getPlants().size(); i++) {
            tile.getPlants().get(i).setHealth(tile.getPlants().get(i).getHealth() - this.getAttackDamage());
        }
    }
    
    public void attackZombies(Tile tile){
        for (int i = 0; i < tile.getZombies().size(); i++) {
            tile.getZombies().get(i).setHealth(tile.getZombies().get(i).getHealth() - this.getAttackDamage());
        }
    }

    public void attackPlants(){}
    public void attackZombies(){}
}
