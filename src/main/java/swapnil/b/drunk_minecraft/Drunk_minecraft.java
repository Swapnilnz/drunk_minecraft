package swapnil.b.drunk_minecraft;

import org.bukkit.plugin.java.JavaPlugin;
import swapnil.b.drunk_minecraft.handlers.*;


public final class Drunk_minecraft extends JavaPlugin {

    @Override
    public void onEnable() {
        new Scoreboard(this);

        new DeathHandler(this);
        new AchievementHandler(this);
        new BlockBreakHandler(this);
        new EnterEndHandler(this);
        new EntityKillHandler(this);
        new TameAnimalHandler(this);
        new TradeHandler(this);
        new InWaterHandler(this);
        new ToolBreakEvent(this);
        new EatsHandler(this);
        new SetOnFireHandler(this);
        new DamageHandler(this);
        new SleepHandler(this);
        new RaidHandler(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
