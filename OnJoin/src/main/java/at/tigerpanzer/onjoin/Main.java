package at.tigerpanzer.onjoin;

import at.tigerpanzer.onjoin.command.JoinCommand;
import at.tigerpanzer.onjoin.events.JoinExecuteCommand;
import at.tigerpanzer.onjoin.events.JoinFirework;
import at.tigerpanzer.onjoin.events.JoinQuitListener;
import at.tigerpanzer.onjoin.util.SpigotPluginUpdater;
import at.tigerpanzer.onjoin.util.Utils;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.plajerlair.core.services.ServiceRegistry;


public class Main extends JavaPlugin {

    private boolean needUpdateJoin;
    private boolean placeholderAPI;

    @Override
    public void onEnable() {
        ServiceRegistry.registerService(this);
        needUpdateJoin = false;
        saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage(Utils.color(getConfig().getString("Console.PrefixConsole") + " &cWird &aGESTARTET &7| &cis &aSTARTED"));
        register();
        update();
        Bukkit.getConsoleSender().sendMessage(Utils.color(getConfig().getString("Console.PrefixConsole") + " &7=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));
        Bukkit.getConsoleSender().sendMessage(Utils.color(getConfig().getString("Console.PrefixConsole") + " &cPlugin version: &e" + getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(Utils.color(getConfig().getString("Console.PrefixConsole") + " &cPlugin author: &e" + getDescription().getAuthors()));
        Bukkit.getConsoleSender().sendMessage(Utils.color(getConfig().getString("Console.PrefixConsole") + " &cPlugin status: &aaktiviert &c| &aenabled"));
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            Bukkit.getConsoleSender().sendMessage(Utils.color(getConfig().getString("Console.PrefixConsole") + " §a✔ §ePlaceholderAPI §7| §aVersion§7:§e " + PlaceholderAPIPlugin.getInstance().getDescription().getVersion()));
            placeholderAPI = true;
        } else {
            Bukkit.getConsoleSender().sendMessage(Utils.color(getConfig().getString("Console.PrefixConsole") + " §c✖ §4PlaceholderAPI"));
            placeholderAPI = false;
        }
        Bukkit.getConsoleSender().sendMessage(Utils.color(getConfig().getString("Console.PrefixConsole") + " &7=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Utils.color(getConfig().getString("Console.PrefixConsole") + " &7=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));
        Bukkit.getConsoleSender().sendMessage(Utils.color(getConfig().getString("Console.PrefixConsole") + " &cPlugin version: &e" + getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(Utils.color(getConfig().getString("Console.PrefixConsole") + " &cPlugin author: &e" + getDescription().getAuthors()));
        Bukkit.getConsoleSender().sendMessage(Utils.color(getConfig().getString("Console.PrefixConsole") + " &cPlugin status: &4deaktiviert &c| &4disabled"));
        Bukkit.getConsoleSender().sendMessage(Utils.color(getConfig().getString("Console.PrefixConsole") + " &7=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));
    }

    private void update() {
        SpigotPluginUpdater update = new SpigotPluginUpdater(this, "http://tigerpanzer02.bplaced.net/plugins/onjoin/");
        update.enableOut();
        update.needsUpdate();
    }

    private void register() {
        new JoinCommand(this);
        new JoinQuitListener(this);
        new JoinFirework(this);
        new JoinExecuteCommand(this);
    }

    public boolean needUpdateJoin() {
        return needUpdateJoin;
    }

    public void setNeedUpdateJoin(boolean needUpdateJoin) {
        this.needUpdateJoin = needUpdateJoin;
    }

    public boolean isPlaceholderAPIEnabled() {
        return placeholderAPI;
    }
}