package com.foldcraft.webtrap;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class WebTrapClient implements ClientModInitializer {
    private static KeyBinding key;

    @Override
    public void onInitializeClient() {
        key = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.webtrap.place",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "category.webtrap"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (key.wasPressed()) {
                if (client.player != null && client.world != null) {
                    ClientPlayNetworking.send(new WebTrapPayload());
                }
            }
        });
    }
}
