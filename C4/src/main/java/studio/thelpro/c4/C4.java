package studio.thelpro.c4;

import org.bukkit.plugin.java.JavaPlugin;
import studio.thelpro.c4.commands.AdminCommand;
import studio.thelpro.c4.events.C4PlaceEvent;

public final class C4 extends JavaPlugin {

    public static C4 plugin;

    @Override
    public void onEnable() {

        plugin = this;

        reloadConfig();
        saveConfig();

        if (!getConfig().contains("item-title")) {
            getConfig().set("item-title", "C4");
            saveConfig();
        }
        if (!getConfig().contains("remote-title")) {
            getConfig().set("remote-title", "Remote");
            saveConfig();
        }

        getServer().getPluginManager().registerEvents(new C4PlaceEvent(), this);

        getCommand("c4").setExecutor(new AdminCommand());

    }
}