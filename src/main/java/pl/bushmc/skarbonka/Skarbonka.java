package pl.bushmc.skarbonka;

import dev.jorel.commandapi.CommandAPI;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.bushmc.skarbonka.commands.SkarbonkaCommand;
import pl.bushmc.skarbonka.data.PiggyBankManager;
import pl.bushmc.skarbonka.data.PiggyBankMongo;
import pl.bushmc.skarbonka.listeners.PlayerJoin;
import pl.bushmc.skarbonka.listeners.PlayerQuit;

public class Skarbonka extends JavaPlugin {

    @Getter private static JavaPlugin skarbonka;

    @Getter private static PiggyBankMongo piggyBankMongo;
    @Getter private static PiggyBankManager piggyBankManager;

    @Override
    public void onEnable() {

        skarbonka = this;

        piggyBankMongo = new PiggyBankMongo();
        piggyBankManager = new PiggyBankManager();


        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(), this);

        new PAPI().register();

        CommandAPI.registerCommand(SkarbonkaCommand.class);
    }
}
