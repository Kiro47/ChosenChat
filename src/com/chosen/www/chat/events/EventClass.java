package spigot.ChosenChat.stone.events;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.EventHandler;

import spigot.ChosenChat.stone.ConfigManager;
import spigot.ChosenChat.stone.MainChat;

public class EventClass implements Listener {

	Plugin plugin;
	ConfigManager cfManager;
	
	public EventClass( Plugin mainPlugin ) {
		plugin = mainPlugin;
		cfManager = ((MainChat) mainPlugin).cfgm;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		
		
	}
	
}
