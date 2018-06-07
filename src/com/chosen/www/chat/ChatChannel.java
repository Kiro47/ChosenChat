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
		
		switch (col) {
		
		case "lightblue":
			color = ChatColor.AQUA;
			return ChatColor.AQUA + "Light Blue";
			
		case "black":
			color = ChatColor.BLACK;
			return ChatColor.BLACK + "Black";
			
		case "blue":
			color = ChatColor.BLUE;
			return ChatColor.BLUE + "Blue";
			
		case "cyan":
			color = ChatColor.DARK_AQUA;
			return ChatColor.DARK_AQUA + "Cyan";
			
		case "darkblue":
			color = ChatColor.DARK_BLUE;
			return ChatColor.DARK_BLUE + "Dark Blue";
			
		case "darkgray":
			color = ChatColor.DARK_GRAY;
			return ChatColor.DARK_GRAY + "Dark Gray";
			
		case "darkgreen":
			color = ChatColor.DARK_GREEN;
			return ChatColor.GREEN + "Dark Green";
			
		case "purple":
			color = ChatColor.DARK_PURPLE;
			return ChatColor.DARK_PURPLE + "Purple";
			
		case "darkred":
			color = ChatColor.DARK_RED;
			return ChatColor.DARK_RED + "Dark Red";
			
		case "orange":
			color = ChatColor.GOLD;
			return ChatColor.GOLD + "Orange";
			
		case "gray":
			color = ChatColor.GRAY;
			return ChatColor.GRAY + "Gray";
			
		case "green":
			color = ChatColor.GREEN;
			return ChatColor.GREEN + "Green";
			
		case "magenta":
			color = ChatColor.LIGHT_PURPLE;
			return ChatColor.LIGHT_PURPLE + "Magenta";
			
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
			color = ChatColor.WHITE;
			return ChatColor.WHITE + "White, because invalid color";
		}
	}
	
	public ChatColor getColor() {
		return color;
	}
	
	public String getColorToString() {
		switch (color) {
		
		case AQUA:
			return "lightblue";
			
		case BLACK:
			return "black";
			
		case BLUE:
			return"blue";
			
		case DARK_AQUA:
			return "cyan";
			
		case DARK_BLUE:
			return "darkblue";
			
		case DARK_GRAY:
			return "darkgray";
			
		case DARK_GREEN:
			return "darkgreen";
			
		case DARK_PURPLE:
			return "purple";
			
		case DARK_RED:
			return "darkred";
			
		case GOLD:
			return "orange";
			
		case GRAY:
			return "gray";
			
		case GREEN:
			return "green";
			
		case LIGHT_PURPLE:
			return "magenta";
			
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
