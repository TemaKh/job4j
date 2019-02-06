package ru.job4j.nonblocking;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlockingCashe {
    private final ConcurrentHashMap<Integer, Base> cache = new ConcurrentHashMap<>();

    /**
     * Method add object in cache.
     * @param model object.
     */
    public void add(Base model) {
        cache.putIfAbsent(model.getId(), model);
    }

    /**
     * Method  delete object in cache.
     * @param model object.
     */
    public void delete(Base model) {
        cache.computeIfPresent(model.getId(), ((integer, base) -> null));
    }

    /**
     * Method  update object version in cache.
     * @param model object.
     */
    public void update(Base model) {
        cache.computeIfPresent(model.getId(), (Integer integer, Base base) -> {
            if (model.getVersion() != base.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            model.updateVersion();
            return model;
        });
    }
}
