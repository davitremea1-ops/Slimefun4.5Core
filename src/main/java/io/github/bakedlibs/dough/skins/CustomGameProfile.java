package io.github.bakedlibs.dough.skins;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import com.mojang.authlib.GameProfile;

import io.github.bakedlibs.dough.reflection.ReflectionUtils;
import io.github.bakedlibs.dough.versions.MinecraftVersion;
import io.github.bakedlibs.dough.versions.UnknownServerVersionException;

/**
 * Authlib-compatible replacement for Dough's historical CustomGameProfile.
 *
 * <p>On modern Paper, skin application uses Bukkit's public PlayerProfile API
 * and does not instantiate or subclass Authlib GameProfile. A legacy profile
 * is created lazily only for older server adapters.</p>
 */
public final class CustomGameProfile {

    private static final String PLAYER_NAME = "CS-CoreLib";

    private final UUID profileId;
    private final URL skinUrl;
    private final String texture;

    CustomGameProfile(@Nonnull UUID uuid, @Nullable String texture, @Nonnull URL url) {
        this.profileId = uuid;
        this.skinUrl = url;
        this.texture = texture;
    }

    @Nonnull
    UUID getId() {
        return profileId;
    }

    @Nonnull
    URL getSkinUrl() {
        return skinUrl;
    }

    /**
     * Legacy adapter access. This method is not used on Paper/Purpur 1.20+.
     */
    @Nonnull
    GameProfile asGameProfile() {
        return createLegacyGameProfile();
    }

    /**
     * Applies this texture using Bukkit's stable PlayerProfile API on modern servers.
     */
    void apply(@Nonnull SkullMeta meta)
            throws NoSuchFieldException, IllegalAccessException, UnknownServerVersionException {
        if (MinecraftVersion.get().isAtLeast(MinecraftVersion.parse("1.20"))) {
            PlayerProfile playerProfile = Bukkit.createPlayerProfile(profileId, PLAYER_NAME);
            PlayerTextures playerTextures = playerProfile.getTextures();
            playerTextures.setSkin(skinUrl);
            playerProfile.setTextures(playerTextures);
            meta.setOwnerProfile(playerProfile);
            return;
        }

        GameProfile legacyProfile = createLegacyGameProfile();
        // Force SkullMeta to serialize the profile, then restore the exact texture data.
        ReflectionUtils.setFieldValue(meta, "profile", legacyProfile);
        meta.setOwningPlayer(meta.getOwningPlayer());
        ReflectionUtils.setFieldValue(meta, "profile", legacyProfile);
    }

    @Nullable
    public String getBase64Texture() {
        return texture;
    }

    /**
     * The old Authlib 2-argument constructor and mutable PropertyMap are used only
     * when running below 1.20. The modern runtime path never calls this method.
     */
    private GameProfile createLegacyGameProfile() {
        try {
            Constructor<GameProfile> constructor =
                    GameProfile.class.getConstructor(UUID.class, String.class);
            GameProfile profile = constructor.newInstance(profileId, PLAYER_NAME);

            if (texture != null) {
                Method accessor = GameProfile.class.getMethod("getProperties");
                Object propertyMap = accessor.invoke(profile);
                Class<?> propertyClass = Class.forName("com.mojang.authlib.properties.Property");
                Object property = propertyClass.getConstructor(String.class, String.class)
                        .newInstance("textures", texture);

                Method put = null;
                for (Method method : propertyMap.getClass().getMethods()) {
                    if ("put".equals(method.getName()) && method.getParameterCount() == 2) {
                        put = method;
                        break;
                    }
                }
                if (put == null) {
                    throw new IllegalStateException("Legacy Authlib PropertyMap does not expose put(key, value)");
                }
                put.invoke(propertyMap, "textures", property);
            }
            return profile;
        } catch (ReflectiveOperationException error) {
            throw new IllegalStateException(
                    "Could not create a legacy Authlib GameProfile; modern Paper uses Bukkit PlayerProfile instead.",
                    error);
        }
    }
}
