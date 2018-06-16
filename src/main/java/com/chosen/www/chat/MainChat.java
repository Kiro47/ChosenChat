package com.chosen.www.chat;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.chosen.www.chat.commands.Commands;
import com.chosen.www.chat.events.EventClass;

public class MainChat extends JavaPlugin {

	public ConfigManager cfgm;
	public Commands commands;
	
	public void onEnable() {
		
		loadConfigManager();
		
		commands = new Commands(this);
		this.getCommand("channel").setExecutor(commands);
		
		getServer().getPluginManager().registerEvents(new EventClass(this), this);
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "ChosenChat enabled");
	}
	
	public void onDisable() {
		
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "ChosenChat disabled");
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
