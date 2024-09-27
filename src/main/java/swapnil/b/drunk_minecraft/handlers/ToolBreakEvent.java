package swapnil.b.drunk_minecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;
import swapnil.b.drunk_minecraft.DrinkEvent;
import swapnil.b.drunk_minecraft.Drunk_minecraft;

public class ToolBreakEvent implements Listener {
    public ToolBreakEvent(Drunk_minecraft plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerToolBreakEvent(PlayerItemBreakEvent event) {
        Player player = event.getPlayer();
        String message = "Player broke tool: " + player.getDisplayName();
        DrinkEvent.event(DrinkEvent.EventType.TOOL, player, message);
    }
}
