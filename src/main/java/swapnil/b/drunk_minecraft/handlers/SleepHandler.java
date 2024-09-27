package swapnil.b.drunk_minecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import swapnil.b.drunk_minecraft.DrinkEvent;
import swapnil.b.drunk_minecraft.Drunk_minecraft;

public class SleepHandler implements Listener {
    public SleepHandler(Drunk_minecraft plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerSleep(PlayerBedLeaveEvent event) {
        Player player = event.getPlayer();
        String message = "Player slept: " + player.getDisplayName();
        DrinkEvent.event(DrinkEvent.EventType.SLEEP, player, message);
    }
}
