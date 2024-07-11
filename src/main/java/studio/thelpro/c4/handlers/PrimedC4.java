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
        String uuid = player.getUniqueId().toString();

        Location loc = config.getLocation(uuid);
        Chunk chunk = loc.getChunk();
        boolean fl = chunk.isForceLoaded();
        chunk.setForceLoaded(true);
        config.set(uuid, null);
        config.set(uuid + "/type", null);
        plugin.saveConfig();
        plugin.saveConfig();
        chunk.setForceLoaded(fl);
        TNTPrimed tnt = (TNTPrimed) loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
        tnt.setSource(Bukkit.getEntity(UUID.fromString(uuid)));
        switch (this.type) {
            case NORMAL:
                tnt.setFuseTicks(20);
                break;
            case STRONG:
                tnt.setYield(tnt.getYield() * 2);
                tnt.setFuseTicks(20);
                break;
            case MIGHTY:
                tnt.setYield(tnt.getYield() * 3);
                tnt.setFuseTicks(20);
                break;
            case DESTRUCTIVE:
                tnt.setYield(tnt.getYield() * 5);
                tnt.setFuseTicks(20);
                break;
            case AIRDROP:
                loc.setY(loc.getWorld().getHighestBlockYAt((int) loc.getX(), (int) loc.getZ()) + 10);
                tnt.teleport(loc);
                tnt.setFuseTicks(30);
                break;
            case INCENDIARY:
                tnt.setIsIncendiary(true);
                tnt.setFuseTicks(20);
                break;
            case INSTANT:
                tnt.setFuseTicks(0);
                break;
            case FAKE:
                Thread t1 = new Thread(() -> {
                    try {
                        tnt.setFuseTicks(30);
                        Thread.sleep(1000);
                        tnt.remove();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                t1.start();
                break;
        }
    }

    public void giveItem() {
        player.getInventory().addItem(Items.getC4(type));
    }

}