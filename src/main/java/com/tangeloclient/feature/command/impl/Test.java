package me.zeroX150.tutorialClient.feature.command.impl;

import me.zeroX150.tutorialClient.feature.command.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class Test extends Command {
    public Test() {
        super("Test", "Amogus", "test","sus","amog","stuff");
    }

    @Override
    public void execute(String[] args) {
        MinecraftClient.getInstance().player.sendMessage(Text.of("amog"),false);
    }
}
