package studio.thelpro.c4.recipies;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import studio.thelpro.c4.C4;

public class RecipeBuilder {

    private C4 plugin = C4.plugin;
    private FileConfiguration config = plugin.getConfig();


    ItemStack result;
    String key;

    public RecipeBuilder(ItemStack result, String key) {
        this.result = result;
        this.key = key;
    }

    public ShapedRecipe getRecipe() {
        return new ShapedRecipe(new NamespacedKey(plugin, key), result);
    }
}