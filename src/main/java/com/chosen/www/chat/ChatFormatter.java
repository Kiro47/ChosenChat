package com.chosen.www.chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatFormatter {

	public String format( Player player, ChatChannel channel, String rank, String message ) {
		
		ChatColor channelColor = channel.getColor();
		char channelChar = channel.getName().charAt(0);
		
		String endMessage = channelColor + "[" + channelChar + "] " + ChatColor.GRAY + "[";
		
		String[] rankPieces = rank.split("&");
		for ( String s : rankPieces ) {
			endMessage += colorFormat(s);
		}
				
		endMessage += ChatColor.GRAY + "] " ;
		
		return endMessage;
	}
	
	private String colorFormat( String message ) {
				
		switch (message.charAt(0)) {
		
		case '0':
			return ChatColor.BLACK + message.substring(1);
		
		case '1':
			return ChatColor.DARK_BLUE + message.substring(1);
			
		case '2':
			return ChatColor.DARK_GREEN + message.substring(1);
			
		case '3':
			return ChatColor.DARK_AQUA + message.substring(1);
			
		case '4':
			return ChatColor.DARK_RED + message.substring(1);
			
		case '5':
			return ChatColor.DARK_PURPLE + message.substring(1);
			
		case '6':
			return ChatColor.GOLD + message.substring(1);
			
		case '7':
			return ChatColor.GRAY + message.substring(1);
		
		case '8':
			return ChatColor.DARK_GRAY + message.substring(1);
		
		case '9':
			return ChatColor.BLUE + message.substring(1);
			
		case 'a':
			return ChatColor.GREEN + message.substring(1);
			
		case 'b':
			return ChatColor.AQUA + message.substring(1);
			
		case 'c':
			return ChatColor.RED + message.substring(1);
			
		case 'd':
			return ChatColor.LIGHT_PURPLE + message.substring(1);
			
		case 'e':
			return ChatColor.YELLOW + message.substring(1);
			
		case 'f':
			return ChatColor.WHITE + message.substring(1);
			
		case 'k':
			return ChatColor.MAGIC + message.substring(1);
			
		case 'l':
			return ChatColor.BOLD + message.substring(1);
			
		case 'm':
			return ChatColor.STRIKETHROUGH + message.substring(1);
			
		case 'n':
			return ChatColor.UNDERLINE + message.substring(1);
			
		case 'o':
			return ChatColor.ITALIC + message.substring(1);
			
		case 'r':
			return ChatColor.RESET + message.substring(1);
			
		default:
			return message;
			
		}

	}
	
}
