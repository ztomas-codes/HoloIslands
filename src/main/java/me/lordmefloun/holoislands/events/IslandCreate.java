package me.lordmefloun.holoislands.events;

import me.lordmefloun.holoislands.HoloIslands;
import me.lordmefloun.holoislands.objects.IslandHologram;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import world.bentobox.bentobox.api.events.island.IslandCreateEvent;
import world.bentobox.bentobox.api.events.island.IslandDeleteEvent;

public class IslandCreate implements Listener {


    @EventHandler
    public void onIslandCreate(IslandCreateEvent e){
        IslandHologram holo = new IslandHologram(e.getIsland());
    }

    @EventHandler
    public void OnIslandDelete(IslandDeleteEvent e){
        IslandHologram.getHoloByIsland(e.getIsland()).remove();
    }
}
