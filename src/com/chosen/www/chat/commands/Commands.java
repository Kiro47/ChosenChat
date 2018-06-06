package com.chosen.www.chat.commands;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.chosen.www.chat.ChatChannel;
import com.chosen.www.chat.ConfigManager;
import com.chosen.www.chat.MainChat;
import com.chosen.www.chat.Permissions;

import net.minecraft.server.v1_12_R1.CommandExecute;

public class Commands extends CommandExecute implements Listener,CommandExecutor {

	
	//permission error String
	private String cannotInto = "You don't have permission to use this command!";
	//command list
	public String cmd0 = "channel";
	
	public HashMap<String, ChatChannel> channels = new HashMap<String, ChatChannel>();
	
	Plugin plugin;
	ConfigManager cfManager;
	
	
	public Commands( Plugin mainPlugin ) {
		plugin = mainPlugin;
		cfManager = ((MainChat) mainPlugin).cfgm;
		
		Iterable<String> permanentChannels = cfManager.getConfig("channels.yml").getConfig().getKeys(false);
		//Initial setup of General Global chat if it doesn't exist
		//General chat's settings can be changed but it must exist or bad things happen
		if ( cfManager.get("channels.yml", "General") == null ) {
			ChatChannel general = new ChatChannel("General", true, false, false, "darkgreen" );
			channels.put("General", general);
		}
		
		//NEED TO INITIALIZE PERMANENT CHANNELS HERE
		for ( String channel : permanentChannels ) {
			boolean permanent = true;
			boolean local = cfManager.get("channels.yml", channel + ".local");
			boolean locked = cfManager.get("channels.yml", channel + ".private");
			String color = cfManager.get("channels.yml", channel + ".color");
			
			ChatChannel newChannel = new ChatChannel(channel, permanent, local, locked, color );
			channels.put(channel, newChannel);
		}
	}
	//when the plugin is disabled, save the permanent channels in config
	public void shutDown() {
		for ( ChatChannel c : channels.values() ) {
			if ( c.isPermanent() ) {
				cfManager.set("channels.yml", c.getName() + ".local", c.isLocal());
				cfManager.set("channels.yml", c.getName() + ".private", c.isPrivate());
				cfManager.set("channels.yml", c.getName() + ".color", c.getColorToString());
			}
		}
	}
	
	public ChatChannel getChannel( String channelName ) {
		return channels.get(channelName);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if ( !(sender instanceof Player) ) {
			sender.sendMessage(ChatColor.RED + "Only players can use this command");
		} else {
			
			Player player = (Player) sender;
			String playerUUID = player.getUniqueId().toString().replace("-", "");
			String activeChannel = cfManager.get("players.yml", playerUUID + ".activeChannel");
			
//			if ( !player.hasPermission(Permissions.COMMAND_GENERAL) ) {
//				player.sendMessage(ChatColor.RED + cannotInto);
//			}
		
			if ( command.getName().equalsIgnoreCase(cmd0) ) {
				
				if ( args.length < 1 ) {
					//add a help message
					player.sendMessage("channel help message");
					return true;
				} else {
				
					switch (args[0]) {
				
					case "help":
					
						break;
					
					case "create":
//						if ( !player.hasPermission(Permissions.COMMAND_CREATE_CHANNEL) ) {
//							player.sendMessage(ChatColor.RED + cannotInto);
//							return true;
//						}
					
						if ( args.length < 2 ) {
							//add a help message
							player.sendMessage("channel create help message");
							break;
						}
				
						createChannel(args[1]);
						swapChannel(player, args[1]);
						break;
					
					case "delete":
//						if ( !player.hasPermission(Permissions.COMMAND_DELETE_CHANNEL) ) {
//							player.sendMessage(ChatColor.RED + cannotInto);
//							return true;
//						}
					
						if ( args.length < 2 ) {
							//add a help message
							player.sendMessage("channel delete help message");
							break;
						} else {
							for ( Player p : channels.get(activeChannel).getPlayers() ) {
								swapChannel(p, "General");
							}
							channels.remove(activeChannel);
							break;
						}
					
					case "join":
						if ( args.length < 2 ) {
							//add a help message
							player.sendMessage("channel join help message");
							break;
						} else {
							swapChannel(player, args[1]);
							break;
						}
					
					case "set":
//						if ( !player.hasPermission(Permissions.COMMAND_EDIT_CHANNEL) ) {
//							player.sendMessage(ChatColor.RED + cannotInto);
//							break;
//						}	
					
						if ( args.length < 2 ) {
							//add a help message
							player.sendMessage("channel set help message");
							break;
						} else if ( args.length < 3 ) {
							player.sendMessage(setChannel(activeChannel, args[1], null));
						} else {
							player.sendMessage(setChannel(activeChannel, args[1], args[2]));
						}
						
						break; 
					}
				}
			}
		}
		return true;
	}

	private void createChannel(String channelName) {
		
		ChatChannel newChannel = new ChatChannel(channelName, false, false, false, "white");
		channels.put(channelName, newChannel);
		
	}
	
	private String setChannel( String channelName, String setting, String value ) {
		
		ChatColor channelColor = channels.get(channelName).getColor();
		
		switch(setting) {
		
		case "permanent":
			
			if ( value == null || (value != "true" && value != "false") ) {
				//add a help message
				return "channel set permanent help message";
			} else {
				channels.get(channelName).setPermanent(value);
				return channelColor + "set channel permanence to: " + value;
			}
		
		case "local":
			if ( value == null || (value != "true" && value != "false") ) {
				//add a help message
				return "channel set local help message";
			} else {
				channels.get(channelName).setLocal(value);;
				return channelColor + "set channel localness to: " + value;
			}
			
		case "private":
			if ( value == null || (value != "true" && value != "false") ) {
				//add a help message
				return "channel set private help message";
			} else {
				channels.get(channelName).setPrivate(value);;
				return channelColor + "set channel privacy to: " + value;
			}
			
		case "color":
			if ( value == null ) {
				//add a help message
				return "channel set color help message";
			} else {
				return channelColor + "Set channel color to: " + channels.get(channelName).setColor(value);
			}
		default:
			return ChatColor.RED + "Invalid Argument";
		}
	}

	private void swapChannel(Player player, String joinedChannel) {
		String playerUUID = player.getUniqueId().toString().replace("-", "");
		String formerChannel = cfManager.get("players.yml", playerUUID + ".activeChannel");
		
		player.sendMessage("leaving the " + formerChannel + " channel");
		
		ChatChannel leftChannel = channels.get(formerChannel);
		leftChannel.leave(player);
		cfManager.set("players.yml", playerUUID + ".activeChannel", joinedChannel);
		
		if ( leftChannel.size() == 0 && !leftChannel.isPermanent() ) {
			channels.remove(formerChannel);
		}
		
		channels.get(joinedChannel).join(player);
		String activeChannel = cfManager.get("players.yml", playerUUID + ".activeChannel");
		ChatColor color = channels.get(activeChannel).getColor();
		
		player.sendMessage(color + "you joined the " + activeChannel + " channel");
	}

}
