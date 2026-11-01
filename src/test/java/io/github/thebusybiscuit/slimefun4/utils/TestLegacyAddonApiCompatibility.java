package io.github.thebusybiscuit.slimefun4.utils;

import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

/**
 * Guards the binary/source compatibility contract expected by existing Slimefun addons.
 */
class TestLegacyAddonApiCompatibility {

    @Test
    @DisplayName("SlimefunItemStack remains usable anywhere an ItemStack is expected")
    void testSlimefunItemStackRemainsAnItemStack() {
        Assertions.assertTrue(
            ItemStack.class.isAssignableFrom(SlimefunItemStack.class),
            "Older addons pass SlimefunItemStack instances directly to Bukkit APIs that accept ItemStack."
        );
    }

    @Test
    @DisplayName("Legacy SlimefunItemStack constructor remains available")
    void testLegacyConstructorRemainsAvailable() {
        Assertions.assertDoesNotThrow(
            () -> SlimefunItemStack.class.getConstructor(String.class, ItemStack.class)
        );
    }
}
