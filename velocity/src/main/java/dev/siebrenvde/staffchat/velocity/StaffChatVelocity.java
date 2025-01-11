package dev.siebrenvde.staffchat.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.siebrenvde.staffchat.StaffChat;
import org.slf4j.Logger;

import java.nio.file.Path;

public class StaffChatVelocity {

    private final StaffChat staffChat;

    private static StaffChatVelocity instance;
    private static ProxyServer proxy;

    @Inject
    public StaffChatVelocity(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        instance = this;
        proxy = server;
        staffChat = new StaffChat(
            dataDirectory,
            new VelocityPlatform(),
            new VelocityGlobalServer(),
            new VelocityLogger(logger)
        );
    }

    @Subscribe
    public void onProxyInitialise(ProxyInitializeEvent event) {
        staffChat.registerCommands(new VelocityCommandManager(proxy.getCommandManager()));
        proxy.getEventManager().register(this, new VelocityEventListeners());
    }

    public static StaffChatVelocity getInstance() { return instance; }
    public static ProxyServer getProxy() { return proxy; }

}
