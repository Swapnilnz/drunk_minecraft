package swapnil.b.drunk_minecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import swapnil.b.drunk_minecraft.DrinkEvent;
import swapnil.b.drunk_minecraft.Drunk_minecraft;

import java.util.*;

public class InWaterHandler implements Listener {
    public InWaterHandler(Drunk_minecraft plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private final Map<UUID, Long> enteredWaterAt = new HashMap<>();

    @EventHandler
    public void onPlayerEntersWater(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.isInWater()) {
            if (!enteredWaterAt.containsKey(player.getUniqueId())) {
                String message = "Player entered water: " + player.getDisplayName();
                DrinkEvent.event(DrinkEvent.EventType.WATER, player, message);
            }
            enteredWaterAt.put(player.getUniqueId(), System.currentTimeMillis());
        } else if (!player.isInWater() && enteredWaterAt.containsKey(player.getUniqueId()) && System.currentTimeMillis() - enteredWaterAt.get(player.getUniqueId()) > 300) {
            enteredWaterAt.remove(player.getUniqueId());
        }
    }
}
