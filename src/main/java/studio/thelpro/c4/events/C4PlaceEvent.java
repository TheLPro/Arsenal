package studio.thelpro.c4.events;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import studio.thelpro.c4.C4;
import studio.thelpro.c4.handlers.C4Type;
import studio.thelpro.c4.handlers.PrimedC4;
import studio.thelpro.c4.utils.Items;

import java.util.UUID;

public class C4PlaceEvent implements Listener {

    static C4 plugin = C4.plugin;
    static FileConfiguration config = plugin.getConfig();

    @EventHandler
    public void placeEvent(PlayerInteractEvent e) throws InterruptedException {

        Player player = e.getPlayer();
        String uuid = player.getUniqueId().toString();

        if (e.getItem() != null && Items.isC4(e.getItem())) {
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (config.get(uuid) != null) {
                    player.sendMessage(ChatColor.RED + "You already placed a C4.");
                    e.setCancelled(true);
                    return;
                }
                player.sendMessage("You placed down a C4, use the remote to ignite it.");

                C4Type type = Items.getC4Type(e.getItem());
                config.set(uuid, e.getClickedBlock().getLocation().add(0, 1, 0));
                config.set(uuid + "/type", type);
                plugin.saveConfig();
            }
        } else if (e.getItem() != null && e.getItem().equals(Items.getRemote())) {
                if (config.get(uuid) != null) {
                    e.setCancelled(true);
                    PrimedC4 primedC4 = new PrimedC4((C4Type) config.get(uuid + "/type"), player);
                    primedC4.explode();
                } else {
                    e.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "You need to place a C4 first!");
            }
        }
    }
}