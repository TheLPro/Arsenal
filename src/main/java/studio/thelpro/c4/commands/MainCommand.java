package studio.thelpro.c4.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import studio.thelpro.c4.handlers.C4Type;
import studio.thelpro.c4.handlers.MineType;
import studio.thelpro.c4.handlers.PrimedC4;
import studio.thelpro.c4.handlers.PrimedMine;
import studio.thelpro.c4.utils.Items;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MainCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        Player player = (Player) commandSender;

        if (args.length == 0) {
            player.sendMessage("Plugin made by " + ChatColor.BOLD + "" + ChatColor.GOLD + "TheLPro" + ChatColor.RESET + ". ");
            return true;
        }
        if (args[0].equalsIgnoreCase("c4")) {
            switch (args[1]) {
                default:
                    player.sendMessage(ChatColor.RED + "Please provide correct arguments.");
                    break;
                case "normal":
                    new PrimedC4(C4Type.NORMAL, player).giveItem();
                    break;
                case "extra":
                    new PrimedC4(C4Type.STRONG, player).giveItem();
                    break;
                case "extraplus":
                    new PrimedC4(C4Type.MIGHTY, player).giveItem();
                    break;
                case "extreme":
                    new PrimedC4(C4Type.DESTRUCTIVE, player).giveItem();
                    break;
                case "airdrop":
                    new PrimedC4(C4Type.AIRDROP, player).giveItem();
                    break;
                case "flame":
                    new PrimedC4(C4Type.INCENDIARY, player).giveItem();
                    break;
                case "instant":
                    new PrimedC4(C4Type.INSTANT, player).giveItem();
                    break;
                case "fake":
                    new PrimedC4(C4Type.FAKE, player).giveItem();
                    break;
                case "*":
                    new PrimedC4(C4Type.NORMAL, player).giveItem();
                    new PrimedC4(C4Type.STRONG, player).giveItem();
                    new PrimedC4(C4Type.MIGHTY, player).giveItem();
                    new PrimedC4(C4Type.DESTRUCTIVE, player).giveItem();
                    new PrimedC4(C4Type.AIRDROP, player).giveItem();
                    new PrimedC4(C4Type.INCENDIARY, player).giveItem();
                    new PrimedC4(C4Type.INSTANT, player).giveItem();
                    new PrimedC4(C4Type.FAKE, player).giveItem();
                    break;
            }
            return true;
        } else if (args[0].equalsIgnoreCase("remote")) {
            player.getInventory().addItem(Items.getRemote());
            return true;
        } else if (args[0].equalsIgnoreCase("mine")) {
            switch (args[1]) {
                default:
                    player.sendMessage(ChatColor.RED + "Please provide correct arguments.");
                    break;
                case "normal":
                    new PrimedMine(MineType.NORMAL, player).giveItem();
                    break;
                case "strong":
                    new PrimedMine(MineType.STRONG, player).giveItem();
                    break;
                case "incendiary":
                    new PrimedMine(MineType.INCENDIARY, player).giveItem();
                    break;
                case "invisible":
                    new PrimedMine(MineType.INVISIBLE, player).giveItem();
                    break;
                case "*":
                    new PrimedMine(MineType.NORMAL, player).giveItem();
                    new PrimedMine(MineType.STRONG, player).giveItem();
                    new PrimedMine(MineType.INCENDIARY, player).giveItem();
                    new PrimedMine(MineType.INVISIBLE, player).giveItem();
                    break;
            }
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "Please provide correct arguments.");
        }

        return true;
    }

    List<String> arguments = new ArrayList<String>();
    List<String> c4s = new ArrayList<String>();
    List<String> mines = new ArrayList<String>();

    @Override
    public @Nullable List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("c4.admin")) {

            if (arguments.isEmpty()) {
                arguments.add("c4");
                arguments.add("mine");
                arguments.add("remote");
            }
            if (c4s.isEmpty()) {
                c4s.add("*");
                c4s.add("normal");
                c4s.add("strong");
                c4s.add("mighty");
                c4s.add("destructive");
                c4s.add("airdrop");
                c4s.add("incendiary");
                c4s.add("instant");
                c4s.add("fake");
            }
            if (mines.isEmpty()) {
                mines.add("*");
                mines.add("normal");
                mines.add("strong");
                mines.add("incendiary");
                mines.add("invisible");
            }

            List<String> result = new ArrayList<String>();
            if (args.length == 1) {
                for (String a : arguments) {
                    if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                        result.add(a);
                }
                return result;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("c4")) {
                for (String a : c4s) {
                    if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                        result.add(a);
                }
                return result;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("mine")) {
                for (String a : mines) {
                    if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                        result.add(a);
                }
                return result;
            }
        }
        return null;
    }
}