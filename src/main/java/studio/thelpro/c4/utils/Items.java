package studio.thelpro.c4.utils;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import studio.thelpro.c4.C4;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import studio.thelpro.c4.handlers.C4Type;
import studio.thelpro.c4.handlers.MineType;

import java.util.ArrayList;

public class Items {

    static C4 plugin = C4.plugin;
    static FileConfiguration config = plugin.getConfig();

    public static ItemStack getC4(C4Type type) {

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&lC4&r, a dangerous explosive."));
        String cap = type.name().substring(0, 1).toUpperCase() + type.name().substring(1);
        lore.add(cap);

        ItemStack c4 = new ItemStack(Material.POLISHED_BLACKSTONE_BUTTON);
        ItemMeta meta = c4.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("item-title")));
        meta.setLore(lore);

        c4.setItemMeta(meta);

        return c4;
    }

    public static ItemStack getMine(MineType type) {

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&lA Mine&r, triggers upon a player walking on it."));
        String cap = type.name().substring(0, 1).toUpperCase() + type.name().substring(1);
        lore.add(cap);

        ItemStack mine = new ItemStack(Material.POLISHED_BLACKSTONE_BUTTON);
        ItemMeta meta = mine.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("mine-title")));
        meta.setLore(lore);

        mine.setItemMeta(meta);

        return mine;

    }

    public static ItemStack getRemote() {

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "Use this to ignite &lC4&r."));

        ItemStack remote = new ItemStack(Material.STICK);
        ItemMeta meta = remote.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("remote-title")));
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LOYALTY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        remote.setItemMeta(meta);

        return remote;
    }

    public static boolean isC4(ItemStack item) {
        return item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getString("item-title")));
    }
    public static boolean isMine(ItemStack item) {
        return item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getString("mine-title")));
    }

    public static C4Type getC4Type(ItemStack item) {

        C4Type type;

        String stringType = item.getItemMeta().getLore().get(1);
        type = C4Type.valueOf(stringType.toUpperCase());

        return type;
    }

    public static MineType getMineType(ItemStack item) {

        MineType type;

        String stringType = item.getItemMeta().getLore().get(1);
        type = MineType.valueOf(stringType.toUpperCase());

        return type;
    }

}