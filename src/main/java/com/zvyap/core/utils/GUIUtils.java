package com.zvyap.core.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIUtils {
	
	public static final ItemStack panel = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
	
	public static Inventory getGUI(String title, int size) {
		Inventory inv = Bukkit.createInventory(null, size, title);
		ItemStack frame = new ItemStack(Material.GRASS_PATH, 7);
		ItemMeta framemeta = frame.getItemMeta();
		framemeta.setDisplayName("§7");
		frame.setItemMeta(framemeta);
		
		if(inv.getSize() == 27) {
			for(int i = 0; i < 10; i++) {
				inv.setItem(i, frame);
			}
			for(int i = 17; i < 27; i++) {
				inv.setItem(i, frame);
			}
		}else if(inv.getSize() == 36){
			for(int i = 0; i < 10; i++) {
				inv.setItem(i, frame);
			}
			
			inv.setItem(17, frame);
			inv.setItem(18, frame);
			
			for(int i = 26; i < 36; i++) {
				inv.setItem(i, frame);
			}
		}else if(inv.getSize() == 45){
			for(int i = 0; i < 10; i++) {
				inv.setItem(i, frame);
			}
			
			inv.setItem(17, frame);
			inv.setItem(18, frame);
			inv.setItem(26, frame);
			inv.setItem(27, frame);
			
			for(int i = 35; i < 45; i++) {
				inv.setItem(i, frame);
			}
		}else if(inv.getSize() == 54) {
			for(int i = 0; i < 10; i++) {
				inv.setItem(i, frame);
			}
			
			inv.setItem(17, frame);
			inv.setItem(18, frame);
			inv.setItem(26, frame);
			inv.setItem(27, frame);
			inv.setItem(35, frame);
			inv.setItem(36, frame);
			
			for(int i = 44; i < 54; i++) {
				inv.setItem(i, frame);
			}
		}
		
		return inv;
	}
	
	public static Inventory removeGUIItems(Inventory inv) {
		for(int o = 10; o < 17; o++) {
			inv.setItem(o, null);
		}
		for(int o = 19; o < 26; o++) {
			inv.setItem(o, null);
		}
		for(int o = 28; o < 35; o++) {
			inv.setItem(o, null);
		}
		for(int o = 37; o < 44; o++) {
			inv.setItem(o, null);
		}
		return inv;
	}
}
