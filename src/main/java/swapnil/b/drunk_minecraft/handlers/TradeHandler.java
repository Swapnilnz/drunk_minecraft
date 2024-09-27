package swapnil.b.drunk_minecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import swapnil.b.drunk_minecraft.DrinkEvent;
import swapnil.b.drunk_minecraft.Drunk_minecraft;

public class TradeHandler implements Listener {
    public TradeHandler(Drunk_minecraft plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onPlayerTrade(TradeSelectEvent event) {
        Player player = (Player) event.getWhoClicked();
        String message = "Player traded: " + player.getDisplayName();
        DrinkEvent.event(DrinkEvent.EventType.TRADE, player, message);
    }
}
