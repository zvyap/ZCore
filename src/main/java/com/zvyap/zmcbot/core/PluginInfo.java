package com.zvyap.zmcbot.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.PluginDescriptionFile;

public class PluginInfo {

	private String pluginName;
	private List<String> authors;
	private ArrayList<Version> supportedVersion = new ArrayList<Version>();
	private String pluginPrefix;
	private boolean isPublicPlugin;
	private String pluginPage;
	private String version;
	private Version serverVersion;
	
	public PluginInfo(PluginDescriptionFile descFile) {
		pluginName = descFile.getName();
		authors = descFile.getAuthors();
		pluginPrefix = descFile.getPrefix();
		isPublicPlugin = false;
		pluginPage = descFile.getWebsite();
		this.version = descFile.getVersion();
		this.serverVersion = Version.getCurrentVersion();
		supportedVersion.addAll(Version.v1_12_R1.toVersion(Version.v1_15_R1));
	}

	public String getPluginName() {
		return pluginName;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public ArrayList<Version> getSupportedVersion() {
		return supportedVersion;
	}

	public String getPluginPrefix() {
		return pluginPrefix;
	}

	public boolean isPublicPlugin() {
		return isPublicPlugin;
	}

	public String getPluginPage() {
		return pluginPage;
	}
	
	public boolean isSupportVersion(Version version) {
		for(Version v : supportedVersion) {
			if(version == v) {
				return true;
			}
		}
		return false;
	}

	public String getVersion() {
		return version;
	}

	public Version getServerVersion() {
		return serverVersion;
	}
	
	public void setPublicPlugin(boolean isPublic) {
		this.isPublicPlugin = isPublic;
	}
	
	public void setPublicPlugin(ArrayList<Version> verList) {
		this.supportedVersion = verList;
	}

}
