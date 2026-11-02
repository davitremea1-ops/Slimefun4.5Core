package io.github.bakedlibs.dough.skins;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.net.URL;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mojang.authlib.GameProfile;

class TestCustomGameProfile {

    @Test
    void wrapperDoesNotExtendFinalAuthlibGameProfile() {
        assertFalse(GameProfile.class.isAssignableFrom(CustomGameProfile.class));
    }

    @Test
    void constructorRetainsSkinMetadataWithoutCreatingAuthlibProfile() throws Exception {
        UUID id = UUID.randomUUID();
        String texture = "dGVzdC10ZXh0dXJl";
        URL skinUrl = new URL("https://textures.minecraft.net/texture/test");

        CustomGameProfile profile = new CustomGameProfile(id, texture, skinUrl);

        assertEquals(id, profile.getId());
        assertEquals(texture, profile.getBase64Texture());
        assertEquals(skinUrl, profile.getSkinUrl());
    }
}
