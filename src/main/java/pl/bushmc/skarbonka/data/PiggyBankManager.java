package pl.bushmc.skarbonka.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.bushmc.skarbonka.Skarbonka;

import java.util.HashMap;
import java.util.Map;

public class PiggyBankManager {

    private final Map<String, PiggyBankPlayer> players;

    public PiggyBankManager() {
        players = new HashMap<>();
        Bukkit.getScheduler().runTaskAsynchronously(Skarbonka.getSkarbonka(), () -> Bukkit.getOnlinePlayers().forEach(this::createPlayerData));
    }

    public void createPlayerData(Player player) {
        String playerName = player.getName();
        PiggyBankPlayer piggyBankPlayer = Skarbonka.getPiggyBankMongo().getPlayerData(player);
        players.put(playerName, piggyBankPlayer);
    }

    public void setPlayerData(PiggyBankPlayer piggyBankPlayer) {
        players.put(piggyBankPlayer.getName(), piggyBankPlayer);
    }


    public void deletePlayerData(Player player) {
        players.remove(player.getName());
    }

    public PiggyBankPlayer getPlayerData(String playerName) {
        return players.get(playerName);
    }
}
