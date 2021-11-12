package me.lordmefloun.holoislands;
import me.lordmefloun.holoislands.events.IslandCreate;
import me.lordmefloun.holoislands.objects.IslandHologram;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import world.bentobox.bentobox.BentoBox;

public final class HoloIslands extends JavaPlugin {

    public static HoloIslands plugin;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        plugin = this;
        BentoBox.getInstance().getAddonsManager().registerListener(BentoBox.getInstance().getAddonsManager().getAddonByName("AOneBlock").get() , new IslandCreate());
        new BukkitRunnable() {
            @Override
            public void run() {
                IslandHologram.loadAllObjects();
            }
        }.runTaskLater(this, 10*30);
    }

}