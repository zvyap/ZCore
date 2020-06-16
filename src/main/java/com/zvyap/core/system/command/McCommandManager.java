package com.zvyap.core.system.command;

import java.util.ArrayList;
import java.util.HashMap;

import com.zvyap.core.PluginMain;

public class McCommandManager {

	private static ArrayList<McCommandManager> commandmanagers = new ArrayList<McCommandManager>();
	
	private HashMap<String, McCommand> commands = new HashMap<String, McCommand>();
	private String prefix, commandSymbol;
	private boolean commandPrefix;
	
	public McCommandManager(String prefix, String commandSymbol, boolean commandPrefix) {
		this.prefix = prefix;
		this.commandSymbol = commandSymbol;
		this.commandPrefix = commandPrefix;
	}
	
	public McCommandManager(String commandSymbol, boolean commandPrefix) {
		this.prefix = PluginMain.getInfo().getPluginPrefix();
		this.commandSymbol = commandSymbol;
		this.commandPrefix = commandPrefix;
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
			PluginMain.getInstance().getCommand(command).setExecutor(new McCommandExecute(mccommand));
			PluginMain.getInstance().getCommand(command).setTabCompleter(new McCommandExecute(mccommand));
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

	public String getCommandSymbol() {
		return commandSymbol;
	}

	public void setCommandSymbol(String commandSymbol) {
		this.commandSymbol = commandSymbol;
	}

	public boolean isCommandPrefix() {
		return commandPrefix;
	}

	public void setCommandPrefix(boolean commandPrefix) {
		this.commandPrefix = commandPrefix;
	}
}
