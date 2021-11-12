package me.lordmefloun.holoislands.objects;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Modules.Holograms.CMIHologram;
import me.lordmefloun.holoislands.HoloIslands;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.database.objects.Island;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class IslandHologram {

    Island island;
    CMIHologram holo;
    static HashSet<IslandHologram> instances = new HashSet<>();


    public IslandHologram(Island island){
        this.island = island;
        instances.add(this);
        this.holo = new CMIHologram("" + island.getName(), this.island.getCenter().add(
                HoloIslands.plugin.getConfig().getDouble("hologram-adjust.x"),
                HoloIslands.plugin.getConfig().getDouble("hologram-adjust.y"),
                HoloIslands.plugin.getConfig().getDouble("hologram-adjust.z")
        ));
        this.setLines();
        CMI.getInstance().getHologramManager().addHologram(holo);
        holo.update();
    }

    public void setLines(){
        List<String> list = HoloIslands.plugin.getConfig().getStringList("lines");
        int a = 0;
        for (String s: list) {
            list.set(a, s.replace("{player_name}", Bukkit.getOfflinePlayer(this.island.getOwner()).getName()));
            a++;
        }
        this.holo.setLines(list);
    }

    public static IslandHologram getHoloByIsland(Island island){
        for (IslandHologram holo : instances){
            if (holo.island.equals(island))
            return holo;
        }
        return null;
    }

    public void remove(){
        instances.remove(this);
        this.holo.remove();
    }

    public static void loadAllObjects(){
        int a= 0;
        for (Island island : BentoBox.getInstance().getIslands().getIslands()){
            a++;
            IslandHologram isholo = new IslandHologram(island);
        }
        Bukkit.broadcast(new TextComponent("[IslandHolograms] Loaded " + a  + " holograms"));
    }


}
