package manager;

import entity.plant.*;
import util.*;
public class Inventory {
    private ListOf<Plant> items;
    private static final int MAX_ITEMS = 10;  // Maximum number of items in the inventory

    public Inventory() {
        this.items = new ListOf<Plant>();
        items.add(PlantFactory.createPlant("Sunflower"));
        items.add(PlantFactory.createPlant("Peashooter"));
        items.add(PlantFactory.createPlant("Repeater"));
        items.add(PlantFactory.createPlant("SnowPea"));
        items.add(PlantFactory.createPlant("Wallnut"));
        items.add(PlantFactory.createPlant("Pumpkin"));
        items.add(PlantFactory.createPlant("Chomper"));
        items.add(PlantFactory.createPlant("TangleKelp"));
        items.add(PlantFactory.createPlant("Squash"));
        items.add(PlantFactory.createPlant("Lilypad"));
    }

    public void addItem(Plant item) {
        if (items.size() < MAX_ITEMS) {
            items.add(item);
        } else {
            System.out.println("Inventory is full. Cannot add more items.");
        }
    }

    public void removeItem(Plant item) {
        items.remove(item);
    }

    public ListOf<Plant> getAllItems() {
        return items;
    }
}

