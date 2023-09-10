package pl.bushmc.skarbonka;

import org.bukkit.OfflinePlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class PAPI extends PlaceholderExpansion {

    private final DecimalFormat decimalFormat;

    public PAPI() {
        decimalFormat = new DecimalFormat("0.00");
    }

    @Override
    public @NotNull String getAuthor() {
        return "BushMC";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "skarbonka";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("stan")){
            return decimalFormat.format(Skarbonka.getPiggyBankManager().getPlayerData(player.getName()).getValue());
        }
        return null;
    }
}