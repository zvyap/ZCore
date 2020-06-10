package com.zvyap.zmcbot.core.support;

import org.bukkit.entity.Player;

import com.zvyap.zmcbot.core.PluginMain;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PlaceholderAPIManager extends PlaceholderExpansion{

	public String getAuthor() {
		return PluginMain.getInfo().getAuthors().get(0);
	}

	public String getIdentifier() {
		return PluginMain.getInfo().getPluginName().toLowerCase();
	}

	public String getVersion() {
		return PluginMain.getInfo().getVersion();
	}

	public String onPlaceholderRequest(Player p, String params) {
		return super.onPlaceholderRequest(p, params);
	}
	
	public boolean canRegister() {
		return true;
	}
	
	public boolean persist() {
		return true;
	}
}
