package studio.thelpro.c4.events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import studio.thelpro.c4.C4;
import studio.thelpro.c4.handlers.MineType;
import studio.thelpro.c4.handlers.PrimedMine;

public class PlayerWalkEvent implements Listener {

    static C4 plugin = C4.plugin;
    static FileConfiguration config = plugin.getConfig();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerWalkEvent(PlayerMoveEvent e) {

        Player player = e.getPlayer();
        Location loc = e.getTo();
        String uuid = player.getUniqueId().toString();

        if (config.contains(uuid + "/mine")) {
            Location mineLoc = config.getLocation(uuid + "/mine");

            assert mineLoc != null;
            assert loc != null;

            int x = (int) loc.getX();
            int y = (int) loc.getY();
            int z = (int) loc.getZ();

            int x2 = (int) mineLoc.getX();
            int y2 = (int) mineLoc.getY();
            int z2 = (int) mineLoc.getZ();

            System.out.println(x + " / " + y + " / " + z);
            if (x == x2 && y == y2 && z == z2 && loc.getWorld().equals(mineLoc.getWorld())) {
                System.out.println("onmine");
                if (player.hasPermission("c4.bypassMines") || player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR))
                    return;
                MineType type = MineType.valueOf(config.getString(uuid + "/mine/type"));
                PrimedMine primedMine = new PrimedMine(type, player);
                primedMine.explode();
                System.out.println("done");
            }
        }
    }
}