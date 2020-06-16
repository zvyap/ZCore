package com.zvyap.core.utils;

import org.bukkit.entity.Player;

import com.zvyap.core.PluginMain;
import com.zvyap.core.objects.SoundType;

public class SoundUtils {
	
	private static boolean enable = true;
	
	public void setEnable(boolean enable) {
		SoundUtils.enable = enable;
	}
	
	public static void playSound(Player p, SoundType type) {
		if (enable == false) {
			return;
		}
		try {
			type.play(p);
		} catch (Exception e) {
			PluginMain.getInstance().getLogger().warning("Config Sound Error");
		}
	}
	
	public static void playSound(Player p, SoundType type, float v, float pi) {
		if (enable == false) {
			return;
		}
		try {
			type.play(p, v, pi);
		} catch (Exception e) {
			PluginMain.getInstance().getLogger().warning("Config Sound Error");
		}
	}
}
