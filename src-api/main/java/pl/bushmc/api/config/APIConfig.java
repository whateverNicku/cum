package pl.bushmc.api.config;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class APIConfig {

    @SerializedName("mongodb-connection")
    MongoDBData mongoDBData = new MongoDBData("", "");

    @Data
    @AllArgsConstructor
    public static class MongoDBData {

        String connectionUrl;
        String databaseName;

    }


}