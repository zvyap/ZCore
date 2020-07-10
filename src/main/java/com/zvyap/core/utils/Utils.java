package com.zvyap.core.utils;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.zvyap.core.PluginMain;

public class Utils {

//	private static ItemTagUtils_Legacy ItemTagUtils;
//			
//	public static void RegisterUtils() {
//		switch (Version.getCurrentVersion()) {
//		case TOO_NEW:
//			break;
//		case TOO_OLD:
//			break;
//		case v1_10_R1:
//			break;
//		case v1_11_R1:
//			break;
//		case v1_12_R1:
//			ItemTagUtils = new v1_12_R1.ItemTagUtils();
//			break;
//		case v1_13_R1:
//			ItemTagUtils = new v1_13_R1.ItemTagUtils();
//			break;
//		case v1_13_R2:
//			ItemTagUtils = new v1_13_R2.ItemTagUtils();
//			break;
//		case v1_14_R1:
//			ItemTagUtils = new v1_14_R1.ItemTagUtils();
//			break;
//		case v1_15_R1:
//			ItemTagUtils = new v1_15_R1.ItemTagUtils();
//			break;
//		case v1_7_R1:
//			break;
//		case v1_7_R2:
//			break;
//		case v1_7_R3:
//			break;
//		case v1_7_R4:
//			break;
//		case v1_8_R1:
//			break;
//		case v1_8_R2:
//			break;
//		case v1_8_R3:
//			break;
//		case v1_9_R1:
//			break;
//		case v1_9_R2:
//			break;
//		default:
//			break;
//		}
//	}
//	
//	public static ItemTagUtils_Legacy getItemTagUtils() {
//		return ItemTagUtils;
//	}
	
	public static void setupUtils() {
		//ItemTagUtils.setup();
	}

	public static void sendSevere(String msg) {
		PluginMain.getInstance().getLogger().severe(msg);
	}

	public static void sendWarning(String msg) {
		PluginMain.getInstance().getLogger().warning(msg);
	}

	public static void sendInfo(String msg) {
		PluginMain.getInstance().getLogger().info(msg);
	}

	public static void sendLog(String msg) {
		PluginMain.getInstance().getLogger().info(msg);
	}
	
	public static void sendConsole(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg);
	}
	
	public static void playDing(Player p) {
		p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
	}
	
	public static void playDong(Player p) {
		p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
	}
	
	public static void playDing(Player p, String msg) {
		p.sendMessage(msg);
		p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
	}
	
	public static void playDong(Player p, String msg) {
		p.sendMessage(msg);
		p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
	}

    public static String formatTime(long timeInMillis) {
        final long hours = timeInMillis / TimeUnit.HOURS.toMillis(1);
        final long minutes = timeInMillis / TimeUnit.MINUTES.toMillis(1);
        final long seconds = timeInMillis % TimeUnit.MINUTES.toMillis(1) / TimeUnit.SECONDS.toMillis(1);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    
    public static String randomString(int length) {
        byte[] array = new byte[length]; // length is bounded by 7
        new Random().nextBytes(array);
        String s = new String(array, Charset.forName("UTF-8"));
		return s;
    }
}
