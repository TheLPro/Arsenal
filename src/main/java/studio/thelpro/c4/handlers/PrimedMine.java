package studio.thelpro.c4.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import studio.thelpro.c4.C4;
import studio.thelpro.c4.utils.Items;

import java.util.UUID;

public class PrimedMine {

    Player player;
    MineType type;
    private C4 plugin = C4.plugin;
    private FileConfiguration config = plugin.getConfig();

    public PrimedMine(MineType type, Player player) {
        this.player = player;
        this.type = type;
    }

    public void explode() {
        String uuid = player.getUniqueId().toString();

        Location loc = config.getLocation(uuid + "/mine");
        config.set(uuid + "/mine", null);
        config.set(uuid + "/mine/type", null);
        plugin.saveConfig();
        TNTPrimed tnt = (TNTPrimed) loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
        tnt.setSource(Bukkit.getEntity(UUID.fromString(uuid)));
        switch (this.type) {
            case NORMAL:
                tnt.setFuseTicks(0);
                break;
            case STRONG:
                tnt.setYield(tnt.getYield() * 2);
                tnt.setFuseTicks(0);
                break;
            case INCENDIARY:
                tnt.setIsIncendiary(true);
                tnt.setFuseTicks(0);
            case INVISIBLE:
                tnt.setFuseTicks(0);
        }
    }

    public void giveItem() {
        player.getInventory().addItem(Items.getMine(type));
    }
}