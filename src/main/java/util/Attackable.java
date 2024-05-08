package util;

import entity.Entity;
import entity.zombie.Zombie;
import entity.plant.Plant;

public interface Attackable {
    public void attack(Entity entity);
    public void attack();
    public void attack(Zombie zombie);
    public void attack(Plant plant);
    public void attack(ListOf<Entity> tile);
} 
