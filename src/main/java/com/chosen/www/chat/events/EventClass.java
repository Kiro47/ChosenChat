package com.chosen.www.chat.events;

import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import com.chosen.www.chat.ChatChannel;
import com.chosen.www.chat.ConfigManager;
import com.chosen.www.chat.MainChat;
import com.chosen.www.chat.commands.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
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
	public void onJoin( PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		String joinMessage = playerJoined(player);
		
		if ( joinMessage != null) {
			event.setJoinMessage(joinMessage);
		}
	}
	
	public String playerJoined( Player player) {
		
		String playerUUID = player.getUniqueId().toString().replace("-", "");
		
		if ( cfManager.get("players.yml", playerUUID) == null) {
			cfManager.set("players.yml", playerUUID + ".username", player.getName());
			cfManager.set("players.yml", playerUUID + ".activeChannel", "General");
			commands.getChannel("General").join(player);
			
			plugin.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "new player " + player.getName() + " joined");
			return ChatColor.LIGHT_PURPLE + "Welcome " + player.getName() + " to the server!";
		} else {
			//if the player has joined before but changed their username
			cfManager.set("players.yml", playerUUID + ".username", player.getName());
			return null;
		}
	}
	
	@EventHandler
	public void onQuit( PlayerQuitEvent event ) {
		
		Player player = event.getPlayer();
		
		commands.swapChannel(player, "General");
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		
		Player player = event.getPlayer();
		String playerUUID = player.getUniqueId().toString().replace("-", "");
		
		String activeChannel = cfManager.get("players.yml", playerUUID + ".activeChannel");
		char channelChar = activeChannel.charAt(0);
		ChatChannel channel = commands.getChannel(activeChannel);
		String channelColor = channel.getColor();
		
		/*
		 * NEED TO ADD SUPPORT FOR PRIVATE AND LOCAL CHANNELS HERE
		 * BASED ON CHANNEL PRIVACY AND LOCALNESS
		 */
		//remove recipients if channel is private
		if ( channel.isPrivate() ) {
			for ( Player p : event.getRecipients() ) {
				if ( !channel.getPlayers().contains(p) ) {
					event.getRecipients().remove(p);
				}
			}
		}
		//remove recipients outside of range if channel is local
		if ( channel.isLocal() ) {
			Location mouth = player.getLocation();
			
			for ( Player p : event.getRecipients() ) {
				Location ear = p.getLocation();
				if ( mouth.distance(ear) > 100 ) {
					event.getRecipients().remove(p);
				}
			}
		}
		//channel character
		String message = channelColor + "[" + channelChar + "] " 
				//player name
				+ "&f" + player.getDisplayName() + "&7: " 
				//message
				+ channelColor + event.getMessage();
		
		event.setFormat(ChatColor.translateAlternateColorCodes('&', message));
	}
	
}
