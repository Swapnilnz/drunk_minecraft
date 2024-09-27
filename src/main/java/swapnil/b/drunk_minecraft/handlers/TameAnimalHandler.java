package swapnil.b.drunk_minecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;
import swapnil.b.drunk_minecraft.DrinkEvent;
import swapnil.b.drunk_minecraft.Drunk_minecraft;

public class TameAnimalHandler implements Listener {
    public TameAnimalHandler(Drunk_minecraft plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerTameAnimal(EntityTameEvent event) {
        if (event.getOwner() instanceof Player player) {
            String message = "Player tamed " + event.getEntity().getType() + ": " + player.getDisplayName();
            DrinkEvent.event(DrinkEvent.EventType.TAME, player, message);
        }
    }
}
