package com.zvyap.core.system.command;

import java.util.ArrayList;
import java.util.HashMap;

import com.zvyap.core.PluginMain;

public class McCommandManager {

	private static ArrayList<McCommandManager> commandmanagers = new ArrayList<McCommandManager>();
	
	private HashMap<String, McCommand> commands = new HashMap<String, McCommand>();
	private String prefix;
	
	public McCommandManager(String prefix) {
		this.prefix = prefix;
	}
	
	public static ArrayList<String> getMinecraftCommand() {
		ArrayList<String> list = new ArrayList<String>();
		for(McCommandManager manager : getCommandManagers()) {
			if(manager.getPrefix().equals("/")) {
				list.addAll(manager.getCommands().keySet());
			}
		}
		return list;
	}
	
	public static ArrayList<McCommandManager> getCommandManagers(){
		return commandmanagers;
	}
	
	public static McCommandManager getManager(String cmd){
		for(McCommandManager manager : getCommandManagers()) {
			if(manager.getPrefix().equals("/")) {
				if(manager.getCommands().containsKey(cmd)) {
					return manager;
				}
			}
		}
		return null;
	}
	
	public McCommand getMcCommand(String cmd) {
		for(String command : getCommands().keySet()) {
			if(command.equals(cmd)) {
				return getCommands().get(command);
			}
		}
		return null;
	}
	
	public void registerCommand(String command, McCommand mccommand) {
		this.getCommands().put(command, mccommand);
		if(prefix.equals("/")) {
			PluginMain.getInstance().getCommand(command).setExecutor(new McCommandExecutor(mccommand));
			PluginMain.getInstance().getCommand(command).setTabCompleter(new McCommandExecutor(mccommand));
		}else {
			PluginMain.getInstance().getServer().getPluginManager().registerEvents(new McCommandListener(this), PluginMain.getInstance());
		}
	}
	
	public HashMap<String, McCommand> getCommands() {
		return commands;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
