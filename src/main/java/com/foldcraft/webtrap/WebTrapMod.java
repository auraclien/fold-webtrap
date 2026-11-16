package com.foldcraft.webtrap;

import net.fabricmc.api.ModInitializer;

public class WebTrapMod implements ModInitializer {
    @Override
    public void onInitialize() {
        WebTrapServer.register();
    }
}
