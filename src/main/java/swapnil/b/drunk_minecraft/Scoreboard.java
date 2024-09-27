package swapnil.b.drunk_minecraft;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;

import static org.bukkit.Bukkit.getServer;

public class Scoreboard implements Listener {
    private final Map<UUID, FastBoard> boards = new HashMap<>();
    public static final Map<UUID, Map<DrinkEvent.DrinkType, Integer>> DRINK_SCORES = new HashMap<>();


    public Scoreboard(Drunk_minecraft plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);

        getServer().getScheduler().runTaskTimer(plugin, () -> {
            for (FastBoard board : this.boards.values()) {
                updateBoard(board);
            }
        }, 0, 20);
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        FastBoard board = new FastBoard(player);
        board.updateTitle(ChatColor.GOLD + "Player        Glugs | Chugs");
        this.boards.put(player.getUniqueId(), board);
        if (!DRINK_SCORES.containsKey(player.getUniqueId())) {
            DRINK_SCORES.put(player.getUniqueId(), new HashMap<>() {{
                put(DrinkEvent.DrinkType.GLUG, 0);
                put(DrinkEvent.DrinkType.CHUG, 0);
            }});
        }
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        FastBoard board = this.boards.remove(player.getUniqueId());
        if (board != null) {
            board.delete();
        }
    }

    private void updateBoard(FastBoard board) {

        List<String> lines = new ArrayList<>();
        List<String> scores = new ArrayList<>();
        for (Player player : getServer().getOnlinePlayers()) {
            lines.add(player.getDisplayName());
            scores.add(DRINK_SCORES.get(player.getUniqueId()).get(DrinkEvent.DrinkType.GLUG) + " | " + DRINK_SCORES.get(player.getUniqueId()).get(DrinkEvent.DrinkType.CHUG));
        }
        board.updateLines(lines);
        board.updateScores(scores);
    }
}
