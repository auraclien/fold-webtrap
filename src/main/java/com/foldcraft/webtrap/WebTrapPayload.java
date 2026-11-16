package com.foldcraft.webtrap;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record WebTrapPayload() implements CustomPayload {
    public static final Id<WebTrapPayload> ID =
        new Id<>(Identifier.of("webtrap", "place"));
    public static final PacketCodec<RegistryByteBuf, WebTrapPayload> CODEC =
        PacketCodec.unit(new WebTrapPayload());

    @Override
    public Id<? extends CustomPayload> getId() { return ID; }
}
