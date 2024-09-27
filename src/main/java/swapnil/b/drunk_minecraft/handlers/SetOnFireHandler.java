package swapnil.b.drunk_minecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import swapnil.b.drunk_minecraft.DrinkEvent;
import swapnil.b.drunk_minecraft.Drunk_minecraft;

public class SetOnFireHandler implements Listener {
    public SetOnFireHandler(Drunk_minecraft plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onEntitySetOnFire(EntityCombustEvent event) {
        if (event.getEntity() instanceof Player player) {
            String message = "Player set on fire: " + player.getDisplayName();
            DrinkEvent.event(DrinkEvent.EventType.FIRE, player, message);
        }
    }
}
