package com.zvyap.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class ItemUtils {

	public static ItemStack getItem(Material mat, String name, String... lore) {
		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		
		List<String> lores = new ArrayList<String>();
		for(String s : lore) {
			lores.add(s);
		}
		meta.setLore(lores);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack getPotion(PotionType type, String name, String... lore) {
		ItemStack item = new ItemStack(Material.POTION);
		PotionMeta pmeta = (PotionMeta) item.getItemMeta();
		PotionData pdata = new PotionData(type);
		pmeta.setBasePotionData(pdata);
		item.setItemMeta(pmeta);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(name);
		List<String> lores = new ArrayList<String>();
		for(String s : lore) {
			lores.add(s);
		}
		meta.setLore(lores);
				
		item.setItemMeta(meta);

		return item;
	}
	
	public static ItemStack getPotion(PotionType type) {
		ItemStack item = new ItemStack(Material.POTION);
		PotionMeta pmeta = (PotionMeta) item.getItemMeta();
		PotionData pdata = new PotionData(type);
		pmeta.setBasePotionData(pdata);
		item.setItemMeta(pmeta);
		
		return item;
	}
	
	public static ItemStack getPotion(PotionType type, String name) {
		ItemStack item = new ItemStack(Material.POTION);
		PotionMeta pmeta = (PotionMeta) item.getItemMeta();
		PotionData pdata = new PotionData(type);
		pmeta.setBasePotionData(pdata);
		item.setItemMeta(pmeta);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(name);
		
		item.setItemMeta(meta);

		return item;
	}
	
	public static ItemStack getItem(Material mat, String name, int amount, String... lore) {
		ItemStack item = new ItemStack(mat, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		
		List<String> lores = new ArrayList<String>();
		for(String s : lore) {
			lores.add(s);
		}
		meta.setLore(lores);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack getItem(Material mat, String name) {
		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack getItem(Material mat, String name, int amount) {
		ItemStack item = new ItemStack(mat, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack getItem(Material mat, boolean enchant, String name) {
		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		
		if(enchant) {
			meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		}
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack getItem(Material mat, boolean enchant, String name, String... lore) {
		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		
		List<String> lores = new ArrayList<String>();
		for(String s : lore) {
			lores.add(s);
		}
		meta.setLore(lores);
		
		if(enchant) {
			meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		}
		
		item.setItemMeta(meta);
		
		return item;
	}
}
