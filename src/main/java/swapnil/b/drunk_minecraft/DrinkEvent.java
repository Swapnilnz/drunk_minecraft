package swapnil.b.drunk_minecraft;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;


import static org.bukkit.Bukkit.getServer;
import static swapnil.b.drunk_minecraft.Scoreboard.DRINK_SCORES;

public class DrinkEvent {
    private static String prefix = "";

    static {
        prefix += ChatColor.GOLD + "\uD83C\uDF79" + ChatColor.RESET;
        prefix += ChatColor.RED + "[" + ChatColor.RESET;
        prefix += ChatColor.YELLOW + "D" + ChatColor.RESET;
        prefix += ChatColor.GREEN + "r" + ChatColor.RESET;
        prefix += ChatColor.AQUA + "u" + ChatColor.RESET;
        prefix += ChatColor.BLUE + "n" + ChatColor.RESET;
        prefix += ChatColor.DARK_PURPLE + "k" + ChatColor.RESET;
        prefix += " ";
        prefix += ChatColor.GREEN + "M" + ChatColor.RESET;
        prefix += ChatColor.GRAY + "C" + ChatColor.RESET;
        prefix += ChatColor.RED + "]" + ChatColor.RESET;
        prefix += ChatColor.GOLD + "\uD83C\uDF79" + ChatColor.RESET;

    }

    public enum EventType {
        ACHIEVEMENT,
        DIAMOND,
        ENDERMAN,
        TAME,
        TRADE,
        WATER,
        TOOL,
        CONSUME,
        END,
        FIRE,
        POISON,
        DEATH,
        SLEEP,
        CREEPER,
        LIGHTENING,
        RAID,
        RAID_SUCCESS
    }

    public enum DrinkType {
        GLUG,
        CHUG,
        OTHER
    }

    private record DrinkEventInfo(String message, DrinkType drinkType) {}
    private static final Map<EventType, DrinkEventInfo> drinkMap = Map.copyOf(Map.ofEntries(
        Map.entry(EventType.ACHIEVEMENT, new DrinkEventInfo("Everyone but you drink!", DrinkType.OTHER)),
        Map.entry(EventType.DIAMOND, new DrinkEventInfo("Everyone but you drink!", DrinkType.OTHER)),
        Map.entry(EventType.ENDERMAN, new DrinkEventInfo("Everyone but you drink!", DrinkType.OTHER)),
        Map.entry(EventType.TAME, new DrinkEventInfo("Everyone but you drink!", DrinkType.OTHER)),
        Map.entry(EventType.TRADE, new DrinkEventInfo("Take a big gulp!", DrinkType.GLUG)),
        Map.entry(EventType.WATER, new DrinkEventInfo("Take a big gulp!", DrinkType.GLUG)),
        Map.entry(EventType.CONSUME, new DrinkEventInfo("Take a big gulp!", DrinkType.GLUG)),
        Map.entry(EventType.TOOL, new DrinkEventInfo("Take a big gulp!", DrinkType.GLUG)),
        Map.entry(EventType.END, new DrinkEventInfo("Take a big gulp!", DrinkType.GLUG)),
        Map.entry(EventType.FIRE, new DrinkEventInfo("Take a big gulp!", DrinkType.GLUG)),
        Map.entry(EventType.POISON, new DrinkEventInfo("Take a big gulp!", DrinkType.GLUG)),
        Map.entry(EventType.CREEPER, new DrinkEventInfo("Finish your drink!", DrinkType.GLUG)),
        Map.entry(EventType.DEATH, new DrinkEventInfo("Finish your drink!", DrinkType.CHUG)),
        Map.entry(EventType.SLEEP, new DrinkEventInfo("Finish your drink!", DrinkType.CHUG)),
        Map.entry(EventType.LIGHTENING, new DrinkEventInfo("Finish your drink!", DrinkType.CHUG)),
        Map.entry(EventType.RAID, new DrinkEventInfo("Finish your drink!", DrinkType.CHUG)),
        Map.entry(EventType.RAID_SUCCESS, new DrinkEventInfo("Finish your drink!", DrinkType.CHUG))
    ));


    public static void event(EventType type, Player player, String msg) {

        int paddingPadding = 4;
        Bukkit.broadcastMessage("");

        String title = "-[DRUNK MC]-";
        int totalWidth = Math.max(Math.max(title.length(), msg.length()), drinkMap.get(type).message.length());
        Bukkit.broadcastMessage("-".repeat(totalWidth + paddingPadding * 2 - 5));

        String prefixUpperCase = prefix.toUpperCase();
        int padding = (totalWidth - title.length()) / 2 + paddingPadding;
        String centeredText = String.format("%" + padding + "s%s%" + padding + "s", "", prefixUpperCase, "");
        Bukkit.broadcastMessage(centeredText);

        int padding2 = (totalWidth - msg.length()) / 2 + paddingPadding;
        String centeredText2 = String.format("%" + padding2 + "s%s%" + padding2 + "s", "", msg, "");
        Bukkit.broadcastMessage(centeredText2);

        int padding3 = (totalWidth - drinkMap.get(type).message.length()) / 2 + paddingPadding;
        String centeredText3 = String.format("%" + (padding3 - 1) + "s%s%" + padding3 + "s", "", "§l" + drinkMap.get(type).message, "");
        Bukkit.broadcastMessage(centeredText3);

        Bukkit.broadcastMessage("-".repeat(totalWidth + paddingPadding * 2 - 5));
        Bukkit.broadcastMessage("");

        DrinkType drinkType = drinkMap.get(type).drinkType;

        switch (drinkType) {
            case GLUG:
                player.sendTitle(ChatColor.GOLD + "§lHave a Glug", msg, 10, 40, 10);
                DRINK_SCORES.get(player.getUniqueId()).put(drinkType, DRINK_SCORES.get(player.getUniqueId()).get(drinkType) + 1);
                break;
            case CHUG:
                player.sendTitle(ChatColor.GOLD + "§lHave a Chug", msg, 10, 40, 10);
                DRINK_SCORES.get(player.getUniqueId()).put(drinkType, DRINK_SCORES.get(player.getUniqueId()).get(drinkType) + 1);
                break;
            case OTHER:
                for (Player otherPlayer : getServer().getOnlinePlayers()) {
                    if (otherPlayer != player) {
                        otherPlayer.sendTitle(ChatColor.GOLD + "§lHave a Glug", msg, 10, 40, 10);
                        DRINK_SCORES.get(otherPlayer.getUniqueId())
                                .put(DrinkType.GLUG,
                                        DRINK_SCORES.get(otherPlayer.getUniqueId()).get(DrinkType.GLUG) + 1);
                    }
                }
                player.sendTitle("", ChatColor.GREEN + drinkMap.get(type).message , 10, 40, 10);
                break;
        }
    }

}

