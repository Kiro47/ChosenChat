package com.chosen.www.chat.events;

import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import com.chosen.www.chat.ChatChannel;
import com.chosen.www.chat.ConfigManager;
import com.chosen.www.chat.MainChat;
import com.chosen.www.chat.commands.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class EventClass implements Listener {

	Plugin plugin;
	ConfigManager cfManager;
	Commands commands;
	
	public EventClass( Plugin mainPlugin ) {
		plugin = mainPlugin;
		cfManager = ((MainChat) mainPlugin).cfgm;
		commands = ((MainChat)mainPlugin).commands;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		String playerUUID = player.getUniqueId().toString().replace("-", "");
		
		
		if ( cfManager.get("players.yml", playerUUID) == null) {
			cfManager.set("players.yml", playerUUID + ".username", player.getName());
			cfManager.set("players.yml", playerUUID + ".activeChannel", "General");
			
			plugin.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "new player " + player.getName() + " joined");
			event.setJoinMessage(ChatColor.LIGHT_PURPLE + "Welcome " + player.getName() + " to the server!");
		} else {
			//if the player has joined before but changed their username
			cfManager.set("players.yml", playerUUID + ".username", player.getName());
		}
		
	}
	
	public void onChat(AsyncPlayerChatEvent event) {
		
		Player player = event.getPlayer();
		String playerUUID = player.getUniqueId().toString().replace("-", "");
		
		String activeChannel = cfManager.get("players.yml", playerUUID + ".activeChannel");
		char channelChar = activeChannel.charAt(0);
		ChatChannel channel = commands.getChannel(activeChannel);
		ChatColor color = channel.getColor();
		
		/*
		 * NEED TO ADD SUPPORT FOR PRIVATE AND LOCAL CHANNELS HERE
		 * BASED ON CHANNEL PRIVACY AND LOCALNESS
		 */
		event.getRecipients().remove(o) //remove people who can't see the message
		event.setFormat(color + "[" + channelChar + "] " + ChatColor.WHITE + player.getDisplayName() + ChatColor.GRAY + ": " + color + event.getMessage() );
		
	}
	
}
