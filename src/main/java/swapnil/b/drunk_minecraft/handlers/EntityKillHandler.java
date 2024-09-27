package swapnil.b.drunk_minecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import swapnil.b.drunk_minecraft.DrinkEvent;
import swapnil.b.drunk_minecraft.Drunk_minecraft;

public class EntityKillHandler implements Listener {
    public EntityKillHandler(Drunk_minecraft plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onKillEnderman(EntityDeathEvent event) {
        if (event.getEntity().getType() == EntityType.ENDERMAN &&
                event.getEntity().getKiller() != null) {
            Player player = event.getEntity().getKiller();
            String message = "Player killed Enderman: " + player.getDisplayName();
            DrinkEvent.event(DrinkEvent.EventType.ENDERMAN, player, message);
        }
    }
}
