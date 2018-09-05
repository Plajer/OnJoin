package at.tigerpanzer.command;

import at.tigerpanzer.Main;
import at.tigerpanzer.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;


public class JoinCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("onjoin")) {
                if((player.hasPermission("OnJoin.config")) || (player.hasPermission("OnJoin.*"))) {
                    if(args.length == 0) {
                        List<String> HelpText = Main.getInstance().getConfig().getStringList("Help.HelpText");
                        for(String msg : HelpText) {
                            player.sendMessage(Utils.setPlaceholders(player, msg));
                        }
                        return true;
                    } else if(args.length == 2) {
                        if(args[0].equalsIgnoreCase("translate")) {
                            if(args[1].equalsIgnoreCase("de")) {
                                //player.sendMessage(Utils.color(Main.getInstance().getConfig().getString("Prefix") + Main.getInstance().getConfig().getString("Help.OutConfigCreate")));
                                player.sendMessage(Utils.color(Main.getInstance().getConfig().getString("Prefix") + "-- MAYBE NEXT UPDATE --"));
                            } else if(args[1].equalsIgnoreCase("en")) {
                                //player.sendMessage(Utils.color(Main.getInstance().getConfig().getString("Prefix") + Main.getInstance().getConfig().getString("Help.OutConfigCreate")));
                                player.sendMessage(Utils.color(Main.getInstance().getConfig().getString("Prefix") + "-- MAYBE NEXT UPDATE --"));
                            }
                        }
                    } else if(args.length == 1) {

                        if(args[0].equalsIgnoreCase("reloadconfig")) {
                            Main.getInstance().reloadConfig();
                            player.sendMessage(Utils.setPlaceholders(player, Main.getInstance().getConfig().getString("Prefix") + Main.getInstance().getConfig().getString("Help.OutConfigLoad")));
                        }
                        if(args[0].equalsIgnoreCase("setspawn")) {
                            Main.getInstance().getConfig().set("SpawnLocation.World", player.getLocation().getWorld().getName());
                            Main.getInstance().getConfig().set("SpawnLocation.XCoord", player.getLocation().getX());
                            Main.getInstance().getConfig().set("SpawnLocation.YCoord", player.getLocation().getY());
                            Main.getInstance().getConfig().set("SpawnLocation.ZCoord", player.getLocation().getZ());
                            Main.getInstance().getConfig().set("SpawnLocation.Yaw", player.getLocation().getYaw());
                            Main.getInstance().getConfig().set("SpawnLocation.Pitch", player.getLocation().getPitch());
                            Main.getInstance().getConfig().set("SpawnLocation.SpawnLocationEnable", true);
                            player.sendMessage(Main.getInstance().getConfig().getString("SpawnLocation.SetSpawnMessageSetTo") + player.getLocation().getWorld().getName() + ", " + player.getLocation().getBlockX() + ", " + player.getLocation().getBlockY() + ", " + player.getLocation().getBlockZ());
                            player.sendMessage(Main.getInstance().getConfig().getString("SpawnLocation.SetSpawnMessageYaw") + player.getLocation().getYaw());
                            player.sendMessage(Main.getInstance().getConfig().getString("SpawnLocation.SetSpawnMessagePitch") + player.getLocation().getPitch());
                            Main.getInstance().saveConfig();
                        }

                    } else {
                        player.sendMessage(Utils.setPlaceholders(player, Main.getInstance().getConfig().getString("Prefix") + "Use /onjoin"));
                        return true;
                    }
                } else {
                    player.sendMessage(Utils.setPlaceholders(player, Main.getInstance().getConfig().getString("Permissionfail")));
                    return true;
                }
            } else {
                player.sendMessage(Utils.setPlaceholders(player, Main.getInstance().getConfig().getString("Prefix") + "Use /onjoin"));
                return true;
            }
        } else {
            System.out.println(Utils.color(Main.getInstance().getConfig().getString("NoPlayer")));
        }
        return false;
    }
}