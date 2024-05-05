package manager;

import entity.plant.*;
import util.*;
public class Inventory {
    private ListOf<Plant> items;
    private static final int MAX_ITEMS = 10;  // Maximum number of items in the inventory

    public Inventory() {
        this.items = new ListOf<>();
    }

    public boolean addItem(Plant item) {
        if (items.size() < MAX_ITEMS) {
            items.add(item);
            return true;
        } else {
            System.out.println("Inventory is full. Cannot add more items.");
            return false;
        }
    }

    public void removeItem(Plant item) {
        items.remove(item);
    }

    public ListOf<Plant> getAllItems() {
        return items;
    }
}

