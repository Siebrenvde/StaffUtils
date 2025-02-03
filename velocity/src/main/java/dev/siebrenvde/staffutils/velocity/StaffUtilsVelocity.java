package dev.siebrenvde.staffutils.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.siebrenvde.staffutils.StaffUtils;
import org.bstats.velocity.Metrics;
import org.slf4j.Logger;

import java.nio.file.Path;

public class StaffUtilsVelocity {

    private final StaffUtils staffUtils;
    private final Metrics.Factory metricsFactory;

    private static StaffUtilsVelocity instance;
    private static ProxyServer proxy;

    @Inject
    public StaffUtilsVelocity(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory, Metrics.Factory metricsFactory) {
        this.metricsFactory = metricsFactory;
        instance = this;
        proxy = server;
        staffUtils = new StaffUtils(
            dataDirectory,
            new VelocityPlatform(),
            new VelocityGlobalServer(),
            new VelocityLogger(logger)
        );
    }

    @Subscribe
    public void onProxyInitialise(ProxyInitializeEvent event) {
        metricsFactory.make(this, 24628);
        staffUtils.load();
        staffUtils.registerCommands(new VelocityCommandManager(proxy.getCommandManager()));
        proxy.getEventManager().register(this, new VelocityEventListeners());
    }

    public static StaffUtilsVelocity getInstance() { return instance; }
    public static ProxyServer getProxy() { return proxy; }

}
