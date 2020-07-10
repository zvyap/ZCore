package com.zvyap.core.system.command;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

public class McCommandExecutor implements CommandExecutor, TabExecutor {
	
	static ArrayList<McCommandManager> managers = McCommandManager.getCommandManagers();

	private McCommand mc;
	
	public McCommandExecutor(McCommand mc) {
		this.mc = mc;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, @Nullable Command command, String label, String[] args) {
		mc.execute(sender, command, label, args);
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		return mc.getTabComplete(sender, cmd, label, args);
	}

}
