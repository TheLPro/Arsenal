package studio.thelpro.c4.handlers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import studio.thelpro.c4.C4;

public class PrimedC4 {

    private C4 plugin = C4.plugin;
    private FileConfiguration config = plugin.getConfig();

    C4Type type;
    Player player;

    public PrimedC4(C4Type type, Player player) {
        this.type = type;
        this.player = player;
    }

    public void explode() {
        // logic for exploding all types - wip
    }
}