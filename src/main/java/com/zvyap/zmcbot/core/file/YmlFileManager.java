package com.zvyap.zmcbot.core.file;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class YmlFileManager {

	private String filename;
	private File file;
	private FileConfiguration config;
	
	public YmlFileManager(File file) {
		this.file = file;
		this.config = null;
		if (file.getName().substring(file.getName().lastIndexOf(".") + 1).equals("yml")) {
			this.config = YamlConfiguration.loadConfiguration(file);
		}
		this.filename = file.getName();
	}
	
	public YmlFileManager(YamlConfiguration ymlfile) {
		this.file = new File(ymlfile.getCurrentPath(), ymlfile.getName());
		if(ymlfile instanceof FileConfiguration) {
			this.config = ymlfile;
		}
		this.filename = file.getName();
	}
	
	public FileConfiguration getFile() {
		return config;
	}

	public ItemStack getItemStack(String field, String def) {
		String key = this.filename + "." + field;
		if (this.config.isSet(key))
			return this.config.getItemStack(key);
		if (this.config.isSet(key.toLowerCase()))
			return this.config.getItemStack(key.toLowerCase());
		try {
			return new ItemStack(Material.valueOf(def));
		} catch (Exception ex) {
			return null;
		}
	}
	
	public boolean isSet(String path) {
		return this.config.isSet(path);
	}

	public void set(String path, Object value) {
		this.config.set(path, value);
	}
	
	public void save() {
		try {
			this.config.save(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getColorString(String path) {
		return ChatColor.translateAlternateColorCodes('&', config.getString(path));
	}
	
	public int getInt(String path) {
		return config.getInt(path);
	}
	
	public double getDouble(String path) {
		return config.getDouble(path);
	}
	
	public String getString(String path) {
		return config.getString(path);
	}
	
	public Sound getSound(String path) {
		try {
			return Sound.valueOf(path);
		} catch (Exception e) {
			return null;
		}
	}
	
	/*
	 * public Set<String> getPathInside(String path) {
	 * if(config.getConfigurationSection("Entity") == null) { return null; }else {
	 * Set<String> set = config.getConfigurationSection(path).getKeys(false); return
	 * set; } }
	 */
	
	public boolean isConfigurationSection(String path) {
		return this.config.isConfigurationSection(path);
	}

	public Set<String> getKeys(String path) {
		return this.config.getConfigurationSection(path).getKeys(false);
	}
}
