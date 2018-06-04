package spigot.ChosenChat.stone;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import spigot.ChosenChat.stone.events.EventClass;

public class MainChat extends JavaPlugin {

public ConfigManager cfgm;
	
	public void onEnable() {
		
		loadConfigManager();
		
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "testfile enabled");
		getServer().getPluginManager().registerEvents(new EventClass(this), this);
	}
	
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "testfile disabled");
	}
	
	public void loadConfigManager() {
		
		cfgm = new ConfigManager();
		cfgm.setup();
		
	}
	
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
