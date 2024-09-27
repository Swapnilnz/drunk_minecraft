package swapnil.b.drunk_minecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import swapnil.b.drunk_minecraft.DrinkEvent;
import swapnil.b.drunk_minecraft.Drunk_minecraft;

public class EatsHandler implements Listener {
    public EatsHandler(Drunk_minecraft plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onPlayerEats(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        String message = "Player ate or drank something (tsk tsk): " + player.getDisplayName();
        DrinkEvent.event(DrinkEvent.EventType.CONSUME, player, message);
    }
}
