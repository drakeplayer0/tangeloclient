package com.tangeloclient.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method="<init>",at=@At("TAIL"))
    public void onMinecraftCreate(RunArgs args, CallbackInfo ci) {
        System.out.println("Minecraft was started!");
    }
}
