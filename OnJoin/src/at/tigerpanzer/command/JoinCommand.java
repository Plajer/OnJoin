package at.tigerpanzer.command;

import at.tigerpanzer.Main;
import at.tigerpanzer.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("onjoin")) {
                if((player.hasPermission("OnJoin.config")) || (player.hasPermission("OnJoin.*"))) {
                    if(args.length == 0) {
                        player.sendMessage(Utils.color(Main.getInstance().getConfig().getString("Prefix") + "!!Derzeit bewirkt dieser Command noch nichts!!"));
                    }
                    if(args[1].equalsIgnoreCase("translation")) {
                        if(args[2].equalsIgnoreCase("de"))
                            player.sendMessage(Utils.color(Main.getInstance().getConfig().getString("Prefix") + Main.getInstance().getConfig().getString("outconfigcreate")));
                        if(args[2].equalsIgnoreCase("en"))
                            player.sendMessage(Utils.color(Main.getInstance().getConfig().getString("Prefix") + Main.getInstance().getConfig().getString("outconfigcreate")));
                    if(args[1].equalsIgnoreCase("reload")) {
                        Main.getInstance().reloadConfig();
                    }
                    } else {
                        player.sendMessage(Utils.color(Main.getInstance().getConfig().getString("Prefix") + "&7Bitte benutze &e/onjoin!"));
                    }
                } else {
                    player.sendMessage(Utils.color(Main.getInstance().getConfig().getString("Prefix") + (Main.getInstance().getConfig().getString("Permissionfail"))));
                }
            }
            return true;
        }
        return false;
    }
}