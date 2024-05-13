package manager;

import entity.plant.*;
import util.*;

public class DeckTanaman {
    private ListOf<Plant> plants;
    private static final int MAX_SIZE = 6;  // Maximum number of plants in the deck

    public DeckTanaman() {
        this.plants = new ListOf<>();
    }

    public boolean addPlant(Plant plant) {
        if (plants.size() < MAX_SIZE) {
            plants.add(plant);
            return true;
        } else {
            System.out.println("Deck is full. Cannot add more plants.");
        }
        return false;
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public ListOf<Plant> getPlants() {
        return plants;
    }

    public boolean containsPlant(Plant plant) {
        return plants.contains(plant);
    }
}

