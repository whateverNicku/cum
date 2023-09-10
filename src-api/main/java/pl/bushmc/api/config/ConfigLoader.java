package pl.bushmc.api.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.io.FileReader;
import java.io.FileWriter;

@Log
public class ConfigLoader {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ConfigLoader() {
        log.info("ConfigLoader loaded");
    }

    @SneakyThrows
    public <T> T load(String filePath, Class<T> configClass) {
        @Cleanup FileReader reader = new FileReader(filePath);
        return gson.fromJson(reader, configClass);
    }

    @SneakyThrows
    public void save(String filePath, Object config) {
        @Cleanup FileWriter writer = new FileWriter(filePath);
        gson.toJson(config, writer);
    }
}
