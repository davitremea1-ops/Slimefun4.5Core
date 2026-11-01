package io.github.thebusybiscuit.slimefun4.api.exceptions;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;

/**
 * Thrown when code attempts to modify an immutable original Slimefun item stack
 * instead of a working copy.
 *
 * @see SlimefunItemStack
 * @see SlimefunItem
 */
public class WrongItemStackException extends RuntimeException {

    private static final long serialVersionUID = 9144658137363309071L;

    @ParametersAreNonnullByDefault
    public WrongItemStackException(String message) {
        super("You probably wanted to alter a different ItemStack: " + message);
    }
}
