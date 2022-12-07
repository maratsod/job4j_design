package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {
    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
         return storage.keySet().stream()
                 .filter(e -> e.equals(id))
                 .findFirst()
                 .map((e) -> storage.replace(e, model))
                 .isPresent();
    }

    @Override
    public boolean delete(String id) {
        return storage.keySet().stream()
                .filter(e -> e.equals(id))
                .findFirst()
                .map(e -> storage.remove(id, storage.get(id)))
                .isPresent();
    }

    @Override
    public T findById(String id) {
        return storage.keySet().stream()
                .filter(e -> e.equals(id))
                .findFirst()
                .map(storage::get)
                .orElse(null);
    }
}