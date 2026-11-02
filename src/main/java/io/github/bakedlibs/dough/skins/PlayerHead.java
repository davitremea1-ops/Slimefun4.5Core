package io.github.bakedlibs.dough.skins;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import com.mojang.authlib.GameProfile;

import io.github.bakedlibs.dough.skins.nms.PlayerHeadAdapter;
import io.github.bakedlibs.dough.versions.MinecraftVersion;
import io.github.bakedlibs.dough.versions.UnknownServerVersionException;

/**
 * Local override that cooperates with the composition-based CustomGameProfile.
 */
public final class PlayerHead {

    private PlayerHead() {}

    public static @Nonnull ItemStack getItemStack(@Nonnull OfflinePlayer player) {
        Validate.notNull(player, "The player can not be null!");
        return getItemStack(meta -> meta.setOwningPlayer(player));
    }

    public static @Nonnull ItemStack getItemStack(@Nonnull PlayerSkin skin) {
        Validate.notNull(skin, "The skin can not be null!");
        return getItemStack(meta -> {
            try {
                skin.getProfile().apply(meta);
            } catch (NoSuchFieldException | IllegalAccessException | UnknownServerVersionException error) {
                throw new IllegalStateException("Could not apply the requested player skin", error);
            }
        });
    }

    private static @Nonnull ItemStack getItemStack(@Nonnull Consumer<SkullMeta> consumer) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        consumer.accept(meta);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Applies a custom skin to a placed skull. Modern servers use Bukkit's public
     * PlayerProfile API, avoiding the Authlib/NMS classes that changed in 1.21.10+.
     * The legacy Dough adapter remains available for older server versions.
     */
    @ParametersAreNonnullByDefault
    public static void setSkin(Block block, PlayerSkin skin, boolean sendBlockUpdate) {
        Validate.notNull(block, "The block cannot be null!");
        Validate.notNull(skin, "The skin cannot be null!");

        Material material = block.getType();
        if (material != Material.PLAYER_HEAD && material != Material.PLAYER_WALL_HEAD) {
            throw new IllegalArgumentException(
                    "Cannot update a head texture. Expected a Player Head, received: " + material);
        }

        if (MinecraftVersion.get().isAtLeast(MinecraftVersion.parse("1.20"))) {
            BlockState state = block.getState();
            if (!(state instanceof Skull skull)) {
                throw new IllegalStateException("The target block did not provide Skull state");
            }

            PlayerProfile profile = Bukkit.createPlayerProfile(
                    skin.getProfile().getId(), "CS-CoreLib");
            PlayerTextures textures = profile.getTextures();
            textures.setSkin(skin.getProfile().getSkinUrl());
            profile.setTextures(textures);
            skull.setOwnerProfile(profile);

            // Bukkit's public BlockState API updates the skull without running physics.
            // It does not expose Dough's old sendBlockUpdate toggle.
            skull.update(true, false);
            return;
        }

        PlayerHeadAdapter adapter = PlayerHeadAdapter.get();
        if (adapter == null) {
            throw new UnsupportedOperationException("Cannot update skin texture, no adapter found");
        }

        try {
            GameProfile profile = skin.getProfile().asGameProfile();
            adapter.setGameProfile(block, profile, sendBlockUpdate);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException error) {
            throw new IllegalStateException("Could not update the player head texture", error);
        }
    }
}
