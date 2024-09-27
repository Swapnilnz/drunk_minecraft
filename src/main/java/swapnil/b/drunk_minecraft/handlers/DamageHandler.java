package swapnil.b.drunk_minecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import swapnil.b.drunk_minecraft.DrinkEvent;
import swapnil.b.drunk_minecraft.Drunk_minecraft;

public class DamageHandler implements Listener {
    public DamageHandler(Drunk_minecraft plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onPlayerTrade(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player player) {
            Entity damager = event.getDamager();
            if (damager.getType().equals(EntityType.CREEPER)) {
                String message = "Player got blown up by a creeper: " + player.getDisplayName();
                DrinkEvent.event(DrinkEvent.EventType.CREEPER, player, message);
            }
        }
    }
}
