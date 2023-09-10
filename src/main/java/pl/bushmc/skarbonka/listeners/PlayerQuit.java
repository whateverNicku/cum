package pl.bushmc.skarbonka.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.bushmc.skarbonka.Skarbonka;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent playerQuitEvent) {
        Skarbonka.getPiggyBankManager().deletePlayerData(playerQuitEvent.getPlayer());
    }

}
