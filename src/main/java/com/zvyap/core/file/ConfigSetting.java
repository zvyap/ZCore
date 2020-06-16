package com.zvyap.core.file;

import javax.annotation.Nullable;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.zvyap.core.objects.SoundType;

public class ConfigSetting {

	private String String;

	public ConfigSetting(String string) {
		this.String = string;
	}
	
	@Nullable
	public ItemStack getItemStack() {
		try {
			return new ItemStack(Material.valueOf(String));
		} catch (Exception ex) {
			return null;
		}
	}
	
	@Nullable
	public Material getMaterial() {
		try {
			return Material.valueOf(String);
		} catch (Exception ex) {
			return null;
		}
	}
	
	public String getColorString() {
		return ChatColor.translateAlternateColorCodes('&', String);
	}
	
	@Nullable
	public SoundType getSoundType() {
		try {
			return SoundType.getSoundType(String);
		} catch (Exception e) {
			return null;
		}
	}

	public String getString() {
		return String;
	}

	public void setString(String string) {
		this.String = string;
	}

	public int getInt() {
		try {
			return Integer.parseInt(String);
		} catch (Exception e) {
			return -1;
		}
	}

	public boolean isBoolean() {
		try {
			return Boolean.parseBoolean(String);
		} catch (Exception e) {
			return false;
		}
	}

	public double getDouble() {
		try {
			return Double.parseDouble(String);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public float getFloat() {
		try {
			return Float.parseFloat(String);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
}
