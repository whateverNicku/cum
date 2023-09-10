package pl.bushmc.api;

import lombok.Getter;
import lombok.extern.java.Log;
import org.bukkit.plugin.java.JavaPlugin;
import pl.bushmc.api.config.APIConfig;
import pl.bushmc.api.config.ConfigLoader;
import pl.bushmc.api.data.MongoStorage;
import pl.bushmc.api.utils.ColorParser;

@Log
public class API extends JavaPlugin {

    @Getter private APIConfig apiConfig;

    @Getter private static ConfigLoader configLoader;
    @Getter private static MongoStorage mongoStorage;
    @Getter private static ColorParser colorParser;

    @Override
    public void onEnable() {

        configLoader = new ConfigLoader();

        if (!getDataFolder().exists() && getDataFolder().mkdir()) {
            configLoader.save(getDataFolder().getAbsolutePath() + "/config.json", new APIConfig());
        }
        apiConfig = configLoader.load(getDataFolder().getAbsolutePath() + "/config.json", APIConfig.class);

        mongoStorage = new MongoStorage();
        mongoStorage.connect(apiConfig.getMongoDBData().getConnectionUrl(), apiConfig.getMongoDBData().getDatabaseName());

        colorParser = new ColorParser();
    }

    @Override
    public void onDisable() {
    }
}
