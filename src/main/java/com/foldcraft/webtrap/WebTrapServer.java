package com.foldcraft.webtrap;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class WebTrapServer {
    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(WebTrapPayload.ID,
            (payload, context) -> {
                ServerPlayerEntity player = context.player();
                context.server().execute(() -> {
                    int slot = -1;
                    for (int i = 0; i < 9; i++) {
                        if (player.getInventory().getStack(i).getItem() == Items.COBWEB) {
                            slot = i; break;
                        }
                    }
                    if (slot == -1) return;

                    BlockPos target = player.getBlockPos();
                    if (player.getWorld().getBlockState(target).isAir()
                        || player.getWorld().getBlockState(target).isReplaceable()) {
                        player.getWorld().setBlockState(target, Blocks.COBWEB.getDefaultState());
                        player.getInventory().getStack(slot).decrement(1);
                    }
                });
            });
    }
}
