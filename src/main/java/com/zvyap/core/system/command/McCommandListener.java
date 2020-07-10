package com.zvyap.core.system.command;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.zvyap.core.system.command.McCommand.CommandCtx;

public class McCommandListener implements Listener {

	private McCommandManager manager;

	public McCommandListener(McCommandManager manager) {
		this.manager = manager;
	}

	@EventHandler
	public void onCommand(AsyncPlayerChatEvent evt) {
		if (!manager.getPrefix().equals("/") && evt.getMessage().startsWith(manager.getPrefix())) {
			String[] args = evt.getMessage().split(" ");
			for (String cmd : manager.getCommands().keySet()) {
				if (args[0].replaceFirst(manager.getPrefix(), "").equals(cmd)) {
					McCommand mc = manager.getMcCommand(args[0].replaceFirst(manager.getPrefix(), ""));
					CommandCtx ctx = new CommandCtx(evt.getPlayer(), null, evt.getMessage().replaceFirst(manager.getPrefix(), ""),
							evt.getMessage().substring(evt.getMessage().indexOf(" ") + 1).split(" "), mc);
					mc.execute(ctx);
					evt.setCancelled(true);
					break;
				}
			}
		}
	}
}
