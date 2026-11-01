package io.github.thebusybiscuit.slimefun4.utils;

import java.util.Arrays;

import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.exceptions.WrongItemStackException;

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
    @DisplayName("Legacy mutability hooks and exception remain available")
    void testLegacyLockApiRemainsAvailable() {
        Assertions.assertDoesNotThrow(() -> SlimefunItemStack.class.getMethod("lock"));
        Assertions.assertDoesNotThrow(() -> SlimefunItem.class.getDeclaredMethod("isItemStackImmutable"));
        Assertions.assertTrue(RuntimeException.class.isAssignableFrom(WrongItemStackException.class));
    }

    @Test
    @DisplayName("Legacy ItemStack clone method descriptor remains available")
    void testLegacyCloneBridgeRemainsAvailable() {
        Assertions.assertTrue(
            Arrays.stream(SlimefunItemStack.class.getDeclaredMethods())
                .anyMatch(method ->
                    method.getName().equals("clone")
                        && method.getReturnType() == ItemStack.class
                        && method.isBridge()
                ),
            "Existing add-ons can invoke SlimefunItemStack.clone() with the ItemStack return descriptor."
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
