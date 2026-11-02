package io.github.thebusybiscuit.slimefun4.api.events;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;

/**
 * Fired immediately before a GEO Miner starts consuming a GEO resource.
 *
 * Addons can cancel this event when the owner of the GEO Miner has not
 * unlocked the technology required for the resource.
 */
public final class GEOMiningStartEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Block block;
    private final GEOResource resource;
    private boolean cancelled;

    public GEOMiningStartEvent(Block block, GEOResource resource) {
        this.block = block;
        this.resource = resource;
    }

    public Block getBlock() {
        return block;
    }

    public GEOResource getResource() {
        return resource;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
