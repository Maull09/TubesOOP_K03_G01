package entity.zombie;

import util.ListOf;
import util.Moveable;
import entity.Entity;
import java.util.Random;
import entity.plant.Plant;

public class Zombie extends Entity implements Moveable{
    private boolean is_aquatic;

    public Zombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic) {
        super(name, health, attackDamage, attackSpeed);
        this.is_aquatic = is_aquatic;
    }

    public boolean getIsAquatic() {
        return is_aquatic;
    }

    public void setIsAquatic(boolean is_aquatic) {
        this.is_aquatic = is_aquatic;
    }

    public void spawnZombie() {
        Random random = new Random();
        double spawnChance = 0.3;
        if (random.nextDouble() < spawnChance) {
            System.out.println(this.getName() + " spawned!");
        }
    }

    public void die() {
        System.out.println(this.getName() + " died!");
    }

    @Override
    public void move(Zombie zombie) {}
    public void attack(Plant plant){}
    public void attack(ListOf<Entity> tile){}
}

