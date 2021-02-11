package com.github.beastyboo.warz.application;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class WarZ {

    private final JavaPlugin plugin;
    private final Logger logger;

    private WarZAPI api;

    WarZ(JavaPlugin plugin) {
        this.plugin = plugin;
        logger = plugin.getLogger();
    }

    void start() {
        logger.info("WarZ Mechanics start up");
        api = new WarZAPI(this);
        api.start();
    }

    void close() {
        logger.info("WarZ Mechanics shutdown");
        api.close();
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Logger getLogger() {
        return logger;
    }

    public WarZAPI getAPI() {
        return api;
    }
}
