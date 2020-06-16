package com.zvyap.core;

import java.util.ArrayList;

import org.bukkit.Bukkit;

public enum Version {
	TOO_OLD(-1), 
	v1_7_R1(171), 
	v1_7_R2(172), 
	v1_7_R3(173), 
	v1_7_R4(174), 
	v1_8_R1(181), 
	v1_8_R2(182), 
	v1_8_R3(183),
	v1_9_R1(191), 
	v1_9_R2(192), 
	v1_10_R1(1101), 
	v1_11_R1(1111), 
	v1_12_R1(1121), 
	v1_13_R1(1132), 
	v1_13_R2(1132), 
	v1_14_R1(1141),
	v1_15_R1(1151), 
	TOO_NEW(-2);

	public static Version currentVersion;

	private static Version latest;

	private Integer versionInteger;

	Version(int versionInteger) {
		this.versionInteger = Integer.valueOf(versionInteger);
	}

	public static Version getCurrentVersion() {
		if (currentVersion == null) {
			String ver = Bukkit.getServer().getClass().getPackage().getName();
			int v = Integer.parseInt(ver.substring(ver.lastIndexOf('.') + 1).replaceAll("_", "").replaceAll("R", "")
					.replaceAll("v", ""));
			for (Version version : values()) {
				if (version.getVersionInteger().intValue() == v) {
					currentVersion = version;
					break;
				}
			}
			if (v > getLatestVersion().getVersionInteger().intValue())
				currentVersion = getLatestVersion();
			if (currentVersion == null)
				currentVersion = TOO_NEW;
		}
		return currentVersion;
	}

	public static Version getLatestVersion() {
		if (latest == null) {
			Version v = TOO_OLD;
			for (Version version : values()) {
				if (version.comparedTo(v).intValue() == 1)
					v = version;
			}
			return v;
		}
		return latest;
	}

	public Integer getVersionInteger() {
		return this.versionInteger;
	}
	
	public ArrayList<Version> toVersion(Version version) {
		int ver = version.getVersionInteger().intValue();
		if(ver == -1 || ver == -2 || this.versionInteger == -1 || this.versionInteger == -2) {
			return null;
		}
		ArrayList<Version> verList = new ArrayList<Version>();
		for (Version v : values()) {
			int vInt = v.getVersionInteger();
			if (vInt >= this.versionInteger) {
				if (vInt <= ver) {
					verList.add(v);
				}
			}
		}
		if (verList.isEmpty()) {
			return null;
		} else {
			return verList;
		}
	}
	
	public static ArrayList<Version> getBetweenVersion(Version version1, Version version2) {
		ArrayList<Version> verList = new ArrayList<Version>();
		int ver = version2.getVersionInteger().intValue();
		if(ver == -1 || ver == -2 || version1.getVersionInteger() == -1 || version1.getVersionInteger() == -2) {
			return null;
		}
		for (Version v : values()) {
			int vInt = v.getVersionInteger();
			if (vInt >= version1.getVersionInteger()) {
				if (vInt <= ver) {
					verList.add(v);
				}
			}
		}
		if (verList.isEmpty()) {
			return null;
		} else {
			return verList;
		}
	}

	public Integer comparedTo(Version version) {
		int resault = -1;
		int current = getVersionInteger().intValue();
		int check = version.getVersionInteger().intValue();
		if (current > check || check == -2) {
			resault = 1;
		} else if (current == check) {
			resault = 0;
		} else if (current < check || check == -1) {
			resault = -1;
		}
		return Integer.valueOf(resault);
	}

	public boolean isNewer(Version version) {
		return (this.versionInteger.intValue() > version.versionInteger.intValue()
				|| this.versionInteger.intValue() == -2);
	}

	public boolean isSame(Version version) {
		return this.versionInteger.equals(version.versionInteger);
	}

	public boolean isOlder(Version version) {
	    return (this.versionInteger.intValue() < version.versionInteger.intValue() || this.versionInteger.intValue() == -1);
	}
}