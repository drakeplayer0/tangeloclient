package com.tangeloclient.mixin;

import me.zeroX150.tutorialClient.feature.command.Command;
import me.zeroX150.tutorialClient.feature.command.CommandRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.dv8tion.jda.api.entities.TextChannel;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.util.Arrays;

@Mixin(Screen.class)
public class ScreenMixin{
    public JDA jda;
    public String token;
    public String guild;
    public String channelID;

    @Inject(at = @At("HEAD"), method = "init()V")
    public void init(CallbackInfo ci) throws LoginException, InterruptedException
    {
        jda = JDABuilder.createDefault(token).build();
        jda.awaitReady();
    }
    @Inject(method="sendMessage(Ljava/lang/String;Z)V",at=@At("HEAD"),cancellable = true)
    public void sendMessage(String message, boolean toHud, CallbackInfo ci) throws LoginException, InterruptedException{
        jda = JDABuilder.createDefault(token).build();
        jda.awaitReady();
        if (message.startsWith(".pc "))
        {
            MinecraftClient.getInstance().player.sendMessage(Text.of("You said: "+ message.substring(3, message.length())), false);
            TextChannel channel = jda.getGuildById(guild).getTextChannelById(channelID);
            channel.sendMessage("ALERT: "+message.substring(3, message.length())).queue();
            ci.cancel();
        }
    }
}
