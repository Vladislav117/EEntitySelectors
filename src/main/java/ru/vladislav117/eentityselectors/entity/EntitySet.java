package ru.vladislav117.eentityselectors.entity;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class EntitySet {
    public static int defaultHashMapCapacity = 512;

    public static int getDefaultHashMapCapacity() {
        return defaultHashMapCapacity;
    }

    public static void setDefaultHashMapCapacity(int defaultHashMapCapacity) {
        EntitySet.defaultHashMapCapacity = defaultHashMapCapacity;
    }

    protected Map<UUID, Entity> map;

    public EntitySet(@Nullable Collection<Entity> entities) {
        map = new HashMap<>(defaultHashMapCapacity);
        if (entities == null) return;
        for (Entity entity : entities) {
            if (map.containsKey(entity.getUniqueId())) continue;
            map.put(entity.getUniqueId(), entity);
        }
    }

    public EntitySet(@Nullable Entity entity) {
        map = new HashMap<>(defaultHashMapCapacity);
        if (entity == null) return;
        if (map.containsKey(entity.getUniqueId())) return;
        map.put(entity.getUniqueId(), entity);
    }

    public EntitySet(@Nullable EntitySet entities) {
        map = new HashMap<>(defaultHashMapCapacity);
        if (entities == null) return;
        for (Entity entity : entities.map.values()) {
            if (map.containsKey(entity.getUniqueId())) continue;
            map.put(entity.getUniqueId(), entity);
        }
    }

    public EntitySet() {
        map = new HashMap<>(defaultHashMapCapacity);
    }

    @Override
    public EntitySet clone() {
        return new EntitySet(this);
    }

    public int getSize() {
        return map.size();
    }

    public boolean contains(@Nullable UUID uuid) {
        if (uuid == null) return false;
        return map.containsKey(uuid);
    }

    public boolean contains(@Nullable Entity entity) {
        if (entity == null) return false;
        return map.containsKey(entity.getUniqueId());
    }

    public @Nullable Entity get(UUID uuid) {
        return map.get(uuid);
    }

    public boolean add(@Nullable Entity entity) {
        if (entity == null) return false;
        if (map.containsKey(entity.getUniqueId())) return false;
        map.put(entity.getUniqueId(), entity);
        return true;
    }

    public int add(@Nullable Collection<Entity> entities) {
        if (entities == null) return 0;
        int added = 0;
        for (Entity entity : entities) {
            if (add(entity)) added++;
        }
        return added;
    }

    public int add(@Nullable EntitySet entities) {
        if (entities == null) return 0;
        int added = 0;
        for (Entity entity : entities.map.values()) {
            if (add(entity)) added++;
        }
        return added;
    }

    public boolean remove(@Nullable Entity entity) {
        if (entity == null) return false;
        if (!map.containsKey(entity.getUniqueId())) return false;
        map.remove(entity.getUniqueId());
        return true;
    }

    public int remove(@Nullable Collection<Entity> entities) {
        if (entities == null) return 0;
        int removed = 0;
        for (Entity entity : entities) {
            if (remove(entity)) removed++;
        }
        return removed;
    }

    public int remove(@Nullable EntitySet entities) {
        if (entities == null) return 0;
        int removed = 0;
        for (Entity entity : entities.map.values()) {
            if (remove(entity)) removed++;
        }
        return removed;
    }

    public Collection<Entity> getEntities() {
        return map.values();
    }
}
