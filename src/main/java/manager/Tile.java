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
}
