package studio.thelpro.c4.events;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import studio.thelpro.c4.C4;
import studio.thelpro.c4.handlers.MineType;
import studio.thelpro.c4.utils.Items;

public class MinePlaceEvent implements Listener {

    static C4 plugin = C4.plugin;
    static FileConfiguration config = plugin.getConfig();

    @EventHandler
    public void placeEvent(PlayerInteractEvent e) {
        if (e.getItem() != null && Items.isMine(e.getItem())) {
            Player player = e.getPlayer();
            String uuid = player.getUniqueId().toString();
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (config.get(uuid) != null) {
                    player.sendMessage(ChatColor.RED + "You already placed a mine.");
                    e.setCancelled(true);
                    return;
                }
                player.sendMessage("You placed down a mine, wait for a player to walk on it.");

                MineType type = Items.getMineType(e.getItem());
                config.set(uuid + "/mine", e.getClickedBlock().getLocation().add(0, 1, 0));
                config.set(uuid + "/mine/type", type);
                if (type.equals(MineType.INVISIBLE)) {
                    config.set(uuid + "/mine/invisible-placed", false);
                }
                plugin.saveConfig();
            }
        }
    }
}