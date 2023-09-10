package pl.bushmc.skarbonka.commands;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.ADoubleArgument;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import org.bson.Document;
import org.bukkit.entity.Player;
import pl.bushmc.api.API;
import pl.bushmc.skarbonka.Skarbonka;

@Command("skarbonka")
public class SkarbonkaCommand {

    @Subcommand("dodaj")
    @Permission("skarbonka.dodaj")
    public static void addSkarbonka(Player player, @APlayerArgument Player target, @ADoubleArgument double value) {
        Document targetDocument = new Document()
                .append("name", target.getName())
                .append("value", Skarbonka.getPiggyBankManager().getPlayerData(target.getName()).getValue());

        Document newTargetDocument = new Document()
                .append("name", target.getName())
                .append("value", Skarbonka.getPiggyBankManager().getPlayerData(target.getName()).getValue() + value);

        API.getMongoStorage().getDatabase().getCollection("skarbonka").findOneAndReplace(targetDocument, newTargetDocument);
    }

    @Subcommand("zabierz")
    @Permission("skarbonka.zabierz")
    public static void removeSkarbonka(Player player, @APlayerArgument Player target, @ADoubleArgument double value) {
        Document targetDocument = new Document()
                .append("name", target.getName())
                .append("value", Skarbonka.getPiggyBankManager().getPlayerData(target.getName()).getValue());

        Document newTargetDocument = new Document()
                .append("name", target.getName())
                .append("value", Skarbonka.getPiggyBankManager().getPlayerData(target.getName()).getValue() - value);

        API.getMongoStorage().getDatabase().getCollection("skarbonka").findOneAndReplace(targetDocument, newTargetDocument);
    }

    @Subcommand("ustaw")
    @Permission("skarbonka.ustaw")
    public static void setSkarbonka(Player player, @APlayerArgument Player target, @ADoubleArgument double value) {
        Document targetDocument = new Document()
                .append("name", target.getName())
                .append("value", Skarbonka.getPiggyBankManager().getPlayerData(target.getName()).getValue());

        Document newTargetDocument = new Document()
                .append("name", target.getName())
                .append("value", value);

        if (targetDocument == newTargetDocument) { return; }

        API.getMongoStorage().getDatabase().getCollection("skarbonka").findOneAndReplace(targetDocument, newTargetDocument);
    }

}