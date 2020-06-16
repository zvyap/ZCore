package com.zvyap.core.file;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

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

	public boolean isSet(String path) {
		return this.config.isSet(path);
	}

	public void set(String path, Object value) {
		this.config.set(path, value);
	}
	
	public void set(String path, ConfigSetting settings) {
		this.config.set(path, settings.getString());
	}
	
	public void save() {
		try {
			this.config.save(this.file);
		} catch (IOException e) {
			e.printStackTrace();
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

	public String getFilename() {
		return filename;
	}
}
