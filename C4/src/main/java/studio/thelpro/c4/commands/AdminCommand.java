package studio.thelpro.c4.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import studio.thelpro.c4.C4;
import studio.thelpro.c4.utils.Items;

public class AdminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = (Player) commandSender;

        if (strings[0].equalsIgnoreCase("1")) {
            player.getInventory().addItem(Items.getC4());
        }
        if (strings[0].equalsIgnoreCase("2")) {
            player.getInventory().addItem(Items.getRemote());
        }

        return true;
    }
}