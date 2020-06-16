package com.zvyap.core.system.command;

import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class McCommand {

	private String cmd, decs, perms, permsMsg;
	private List<String> aliases, tabComplete;

	public McCommand(String cmd, String decs, String perms, String permsMsg, List<String> aliases) {
		this.cmd = cmd;
		this.decs = decs;
		this.perms = perms;
		this.permsMsg = permsMsg;
		this.aliases = aliases;
	}

	protected abstract void onCommand(CommandCtx ctx);

	protected abstract List<String> onTabComplete(CommandCtx ctx);

	public void execute(CommandSender sender, @Nullable Command cmd, String label, String[] args) {
		CommandCtx ctx = new CommandCtx(sender, cmd, label, args, this);
		onCommand(ctx);
	}
	
	public void execute(CommandCtx ctx) {
		onCommand(ctx);
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
