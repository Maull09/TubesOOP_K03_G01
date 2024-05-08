package util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import entity.Entity;

public class ListOf<T> {
    private List<T> items;

    public ListOf() {
        this.items = new ArrayList<>();
    }

    public void add(T item) {
        items.add(item);
    }

    public void remove(T item) {
        items.remove(item);
    }

    public T get(int index) {
        return items.get(index);
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public List<T> find(Predicate<T> predicate) {
        List<T> results = new ArrayList<>();
        for (T item : items) {
            if (predicate.test(item)) {
                results.add(item);
            }
        }
        return results;
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public ListOf<Entity> getAllOfType(Class<?> type, ListOf<Entity> entities) {
        ListOf<Entity> result = new ListOf<>();
        for (int i = 0; i < entities.size(); i++) {
            if (type.isInstance(entities.get(i))) {
                result.add(entities.get(i));
            }
        }
        return result;
    }

    public boolean contains(Class<?> type, ListOf<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            if (type.isInstance(entities.get(i))) {
                return true;
            }
        }
        return false;
    }
}

