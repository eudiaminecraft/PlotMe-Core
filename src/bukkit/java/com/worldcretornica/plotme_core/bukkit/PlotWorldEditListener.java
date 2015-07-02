package com.worldcretornica.plotme_core.bukkit;

import com.sk89q.worldedit.event.extent.EditSessionEvent;
import com.sk89q.worldedit.extension.platform.Actor;
import com.sk89q.worldedit.util.eventbus.EventHandler;
import com.sk89q.worldedit.util.eventbus.Subscribe;
import com.worldcretornica.plotme_core.PlotMeCoreManager;
import com.worldcretornica.plotme_core.PlotMe_Core;
import com.worldcretornica.plotme_core.bukkit.api.BukkitWorld;

public class PlotWorldEditListener {

    private final PlotMe_Core api;

    public PlotWorldEditListener(PlotMe_Core api) {
        this.api = api;
    }

    @Subscribe(priority = EventHandler.Priority.VERY_EARLY)
    public void worldEditListener(EditSessionEvent event) {
        Actor actor = event.getActor();
        if (event.getWorld() == null) {
            return;
        }
        BukkitWorld plotmeWorld = null;
        if (event.getWorld() instanceof com.sk89q.worldedit.bukkit.BukkitWorld) {
            plotmeWorld = BukkitUtil.adapt(((com.sk89q.worldedit.bukkit.BukkitWorld) event.getWorld()).getWorld());
        } else if (event.getWorld() instanceof org.primesoft.asyncworldedit.worldedit.world.AsyncWorld) {
            org.primesoft.asyncworldedit.worldedit.world.AsyncWorld world =
                    (org.primesoft.asyncworldedit.worldedit.world.AsyncWorld) event.getWorld();
            plotmeWorld = BukkitUtil.adapt(((com.sk89q.worldedit.bukkit.BukkitWorld) world.getWorld()).getWorld());
        } else {
            return;
        }
        if (PlotMeCoreManager.getInstance().isPlotWorld(plotmeWorld)) {
            if (actor != null && actor.isPlayer()) {
                event.setExtent(new PlotMeWorldEdit(api, event.getExtent(), event.getActor()));
            }
        }
    }
}