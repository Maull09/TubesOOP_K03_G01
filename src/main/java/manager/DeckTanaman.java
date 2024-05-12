package manager;

import entity.plant.*;
import util.*;

public class DeckTanaman {
    private ListOf<Plant> plants;
    private static final int MAX_SIZE = 6;  // Maximum number of plants in the deck

    public DeckTanaman() {
        this.plants = new ListOf<>();
    }

    public void addPlant(Plant plant) {
        if (plants.size() < MAX_SIZE) {
            plants.add(plant);
        } else {
            System.out.println("Deck is full. Cannot add more plants.");
        }
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public ListOf<Plant> getPlants() {
        return plants;
    }
}

