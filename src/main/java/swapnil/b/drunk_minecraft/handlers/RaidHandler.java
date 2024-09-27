package swapnil.b.drunk_minecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.event.raid.RaidFinishEvent;
import org.bukkit.event.raid.RaidTriggerEvent;
import swapnil.b.drunk_minecraft.DrinkEvent;
import swapnil.b.drunk_minecraft.Drunk_minecraft;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;

public class RaidHandler implements Listener {
    public RaidHandler(Drunk_minecraft plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onRaidStart(RaidTriggerEvent event) {
        Player player = event.getPlayer();
        String message = "Player started a raid: " + player.getDisplayName();
        DrinkEvent.event(DrinkEvent.EventType.RAID, player, message);
    }

    @EventHandler
    public void onRaidEnd(RaidFinishEvent event) {
        String message = "Raid success, everyone else drink!";
        for (Player player : getServer().getOnlinePlayers()) {
            if (!event.getWinners().contains(player)) {
                DrinkEvent.event(DrinkEvent.EventType.RAID, player, message);
            }
        }
    }
}
