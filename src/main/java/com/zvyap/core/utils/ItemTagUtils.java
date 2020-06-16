package com.zvyap.core.utils;

import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import com.zvyap.core.Version;

public class ItemTagUtils {
	private static Class<?> CraftServerClass;

	private static Object CraftServer;

	private static Class<?> TagTagCompound;

	private static Class<?> TagBase;

	private static Class<?> CraftItemStack;

	private static Class<?> IStack;

	public static void setup() {
		try {
			CraftServerClass = getBukkitClass("CraftServer");
			CraftServer = CraftServerClass.cast(Bukkit.getServer());
			TagTagCompound = getMinecraftClass("TagTagCompound");
			TagBase = getMinecraftClass("TagBase");
			CraftItemStack = getBukkitClass("inventory.CraftItemStack");
			IStack = getMinecraftClass("ItemStack");
		} catch (ClassCastException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Class<?> getBukkitClass(String nmsClassString) throws ClassNotFoundException {
		return Class
				.forName("org.bukkit.craftbukkit." + Version.getCurrentVersion() + "." + nmsClassString);
	}

	public static Class<?> getMinecraftClass(String nmsClassString) throws ClassNotFoundException {
		return Class
				.forName("net.minecraft.server." + Version.getCurrentVersion() + "." + nmsClassString);
	}

	public static ItemStack removeTag(ItemStack item, String name, String value) {
		if (item == null)
			return null;
		try {
			Object nmsStack = asNMSCopy(item);
			Method methTag = nmsStack.getClass().getMethod("getTag", new Class[0]);
			Object tag = methTag.invoke(nmsStack, new Object[0]);
			if (tag == null)
				return item;
			Method compountMeth = tag.getClass().getMethod("getCompound", new Class[] { String.class });
			Object compountTag = compountMeth.invoke(tag, new Object[] { name });
			if (compountTag == null)
				return item;
			Method meth = compountTag.getClass().getMethod("remove", new Class[] { String.class });
			meth.invoke(compountTag, new Object[] { value });
			Method mm = tag.getClass().getMethod("set", new Class[] { String.class, TagBase });
			mm.invoke(tag, new Object[] { name, compountTag });
			Method meth2 = nmsStack.getClass().getMethod("setTag", new Class[] { TagTagCompound });
			meth2.invoke(nmsStack, new Object[] { tag });
			return (ItemStack) asBukkitCopy(nmsStack);
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ItemStack removeTag(ItemStack item, String name) {
		if (item == null)
			return null;
		try {
			Object nmsStack = asNMSCopy(item);
			Method methTag = nmsStack.getClass().getMethod("getTag", new Class[0]);
			Object tag = methTag.invoke(nmsStack, new Object[0]);
			if (tag == null)
				return item;
			Method meth = tag.getClass().getMethod("remove", new Class[] { String.class });
			meth.invoke(tag, new Object[] { name });
			Method meth2 = nmsStack.getClass().getMethod("setTag", new Class[] { TagTagCompound });
			meth2.invoke(nmsStack, new Object[] { tag });
			return (ItemStack) asBukkitCopy(nmsStack);
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean hasTag(ItemStack item, String value) {
		if (item == null)
			return false;
		try {
			Object Tag = getTag(item);
			if (Tag == null)
				return false;
			Method meth = Tag.getClass().getMethod("getCompound", new Class[] { String.class });
			Object res = meth.invoke(Tag, new Object[] { value });
			return (res != null);
		} catch (Throwable e) {
			return false;
		}
	}

	public static boolean hasTagString(ItemStack item, String name) {
		if (item == null)
			return false;
		try {
			Object Tag = getTag(item);
			if (Tag == null)
				return false;
			Method meth = Tag.getClass().getMethod("getString", new Class[] { String.class });
			Object res = meth.invoke(Tag, new Object[] { name });
			return (res != null);
		} catch (Throwable e) {
			return false;
		}
	}

	public static Object getTag(ItemStack item) {
		if (item == null)
			return null;
		try {
			Object nmsStack = asNMSCopy(item);
			Method methTag = nmsStack.getClass().getMethod("getTag", new Class[0]);
			Object tag = methTag.invoke(nmsStack, new Object[0]);
			return tag;
		} catch (Throwable e) {
			return null;
		}
	}

	public static Object getTag(ItemStack item, String name, String value) {
		if (item == null)
			return null;
		try {
			Object Tag = getTag(item);
			if (Tag == null)
				return null;
			Method compoundMeth = Tag.getClass().getMethod("getCompound", new Class[] { String.class });
			Object compoundRes = compoundMeth.invoke(Tag, new Object[] { name });
			if (compoundRes == null)
				return null;
			Method meth = compoundRes.getClass().getMethod("getString", new Class[] { String.class });
			Object res = meth.invoke(compoundRes, new Object[] { value });
			return res;
		} catch (Throwable e) {
			return null;
		}
	}

	public static ItemStack setTag(ItemStack item, String name, String value) {
		if (item == null)
			return null;
		try {
			Object nmsStack = asNMSCopy(item);
			if (nmsStack == null)
				return item;
			Method methTag = nmsStack.getClass().getMethod("getTag", new Class[0]);
			Object tag = methTag.invoke(nmsStack, new Object[0]);
			if (tag == null)
				tag = TagTagCompound.newInstance();
			Method meth = tag.getClass().getMethod("setString", new Class[] { String.class, String.class });
			meth.invoke(tag, new Object[] { name, value });
			Method meth2 = nmsStack.getClass().getMethod("setTag", new Class[] { TagTagCompound });
			meth2.invoke(nmsStack, new Object[] { tag });
			return (ItemStack) asBukkitCopy(nmsStack);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Object getTag(ItemStack item, String value) {
		if (item == null)
			return null;
		try {
			Object Tag = getTag(item);
			if (Tag == null)
				return null;
			Method meth = Tag.getClass().getMethod("getString", new Class[] { String.class });
			Object res = meth.invoke(Tag, new Object[] { value });
			return res;
		} catch (Throwable e) {
			return null;
		}
	}

	public static Object asNMSCopy(ItemStack item) {
		try {
			Method meth = CraftItemStack.getMethod("asNMSCopy", new Class[] { ItemStack.class });
			return meth.invoke(CraftItemStack, new Object[] { item });
		} catch (Throwable e) {
			return null;
		}
	}

	public static Object asBukkitCopy(Object item) {
		try {
			Method meth = CraftItemStack.getMethod("asBukkitCopy", new Class[] { IStack });
			return meth.invoke(CraftItemStack, new Object[] { item });
		} catch (Throwable e) {
			return null;
		}
	}

	public Object getCraftServer() {
		return CraftServer;
	}
}
