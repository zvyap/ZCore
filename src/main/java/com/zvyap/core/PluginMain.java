package com.zvyap.core;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.zvyap.core.PluginInfo.Plugin;
import com.zvyap.core.utils.Utils;

public abstract class PluginMain extends JavaPlugin {

	protected abstract void setupEnable();

	protected abstract void setupDisable();
	
	protected abstract void setupFile();
	
	protected abstract void setupCommand();
	
	protected abstract void setupListener();
		
	protected static PluginMain plgMain;
		
	protected static Version version = Version.getCurrentVersion();
	
	protected static PluginInfo info;
	
	public final void onEnable() {
		info = new PluginInfo(getDescription());
		plgMain = this;
		setupEnable();
		if(!checkSupport()) {
			return;
		}
		if(!checkDepends()) {
			return;
		}
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
	
	protected void registerListener(Listener listener, org.bukkit.plugin.Plugin plugin) {
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
	
	private boolean checkDepends() {
		for (Plugin plugin : Plugin.values()) {
			if (info.getPlugindepends().containsKey(plugin)) {
				if (info.getPlugindepends().get(plugin)) {
					if (plugin.getPlugin() == null || plugin.getPlugin().isEnabled() == false) {
						Utils.sendSevere("Plugin | " + plugin.getPlugin().getName() + " | is not enable");
						getServer().getPluginManager().disablePlugin(this);
						return false;
					} else {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static PluginInfo getInfo() {
		return info;
	}
}

