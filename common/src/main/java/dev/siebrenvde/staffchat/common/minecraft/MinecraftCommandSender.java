package dev.siebrenvde.staffchat.common.minecraft;

public interface MinecraftCommandSender {

    String getName();

    String getDisplayName();

    String getServerName();

    void sendMessage(String message);

}
