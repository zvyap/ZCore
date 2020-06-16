package com.zvyap.core;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.zvyap.core.utils.Utils;

public abstract class PluginMain extends JavaPlugin {

	protected abstract void setupEnable();

	protected abstract void setupDisable();
	
	protected abstract void setupFile();
	
	protected abstract void setupCommand();
	
	protected abstract void setupListener();
		
	protected static PluginMain plgMain;
	
	protected boolean isCitizens; 
	
	protected static Version version = Version.getCurrentVersion();
	
	protected static PluginInfo info;
	
	public final void onEnable() {
		info = new PluginInfo(getDescription());
		if(!checkSupport()) {
			return;
		}
		plgMain = this;
		checkcitizens();
		setupEnable();
		setupFile();
		setupCommand();
		setupListener();
		Utils.setupUtils();
		Bukkit.getConsoleSender().sendMessage("=====================================");
		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage(info.getPluginName() +"System Enable Successfully");
		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage("=====================================");
	}

	public final void onDisable() {
		setupDisable();
		plgMain = null;
		Bukkit.getConsoleSender().sendMessage("=====================================");
		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage(info.getPluginName() +" System Disable Successfully");
		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage("=====================================");
	}
	
	protected void registerListener(Listener listener, Plugin plugin) {
		getServer().getPluginManager().registerEvents(listener, plugin);;
	}
	
	protected void registerCommand(String cmd, CommandExecutor executor) {
		getCommand(cmd).setExecutor(executor);
	}
	
	public static PluginMain getInstance() {
		return plgMain;
	}
	
	public static Version getVersion() {
		return version;
	}
	
	private boolean checkSupport() {
		if (info.isSupportVersion(version)) {
			String header = "============= "+ info.getPluginName() +" =============";
			String footer = "";
			for(int i = 0; i > header.length(); i++) {
				footer = footer + "=";
			}
			System.out.println(header);
			System.out.println(" ");
			System.out.println("Plugin Load Fail: This server is running on unsupport version. Please check plugin's spigot page for more information about plugin support Minecraft versions.");
			System.out.println(" ");
			System.out.println("Minecraft Server Version: " + version.toString());
			System.out.println(" ");
			System.out.println(footer);
			Bukkit.getPluginManager().disablePlugin(this);
			return false;
		}else {
			return true;
		}
	}
	
	private boolean checkcitizens() {
		if(getServer().getPluginManager().getPlugin("Citizens") == null || getServer().getPluginManager().getPlugin("Citizens").isEnabled() == false) {
			getLogger().log(Level.SEVERE, "Citizens 2.0 not found or not enabled");
			//getServer().getPluginManager().disablePlugin(this);	
			return false;
		}else {
			return true;
		}
	}

	public static PluginInfo getInfo() {
		return info;
	}
}

