package pl.bushmc.skarbonka.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.bushmc.skarbonka.Skarbonka;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
        Skarbonka.getPiggyBankManager().createPlayerData(playerJoinEvent.getPlayer());
    }

}
