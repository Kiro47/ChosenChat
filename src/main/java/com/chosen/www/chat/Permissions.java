package com.chosen.www.chat;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.plugin.Plugin;

public class Permissions {
	
	//command permissions
	public static String COMMAND_GENERAL = "chosencraft.command.general";
	public static String COMMAND_CREATE_CHANNEL = "chosenchat.command.create";
	public static String COMMAND_DELETE_CHANNEL = "chosenchat.command.delete";
	public static String COMMAND_EDIT_CHANNEL = "chosenchat.command.set";
	public static String COMMAND_MAKE_PERMANENT = "chosenchat.command.set.permanent";
	
	ConfigManager cfManager;
	public HashMap<String, String> groups = new HashMap<String, String>();
	
	public Permissions( Plugin mainPlugin) {
		
		cfManager = ((MainChat) mainPlugin).cfgm;
		
		if ( cfManager.get("groups.yml", "chosenchat.groups.default") == null ) {
			groups.put("default", "&7default");
			System.out.println("created default group because it did not exist");
		}
		
		//need to loop thru all groups/ranks in config and add them to a list
		Set<String> configGroups = cfManager.getConfig("groups.yml").getConfig().getConfigurationSection("chosenchat.groups").getKeys(false);
		for ( String key : configGroups ) {
			groups.put(key, cfManager.get("groups.yml", "chosenchat.groups." + key));
		}
	}
	
	//rank groups
	public static String GROUP_STAFF = "chosenchat.group.staff";
	public static String GROUP_ADMIN = "chosenchat.group.admin";
	public static String GROUP_MODERATOR = "chosenchat.group.moderator";
	public static String GROUP_HELPER = "chosenchat.group.helper";
//	public static String GROUP_CHATMOD = "chosenchat.group.chatmod";
	public static String GROUP_ELDER = "chosenchat.group.elder";
	public static String GROUP_VETERAN = "chosenchat.group.veteran";
	public static String GROUP_REGULAR = "chosenchat.group.regular";
	public static String GROUP_DEFAULT = "chosenchat.group.default";
	
}
