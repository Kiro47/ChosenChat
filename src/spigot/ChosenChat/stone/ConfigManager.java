package spigot.ChosenChat.stone;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
	
	//config class for storing configuration files and config instances
	private class configNode {
		
		private FileConfiguration config;
		private File configFile;
		
		//constructor
		public configNode( FileConfiguration cfg, File cFile) {
			setConfig(cfg);
			setFile(cFile);
		}

		public FileConfiguration getConfig() {
			return config;
		}

		public void setConfig(FileConfiguration config) {
			this.config = config;
		}

		public File getFile() {
			return configFile;
		}

		public void setFile(File configFile) {
			this.configFile = configFile;
		}
		
	}

	private MainChat mainPlugin = MainChat.getPlugin(MainChat.class);
	private HashMap<String, configNode> allConfigs = new HashMap<String, configNode>();
	
	public void setup() {
		
		if ( !mainPlugin.getDataFolder().exists() ) {
			mainPlugin.getDataFolder().mkdir();
		}
		
		File[] configs = mainPlugin.getDataFolder().listFiles();
		
		for ( File f : configs ) {
			FileConfiguration tempConfig = YamlConfiguration.loadConfiguration(f);
			configNode cfgNode = new configNode(tempConfig, f);
			allConfigs.put(f.getName(), cfgNode);
		}
	}
	
	//returns the config with the given filename
	//otherwise creates a new config file with the given filename if one does not exist
	public configNode getConfig( String fileName ) {
		
		FileConfiguration tempConfig;
		File tempConfigFile = new File(mainPlugin.getDataFolder(), fileName);
		
		if ( !tempConfigFile.exists() ) {
			try {
				tempConfigFile.createNewFile();
			} catch ( IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "The" + fileName + "config file could not be created");
			}
			tempConfig = YamlConfiguration.loadConfiguration(tempConfigFile);
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "New config setup completed");
		
			configNode cfgNode = new configNode(tempConfig, tempConfigFile);
			allConfigs.put(fileName, cfgNode);
			saveConfig(tempConfig, tempConfigFile);
		
			return cfgNode;
		} else {
			
			return allConfigs.get(fileName);
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get( String fileName, String path) {
		
		configNode cfgNode = getConfig(fileName);
		FileConfiguration config = cfgNode.getConfig();
		return (T) config.get(path);
		
	}
	
	public void set( String fileName, String path, Object value) {

		configNode cfgNode = allConfigs.get(fileName);
		FileConfiguration config = cfgNode.getConfig();
		
		config.set(path, value);
		saveConfig( config, cfgNode.getFile());
	}
	
	public void saveConfig( FileConfiguration config, File configFile ) {
		try {
			config.save(configFile);
		} catch ( IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "The config was not saved");
		}
	}
	
	public void reload( FileConfiguration config, File configFile ) {
		config = YamlConfiguration.loadConfiguration(configFile);
		
		configNode cfgNode = new configNode(config, configFile);
		allConfigs.put(configFile.getName(), cfgNode);
	}
	
}
