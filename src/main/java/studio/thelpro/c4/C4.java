package studio.thelpro.c4;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import studio.thelpro.c4.commands.MainCommand;
import studio.thelpro.c4.events.C4PlaceEvent;
import studio.thelpro.c4.events.MinePlaceEvent;
import studio.thelpro.c4.events.PlaceBlockEvent;
import studio.thelpro.c4.events.PlayerWalkEvent;
import studio.thelpro.c4.handlers.C4Type;
import studio.thelpro.c4.handlers.MineType;
import studio.thelpro.c4.recipies.RecipeBuilder;
import studio.thelpro.c4.utils.Items;

public final class C4 extends JavaPlugin {

    public static C4 plugin;

    @Override
    public void onEnable() {

        plugin = this;

        reloadConfig();
        saveConfig();

        if (!getConfig().contains("item-title")) {
            getConfig().set("item-title", "&lC4");
            saveConfig();
        }
        if (!getConfig().contains("remote-title")) {
            getConfig().set("remote-title", "&lRemote");
            saveConfig();
        }
        if (!getConfig().contains("mine-title")) {
            getConfig().set("mine-title", "&lMine");
            saveConfig();
        }

        getServer().getPluginManager().registerEvents(new C4PlaceEvent(), this);
        getServer().getPluginManager().registerEvents(new MinePlaceEvent(), this);
        getServer().getPluginManager().registerEvents(new PlaceBlockEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerWalkEvent(), this);

        getCommand("c4").setExecutor(new MainCommand());

        //RECIPES
        //C4

        ShapedRecipe c4n = new RecipeBuilder(Items.getC4(C4Type.NORMAL), "c4normal").getRecipe();
        c4n.shape("   ", " T ", "BEB");
        c4n.setIngredient('T', Material.REDSTONE_TORCH);
        c4n.setIngredient('B', Material.BLACKSTONE);
        c4n.setIngredient('E', Material.TNT);

        ShapedRecipe c4s = new RecipeBuilder(Items.getC4(C4Type.STRONG), "c4strong").getRecipe();
        c4s.shape(" T ", " E ", "BEB");
        c4s.setIngredient('T', Material.REDSTONE_TORCH);
        c4s.setIngredient('B', Material.BLACKSTONE);
        c4s.setIngredient('E', Material.TNT);

        ShapedRecipe c4m = new RecipeBuilder(Items.getC4(C4Type.MIGHTY), "c4mighty").getRecipe();
        c4m.shape(" E ", "TET", "BEB");
        c4m.setIngredient('T', Material.REDSTONE_TORCH);
        c4m.setIngredient('B', Material.BLACKSTONE);
        c4m.setIngredient('E', Material.TNT);

        ShapedRecipe c4d = new RecipeBuilder(Items.getC4(C4Type.DESTRUCTIVE), "c4destructive").getRecipe();
        c4d.shape("TET", "EEE", "BEB");
        c4d.setIngredient('T', Material.REDSTONE_TORCH);
        c4d.setIngredient('B', Material.BLACKSTONE);
        c4d.setIngredient('E', Material.TNT);

        ShapedRecipe c4a = new RecipeBuilder(Items.getC4(C4Type.AIRDROP), "c4airdrop").getRecipe();
        c4a.shape(" C ", " T ", "BEB");
        c4a.setIngredient('T', Material.REDSTONE_TORCH);
        c4a.setIngredient('B', Material.BLACKSTONE);
        c4a.setIngredient('E', Material.TNT);
        c4a.setIngredient('C', Material.WHITE_CARPET);

        ShapedRecipe c4i = new RecipeBuilder(Items.getC4(C4Type.INCENDIARY), "c4incendiary").getRecipe();
        c4i.shape(" F ", " T ", "BEB");
        c4i.setIngredient('T', Material.REDSTONE_TORCH);
        c4i.setIngredient('B', Material.BLACKSTONE);
        c4i.setIngredient('E', Material.TNT);
        c4i.setIngredient('F', Material.FIRE_CHARGE);

        ShapedRecipe c4in = new RecipeBuilder(Items.getC4(C4Type.INSTANT), "c4instant").getRecipe();
        c4in.shape("   ", "T T", "BEB");
        c4in.setIngredient('T', Material.REDSTONE_TORCH);
        c4in.setIngredient('B', Material.BLACKSTONE);
        c4in.setIngredient('E', Material.TNT);

        ShapedRecipe c4f = new RecipeBuilder(Items.getC4(C4Type.FAKE), "c4fake").getRecipe();
        c4f.shape("   ", " S ", "BWB");
        c4f.setIngredient('S', Material.STICK);
        c4f.setIngredient('W', Material.RED_WOOL);
        c4f.setIngredient('B', Material.BLACKSTONE);

        // DETONATOR

        ShapedRecipe det = new RecipeBuilder(Items.getRemote(), "c4remote").getRecipe();
        c4f.shape(" F ", " S ", " R ");
        c4f.setIngredient('R', Material.REDSTONE_BLOCK);
        c4f.setIngredient('S', Material.STICK);
        c4f.setIngredient('F', Material.FLINT_AND_STEEL);

        // MINES

        ShapedRecipe mn = new RecipeBuilder(Items.getMine(MineType.NORMAL), "minenormal").getRecipe();
        mn.shape("   ", " P ", "BEB");
        mn.setIngredient('P', Material.POLISHED_BLACKSTONE_PRESSURE_PLATE);
        mn.setIngredient('E', Material.TNT);
        mn.setIngredient('B', Material.BLACKSTONE);

        ShapedRecipe ms = new RecipeBuilder(Items.getMine(MineType.STRONG), "minestrong").getRecipe();
        ms.shape(" P ", " E ", "BEB");
        ms.setIngredient('P', Material.POLISHED_BLACKSTONE_PRESSURE_PLATE);
        ms.setIngredient('E', Material.TNT);
        ms.setIngredient('B', Material.BLACKSTONE);

        ShapedRecipe mi = new RecipeBuilder(Items.getMine(MineType.INCENDIARY), "mineincendiary").getRecipe();
        mi.shape(" F ", " P ", "BEB");
        mi.setIngredient('P', Material.POLISHED_BLACKSTONE_PRESSURE_PLATE);
        mi.setIngredient('E', Material.TNT);
        mi.setIngredient('B', Material.BLACKSTONE);
        mi.setIngredient('F', Material.FIRE_CHARGE);

        ShapedRecipe min = new RecipeBuilder(Items.getMine(MineType.INCENDIARY), "mineinvisible").getRecipe();
        min.shape("GGG", "GPG", "GEG");
        min.setIngredient('G', Material.GLASS);
        min.setIngredient('E', Material.TNT);
        min.setIngredient('P', Material.POLISHED_BLACKSTONE_PRESSURE_PLATE);

        // Adding recipes

        Bukkit.addRecipe(c4n);
        Bukkit.addRecipe(c4s);
        Bukkit.addRecipe(c4m);
        Bukkit.addRecipe(c4d);
        Bukkit.addRecipe(c4a);
        Bukkit.addRecipe(c4i);
        Bukkit.addRecipe(c4in);
        Bukkit.addRecipe(c4f);

        Bukkit.addRecipe(det);

        Bukkit.addRecipe(mn);
        Bukkit.addRecipe(ms);
        Bukkit.addRecipe(mi);
        Bukkit.addRecipe(min);

    }
}