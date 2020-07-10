package com.zvyap.core.system.command;

import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.zvyap.core.objects.SoundType;

public abstract class McCommand {

	protected String cmd, decs, perms, permsMsg;
	protected List<String> aliases, tabComplete;
	protected McCommandManager manager;
	protected SoundType failSound, successSound;
	protected boolean playeronly;
	
	public McCommand(McCommandManager manager, String cmd) {
		this.manager = manager;
		this.cmd = cmd;
		
		manager.registerCommand(cmd, this);
	}
	
	public McCommand(McCommandManager manager, String cmd, String decs, String perms, String permsMsg, List<String> aliases) {
		this.manager = manager;
		this.cmd = cmd;
		this.decs = decs;
		this.perms = perms;
		this.permsMsg = permsMsg;
		this.aliases = aliases;
		
		manager.registerCommand(cmd, this);
	}

	protected abstract boolean onCommand(CommandCtx ctx);

	protected abstract List<String> onTabComplete(CommandCtx ctx);

	public void execute(CommandSender sender, @Nullable Command cmd, String label, String[] args) {
		if(playeronly) {
			if(sender instanceof Player) {
				return;
			}
		}
		if(perms != null && !sender.hasPermission(perms)) {
			if(permsMsg != null) {
				sender.sendMessage(permsMsg);
			}else {
				sender.sendMessage("You dont have permission to use this");
			}
			if(failSound != null) {
				if(sender instanceof Player) {
					failSound.play((Player) sender);
				}
			}
			return;
		}
		
		CommandCtx ctx = new CommandCtx(sender, cmd, label, args, this);
		if(onCommand(ctx)) {
			if(successSound != null) {
				if(sender instanceof Player) {
					successSound.play((Player) sender);
				}
			}
		}
	}
	
	public void execute(CommandCtx ctx) {
		if(onCommand(ctx)) {
			if(successSound != null) {
				if(ctx.getSender() instanceof Player) {
					successSound.play((Player) ctx.getSender());
				}
			}
		}
	}
	
	private void tabExecute(CommandSender sender, Command cmd, String label, String[] args) {
		CommandCtx ctx = new CommandCtx(sender, cmd, label, args, this);
		this.tabComplete = onTabComplete(ctx);
	}

	public String getCmd() {
		return cmd;
	}

	public String getDecs() {
		return decs;
	}

	public String getPerms() {
		return perms;
	}

	public String getPermsMsg() {
		return permsMsg;
	}

	public List<String> getAliases() {
		return aliases;
	}

	public List<String> getTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		tabExecute(sender, cmd, label, args);
		return tabComplete;
	}

	public static class CommandCtx {
		
		private CommandSender sender;
		private String cmd;
		private String label;
		private String[] args;
		private McCommand mc;

		public CommandCtx(CommandSender sender, Command cmd, String label, String[] args, McCommand mc) {
			this.sender = sender;
			this.cmd = cmd.getName();
			this.label = label;
			this.args = args;
			this.mc = mc;
			cmd.getAliases();
			cmd.getDescription();
			cmd.getPermission();
			cmd.getPermissionMessage();
		}

		public CommandSender getSender() {
			return sender;
		}

		public String getCmd() {
			return cmd;
		}

		public String getLabel() {
			return label;
		}

		public String[] getArgs() {
			return args;
		}

		public McCommand getMc() {
			return mc;
		}
	}
}
