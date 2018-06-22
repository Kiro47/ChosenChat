package com.chosen.www.chat;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatChannel {
	
	ArrayList<Player> playerList = new ArrayList<Player>();
	String channelName;
	boolean permanent;
	boolean local;
	boolean locked;
	private String color;
	
	public ChatChannel( String name, boolean permanent, boolean local, boolean locked, String color ) {
		channelName = name;
		this.permanent = permanent;
		this.local = local;
		this.locked = locked;
		setColor(color);
	}
	
	public ArrayList<Player> getPlayers() {
		return playerList;
	}
	
	public int size() {
		return playerList.size();
	}
	
	public void join( Player player ) {
		playerList.add(player);
	}
	
	public void leave( Player player ) {
		playerList.remove(player);
	}
	
	public String setColor( String col ) {
		
		switch (col.toLowerCase()) {
		
		case "&b":
			color = "&b";
			return ChatColor.AQUA + "Aqua";
			
		case "&0":
			color = "&0";
			return ChatColor.BLACK + "Black";
			
		case "&9":
			color = "&9";
			return ChatColor.BLUE + "Blue";
			
		case "&3":
			color = "&3";
			return ChatColor.DARK_AQUA + "Dark Aqua";
			
		case "&1":
			color = "&1";
			return ChatColor.DARK_BLUE + "Dark Blue";
			
		case "&8":
			color = "&8";
			return ChatColor.DARK_GRAY + "Dark Gray";
			
		case "&2":
			color = "&2";
			return ChatColor.DARK_GREEN + "Dark Green";
			
		case "&5":
			color = "&5";
			return ChatColor.DARK_PURPLE + "Dark Purple";
			
		case "&4":
			color = "&4";
			return ChatColor.DARK_RED + "Dark Red";
			
		case "&6":
			color = "&6";
			return ChatColor.GOLD + "Gold";
			
		case "&7":
			color = "&7";
			return ChatColor.GRAY + "Gray";
			
		case "&a":
			color = "&a";
			return ChatColor.GREEN + "Green";
			
		case "&d":
			color = "&d";
			return ChatColor.LIGHT_PURPLE + "Light Purple";
			
		case "&c":
			color = "&c";
			return ChatColor.RED + "Red";
			
		case "&f":
			color = "&f";
			return ChatColor.WHITE + "White";
			
		case "&e":
			color = "&e";
			return ChatColor.YELLOW + "Yellow";
			
		case "&k":
			return ChatColor.MAGIC + "magic";
			
		case "&l":
			return ChatColor.BOLD + "bold";
			
		case "&m":
			return ChatColor.STRIKETHROUGH + "strikethrough";
		
		case "&n":
			return ChatColor.UNDERLINE + "underline";
			
		case "&o":
			return ChatColor.ITALIC + "italic";
			
		case "&r":
			return ChatColor.RESET + "reset";
		
		default:
			return color + "the same color, because invalid color";
		}
	}
	
	public String getColor() {
		return color;
	}
	
	public String getColorToString() {
		switch (color) {
		
		case "&b":
			return "aqua";
			
		case "&0":
			return "black";
			
		case "&9":
			return"blue";
			
		case "&3":
			return "dark_aqua";
			
		case "&1":
			return "dark_blue";
			
		case "&8":
			return "dark_gray";
			
		case "&2":
			return "dark_green";
			
		case "&5":
			return "dark_purple";
			
		case "&4":
			return "dark_red";
			
		case "&6":
			return "gold";
			
		case "&7":
			return "gray";
			
		case "&a":
			return "green";
			
		case "&d":
			return "light_purple";
			
		case "&c":
			return "red";
			
		case "&f":
			return "white";
			
		case "&e":
			return "yellow";
			
		case "&k":
			return "magic";
			
		case "&l":
			return "bold";
			
		case "&m":
			return "strikethrough";
		
		case "&n":
			return "underline";
			
		case "&o":
			return "italic";
			
		case "&r":
			return "reset";
			
		default:
			return "error";
		}
	}
	
	public String getName() {
		return channelName;
	}
	
	public void setName( String name ) {
		channelName = name;
	}
	
	public boolean isPermanent() {
		return permanent;
	}
	
	public void setPermanent( String value ) {
		permanent = Boolean.valueOf(value);
	}
	
	public boolean isLocal() {
		return local;
	}
	
	public void setLocal( String value ) {
		local = Boolean.valueOf(value);
	}
	
	public boolean isPrivate() {
		return locked;
	}
	
	public void setPrivate( String value ) {
		locked = Boolean.valueOf(value);
	}
	
}
