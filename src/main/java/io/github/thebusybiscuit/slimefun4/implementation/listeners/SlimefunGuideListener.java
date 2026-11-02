package io.github.thebusybiscuit.slimefun4.implementation.listeners;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.events.SlimefunGuideOpenEvent;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.core.guide.options.SlimefunGuideSettings;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;

public class SlimefunGuideListener implements Listener {

    private final boolean giveOnFirstJoin;

    public SlimefunGuideListener(@Nonnull Slimefun plugin, boolean giveOnFirstJoin) {
        this.giveOnFirstJoin = giveOnFirstJoin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (giveOnFirstJoin && !e.getPlayer().hasPlayedBefore()) {
            Player p = e.getPlayer();

            if (!Slimefun.getWorldSettingsService().isWorldEnabled(p.getWorld())) {
                return;
            }

            SlimefunGuideMode type = SlimefunGuide.getDefaultMode();
            p.getInventory().addItem(SlimefunGuide.getItem(type).clone());
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInteract(PlayerRightClickEvent e) {
        Player p = e.getPlayer();
        SlimefunGuideMode mode = SlimefunGuide.getGuideMode(e.getItem());

        if (mode == null) {
            return;
        }

        if (!Slimefun.getWorldSettingsService().isWorldEnabled(p.getWorld())) {
            Slimefun.getLocalization().sendMessage(p, "messages.disabled-item", true);
            e.cancel();
            return;
        }

        if (p.isSneaking()) {
            e.cancel();
            SlimefunGuideSettings.openSettings(p, e.getItem());
            return;
        }

        if (mode == SlimefunGuideMode.CHEAT_MODE) {
            /*
             * Permission checks are handled by the command itself.
             * Cancel the book interaction so Minecraft does not use the item.
             */
            e.cancel();
            p.chat("/sf cheat");
            return;
        }

        openGuide(p, e, mode);
    }

    @ParametersAreNonnullByDefault
    private void openGuide(Player p, PlayerRightClickEvent e, SlimefunGuideMode layout) {
        SlimefunGuideOpenEvent event = new SlimefunGuideOpenEvent(p, e.getItem(), layout);
        Bukkit.getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            e.cancel();
            SlimefunGuide.openGuide(p, event.getGuideLayout());
        }
    }

}
