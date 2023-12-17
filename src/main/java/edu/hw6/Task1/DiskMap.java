package edu.hw6.Task1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    HashMap<String, String> values = new HashMap<>();

    public DiskMap() {
    }

    public static DiskMap ofMap(Map<String, String> m) {
        DiskMap result = new DiskMap();
        result.putAll(m);
        return result;
    }

    public static DiskMap open(Path path) throws IOException {
        DiskMap result = new DiskMap();
        for (String line : Files.readAllLines(path)) {
            if (line.isBlank()) {
                continue;
            }

            final String[] keyValue = line.split(":");
            if (keyValue.length != 2) {
                throw new IllegalArgumentException("Line \"" + line + "\" must contain only one colon");
            }

            final String key = keyValue[0];
            final String value = keyValue[1];
            result.put(key, value);
        }
        return result;
    }

    public void save(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Entry<String, String> entry : values.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
        }
    }

    public void appendFile(Path path) throws IOException {
        this.putAll(DiskMap.open(path));
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return values.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return values.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        return values.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return values.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        values.putAll(m);
    }

    @Override
    public void clear() {
        values.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return values.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return values.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return values.entrySet();
    }

    @SuppressWarnings("checkstyle:CovariantEquals")
    public boolean equals(Map<String, String> other) {
        return this.entrySet().equals(other.entrySet());
    }
}
