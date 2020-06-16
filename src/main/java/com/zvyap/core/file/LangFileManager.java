package com.zvyap.core.file;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import com.zvyap.core.PluginMain;
import com.zvyap.core.utils.Utils;

public class LangFileManager {
	
	private YamlConfiguration lang;
	
	private File file;
	
	private LangFile langfile;
	
	private String language = "en.yml";
	
	public LangFileManager(LangFile langfile, String language) {
		this.language = language;
		this.langfile = langfile;
		this.lang = loadLang();
	}
	
	private YamlConfiguration loadLang() {
		file = new File(PluginMain.getInstance().getDataFolder(), language);
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				Utils.sendSevere("Couldn't create language file.");
				Utils.sendSevere("Loaded default language |English|.");
				this.language = "en.yml";
			}
		YamlConfiguration conf = YamlConfiguration.loadConfiguration(file);
		try {
			saveFile();
		} catch (IOException | IllegalArgumentException | IllegalAccessException e) {
			Utils.sendWarning("Failed to save lang.yml.");
			Utils.sendWarning("Report this erorr to zvyap with lang information");
			e.printStackTrace();
		}
		loadFromlang();		
		Utils.sendInfo("Loaded language file " + language);
		return conf;
	}

	
	public void saveFile() throws IllegalArgumentException, IllegalAccessException, IOException {
		for (String langid : langfile.getLangs().keySet()) {
			if (!lang.contains(langid)) {
				lang.set(langid, langfile.getLangs().get(langid).replace("§", "&"));
			}
		}
		lang.save(file);
	}

	public void loadFromlang() {
		for (String langid : langfile.getLangs().keySet()) {
			String string = langfile.getLangs().get(langid);
			string = ChatColor.translateAlternateColorCodes('&', string);
			try {
				langfile.setLang(langid, string);
			} catch (IllegalArgumentException | SecurityException e) {
				Utils.sendWarning("Error when loading language id \"" + langid + "\".");
				e.printStackTrace();
			}
		}
	}
}
