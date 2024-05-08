package entity.zombie;

import util.Moveable;
import entity.Entity;
import java.util.List;
import java.util.Random;
import entity.plant.Plant;
import manager.GameMap;

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

    public void spawnZombie(GameMap gameMap) {
        Random random = new Random();
        List spawnLand = List.of(1, 2, 5, 6);
        List spawnWater = List.of(3, 4);
        List<String> zombieTypes = List.of("Normal", "ConeHead", "Pole Vaulting", "Bucket Head", "Ducky Tube", "Dolphin Rider", "Door", "Football", "Flag", "Newspaper");
        double spawnChance = 0.3;
        if (random.nextDouble() < spawnChance) {
            Zombie zombie = ZombieFactory.createZombie(zombieTypes.get(random.nextInt(zombieTypes.size())));
            if (zombie.getName().equals("Ducky Tube") || zombie.getName().equals("Dolphin Rider")) {
                int randomIndex = random.nextInt(spawnWater.size());
                gameMap.addEntity(0, randomIndex, zombie);
            } else {
                int randomIndex = random.nextInt(spawnLand.size());
                gameMap.addEntity(0, randomIndex, zombie);
            }
        }
    }

    public void die() {
        System.out.println(this.getName() + " died!");
    }

    @Override
    public void move(Zombie zombie) {}
    public void attack(Plant plant){}
}

