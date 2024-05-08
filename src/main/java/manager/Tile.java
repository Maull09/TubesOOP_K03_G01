package manager;

import util.ListOf;
import entity.Entity;

public class Tile {
    private TileType type;
    private ListOf<Entity> entities;

    public Tile(TileType type) {
        this.type = type;
        this.entities = new ListOf<>();
    }

    public void addEntity(Entity entity) {
        // Example: Only allow water-based plants in POOL type tiles
        if (this.type == TileType.POOL) {
            entities.add(entity);
        } else if (this.type != TileType.POOL) {
            entities.add(entity);
        }
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public ListOf<Entity> getEntities() {
        return entities;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public boolean isEmpty() {
        return entities.isEmpty();
    }

    public boolean contains(Entity type) {
        return entities.contains(type);
    }

    public boolean contains(String type) {
        return entities.contains(type);
    }
}
