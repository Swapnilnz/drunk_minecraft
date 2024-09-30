package swapnil.b.drunk_minecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import swapnil.b.drunk_minecraft.DrinkEvent;
import swapnil.b.drunk_minecraft.Drunk_minecraft;

public class AchievementHandler implements Listener {
    public AchievementHandler(Drunk_minecraft plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerAchievement(PlayerAdvancementDoneEvent event) {
        Player player = event.getPlayer();
        String message = "Player Achievement: " + player.getDisplayName();
        if (event.getAdvancement().getDisplay() != null) {
            DrinkEvent.event(DrinkEvent.EventType.ACHIEVEMENT, player, message);
        } else {
            System.out.println("[test] null display");
        }
    }
}
