package studio.thelpro.c4.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import studio.thelpro.c4.C4;
import studio.thelpro.c4.handlers.MineType;

public class PlaceBlockEvent implements Listener {

    private C4 plugin = C4.plugin;
    private FileConfiguration config = plugin.getConfig();

    @EventHandler
    public void placeBlockEvent(BlockPlaceEvent e) {

        Player player = e.getPlayer();
        String uuid = player.getUniqueId().toString();

        if (config.contains(uuid + "/mine")) {
            Block block = e.getBlockPlaced();
            if (((MineType) config.get(uuid + "/mine/type")).equals(MineType.INVISIBLE)) {
                if (!config.getBoolean(uuid + "/mine/invisible-placed")) {
                    block.setType(Material.AIR);
                    config.set(uuid + "/mine/invisible-placed", true);
                    plugin.saveConfig();
                }
            }
        }
    }
}