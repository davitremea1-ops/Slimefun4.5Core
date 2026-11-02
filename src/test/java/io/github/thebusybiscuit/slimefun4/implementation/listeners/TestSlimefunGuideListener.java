package io.github.thebusybiscuit.slimefun4.implementation.listeners;

import java.util.List;
import java.util.stream.Stream;

import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.block.Action;
import org.bukkit.block.BlockFace;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.test.TestUtilities;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;

class TestSlimefunGuideListener {

    private static Slimefun plugin;
    private static ServerMock server;

    @BeforeAll
    public static void load() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(Slimefun.class);
    }

    @AfterAll
    public static void unload() {
        MockBukkit.unmock();
    }

    @ParameterizedTest
    @DisplayName("Test Slimefun Guides being given on first join")
    @MethodSource("cartesianBooleans")
    void testFirstJoin(boolean hasPlayedBefore, boolean giveSlimefunGuide) {
        SlimefunGuideListener listener = new SlimefunGuideListener(plugin, giveSlimefunGuide);
        PlayerMock player = new PlayerMock(server, "CanIHazGuide");

        if (hasPlayedBefore) {
            server.getPlayerList().setLastSeen(player.getUniqueId(), System.currentTimeMillis());
        }

        PlayerJoinEvent event = new PlayerJoinEvent(player, "CanIHazGuide has joined and wants sum guide");
        listener.onJoin(event);

        ItemStack guide = SlimefunGuide.getItem(SlimefunGuide.getDefaultMode());
        Assertions.assertEquals(!hasPlayedBefore && giveSlimefunGuide, hasSlimefunGuide(player, guide));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Guide identity survives changes to display name and lore")
    void testGuideIdentityUsesPersistentMarker() {
        for (SlimefunGuideMode mode : SlimefunGuideMode.values()) {
            ItemStack guide = SlimefunGuide.getItem(mode).clone();
            ItemMeta meta = guide.getItemMeta();
            meta.setDisplayName("A customized book");
            meta.setLore(List.of("Custom lore which no longer matches the original guide"));
            guide.setItemMeta(meta);

            Assertions.assertEquals(mode, SlimefunGuide.getGuideMode(guide));
            Assertions.assertTrue(SlimefunGuide.isGuideItem(guide));
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Right-clicking a customized survival guide opens the guide inventory")
    void testRightClickCustomizedGuideOpensMenu() throws InterruptedException {
        PlayerMock player = server.addPlayer();
        TestUtilities.awaitProfile(player);

        ItemStack guide = SlimefunGuide.getItem(SlimefunGuideMode.SURVIVAL_MODE).clone();
        ItemMeta meta = guide.getItemMeta();
        meta.setDisplayName("Custom guide name");
        meta.setLore(List.of("The original lore was changed"));
        guide.setItemMeta(meta);

        PlayerInteractEvent interact = new PlayerInteractEvent(
                player, Action.RIGHT_CLICK_AIR, guide, null, BlockFace.UP, EquipmentSlot.HAND
        );
        PlayerRightClickEvent rightClick = new PlayerRightClickEvent(interact);
        new SlimefunGuideListener(plugin, false).onInteract(rightClick);

        Assertions.assertEquals(Result.DENY, rightClick.useItem());
        Assertions.assertEquals(54, player.getOpenInventory().getTopInventory().getSize());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Legacy guide books without a marker remain recognizable")
    void testLegacyGuideCompatibility() {
        ItemStack guide = SlimefunGuide.getItem(SlimefunGuideMode.SURVIVAL_MODE).clone();
        ItemMeta meta = guide.getItemMeta();
        meta.getPersistentDataContainer().remove(Slimefun.getRegistry().getGuideDataKey());
        guide.setItemMeta(meta);

        Assertions.assertEquals(SlimefunGuideMode.SURVIVAL_MODE, SlimefunGuide.getGuideMode(guide));
    }

    private boolean hasSlimefunGuide(Player player, ItemStack guide) {
        return SlimefunUtils.isItemSimilar(player.getInventory().getItem(0), guide, true);
    }

    /**
     * This returns an {@link Arguments} {@link Stream} of boolean combinations.
     * It performs a cartesian product on two boolean sets.
     * 
     * @return a {@link Stream} of {@link Arguments}
     */
    private static Stream<Arguments> cartesianBooleans() {
        Stream<Boolean> stream = Stream.of(true, false);
        return stream.flatMap(a -> Stream.of(true, false).map(b -> Arguments.of(a, b)));
    }

}
