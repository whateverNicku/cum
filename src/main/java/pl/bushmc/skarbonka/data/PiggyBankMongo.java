package pl.bushmc.skarbonka.data;

import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.FullDocument;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.bushmc.api.API;
import pl.bushmc.skarbonka.Skarbonka;

public class PiggyBankMongo {

    public PiggyBankMongo() {
        Bukkit.getScheduler().runTaskAsynchronously(Skarbonka.getSkarbonka(), () -> API.getMongoStorage().getDatabase().getCollection("skarbonka").watch().fullDocument(FullDocument.UPDATE_LOOKUP).forEach((ChangeStreamDocument<Document> changeStreamDocument) -> {
            String name = changeStreamDocument.getFullDocument().getString("name");
            Double value = changeStreamDocument.getFullDocument().getDouble("value");

            if (Bukkit.getPlayer(name) != null) {
                Skarbonka.getPiggyBankManager().setPlayerData(new PiggyBankPlayer(name, value));
            }
        } ));
    }

    public PiggyBankPlayer getPlayerData(Player player) {
        String playerName = player.getName();
        Document queryDocument = new Document().append("name", playerName);

        Document playerDataDocument = API.getMongoStorage().getDatabase().getCollection("skarbonka").find(queryDocument).first();

        if (playerDataDocument == null) {
            createPlayerData(playerName);
            return new PiggyBankPlayer(playerName, 0D);
        }

        return new PiggyBankPlayer(playerName, playerDataDocument.getDouble("value"));
    }

    private void createPlayerData(String playerName) {
        Document newPlayerDocument = new Document()
                .append("name", playerName)
                .append("value", 0D);

        API.getMongoStorage().getDatabase().getCollection("skarbonka").insertOne(newPlayerDocument);
    }
}
