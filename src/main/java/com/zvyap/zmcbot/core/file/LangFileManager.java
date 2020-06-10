package com.zvyap.zmcbot.core.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import com.zvyap.zmcbot.core.PluginMain;

public class LangFileManager {

	private Class<?> classPath;
	
	private YamlConfiguration config;
	
	public LangFileManager(Class<?> classPath) {
		this.classPath = classPath;
		this.config = loadLang();
	}
	
	private YamlConfiguration loadLang() {
		String s = "en.yml";
		if (PluginMain.getInstance().getConfig().isSet("language")) {
			s = PluginMain.getInstance().getConfig().getString("language") + ".yml";
		}
		File lang = new File(PluginMain.getInstance().getDataFolder(), s);
		if (!lang.exists())
			try {
				lang.createNewFile();
				InputStream defConfigStream = PluginMain.getInstance().getResource(s);
				if (defConfigStream != null) {
					YamlConfiguration defConfig = YamlConfiguration
							.loadConfiguration(new InputStreamReader(defConfigStream, StandardCharsets.UTF_8));
					defConfig.save(lang);
					loadFromConfig();
					PluginMain.getInstance().getLogger().info("Created language file " + s);
					return defConfig;
				}
			} catch (IOException e) {
				e.printStackTrace();
				PluginMain.getInstance().getLogger().severe("Couldn't create language file.");
				PluginMain.getInstance().getLogger().severe("This is a fatal error. Now disabling.");
			}
		YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
		try {
			saveFile(lang);
		} catch (IOException | IllegalArgumentException | IllegalAccessException e) {
			PluginMain.getInstance().getLogger().warning("Failed to save lang.yml.");
			PluginMain.getInstance().getLogger().warning("Report this erorr to zvyap with config information");
			e.printStackTrace();
		}
		loadFromConfig();
		PluginMain.getInstance().getLogger().info("Loaded language file " + s);
		return conf;
	}

	
	public void saveFile(File file)
			throws IllegalArgumentException, IllegalAccessException, IOException {
		for (Field f : this.classPath.getDeclaredFields()) {
			if (!config.contains(f.getName()))
				config.set(f.getName(), f.get(null));
		}
		config.save(file);
	}

	public void loadFromConfig() {
		for (String key : config.getValues(false).keySet()) {
			key = ChatColor.translateAlternateColorCodes('&', key);
			try {
				this.classPath.getDeclaredField(key).set(key, config.get(key));
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				PluginMain.getInstance().getLogger().warning("Error when loading language value \"" + key + "\".");
				e.printStackTrace();
			}
		}
	}
}
