package pl.bushmc.api.data;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@Log
public class MongoStorage {

    @Getter private MongoClient mongoClient;
    @Getter private MongoDatabase database;

    @SneakyThrows
    public void connect(String connectionString, String databaseName) {
        if (1 > connectionString.length()) {
            log.severe("Brak argumentow aby polaczyc z baza danych");
            return;
        }

        mongoClient = MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build());
        database = mongoClient.getDatabase(databaseName);
        log.info("Polaczono z baza danych");
    }

    @SneakyThrows
    public void disconnect() {
        if (mongoClient != null) {
            mongoClient.close();
            log.info("Rozłączono z bazą danych");
        }
    }
}