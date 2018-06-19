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
	private ChatColor color;
	
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
		
		case "aqua":
			color = ChatColor.AQUA;
			return ChatColor.AQUA + "Aqua";
			
		case "black":
			color = ChatColor.BLACK;
			return ChatColor.BLACK + "Black";
			
		case "blue":
			color = ChatColor.BLUE;
			return ChatColor.BLUE + "Blue";
			
		case "dark_aqua":
			color = ChatColor.DARK_AQUA;
			return ChatColor.DARK_AQUA + "Dark Aqua";
			
		case "dark_blue":
			color = ChatColor.DARK_BLUE;
			return ChatColor.DARK_BLUE + "Dark Blue";
			
		case "dark_gray":
			color = ChatColor.DARK_GRAY;
			return ChatColor.DARK_GRAY + "Dark Gray";
			
		case "dark_green":
			color = ChatColor.DARK_GREEN;
			return ChatColor.DARK_GREEN + "Dark Green";
			
		case "dark_purple":
			color = ChatColor.DARK_PURPLE;
			return ChatColor.DARK_PURPLE + "Dark Purple";
			
		case "dark_red":
			color = ChatColor.DARK_RED;
			return ChatColor.DARK_RED + "Dark Red";
			
		case "gold":
			color = ChatColor.GOLD;
			return ChatColor.GOLD + "Gold";
			
		case "gray":
			color = ChatColor.GRAY;
			return ChatColor.GRAY + "Gray";
			
		case "green":
			color = ChatColor.GREEN;
			return ChatColor.GREEN + "Green";
			
		case "light_purple":
			color = ChatColor.LIGHT_PURPLE;
			return ChatColor.LIGHT_PURPLE + "Light Purple";
			
		case "red":
			color = ChatColor.RED;
			return ChatColor.RED + "Red";
			
		case "white":
			color = ChatColor.WHITE;
			return ChatColor.WHITE + "White";
			
		case "yellow":
			color = ChatColor.YELLOW;
			return ChatColor.YELLOW + "Yellow";
		
		default:
			return color + "the same color, because invalid color";
		}
	}
	
	public ChatColor getColor() {
		return color;
	}
	
	public String getColorToString() {
		switch (color) {
		
		case AQUA:
			return "aqua";
			
		case BLACK:
			return "black";
			
		case BLUE:
			return"blue";
			
		case DARK_AQUA:
			return "dark_aqua";
			
		case DARK_BLUE:
			return "dark_blue";
			
		case DARK_GRAY:
			return "dark_gray";
			
		case DARK_GREEN:
			return "dark_green";
			
		case DARK_PURPLE:
			return "dark_purple";
			
		case DARK_RED:
			return "dark_red";
			
		case GOLD:
			return "gold";
			
		case GRAY:
			return "gray";
			
		case GREEN:
			return "green";
			
		case LIGHT_PURPLE:
			return "light_purple";
			
		case RED:
			return "red";
			
		case WHITE:
			return "white";
			
		case YELLOW:
			return "yellow";
		
		default:
			return "white";
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
