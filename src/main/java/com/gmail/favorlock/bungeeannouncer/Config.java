package com.gmail.favorlock.bungeeannouncer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import net.craftminecraft.bungee.bungeeyaml.file.YamlConfiguration;
import net.md_5.bungee.api.ProxyServer;

public class Config {
	
	private YamlConfiguration config;
	private int interval = 60;
	private String prefix = "";
	private ArrayList<String> announcements;
	
	public Config(File file) {
		file = new File(file + File.separator + "config.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				ProxyServer.getInstance().getLogger().log(Level.SEVERE, "Failed to create config!", e);
			}
		}
		config = YamlConfiguration.loadConfiguration(file);
		
		this.addDefaults(file);
		this.load();
	}
	
	public void addDefaults(File file) {
		config.addDefault("settings.interval", 60);
		config.addDefault("settings.prefix", "&7Announcer&f: ");
		
		// Set Example Announcements
		ArrayList<String> announcement = new ArrayList<String>();
		announcement.add("&6Hello there&f, welcome to the server!");
		announcement.add("&4Enjoy your stay!");
		announcement.add("&4Vote for &amoney!");
		config.addDefault("announcements.global", announcement);
		
		config.options().copyDefaults(true);
		save(file);
	}
	
	public void load() {
		interval = config.getInt("settings.interval");
		prefix = config.getString("settings.prefix");
		setAnnouncements( (List<String>) config.getList("announcements.global"));
	}
	
	public void save(File file) {
		if (config == null || file == null) {
			return;
			}
			try {
			config.save(file);
			} catch (IOException e) {
			ProxyServer.getInstance().getLogger().log(Level.SEVERE, "Failed to save config!", e);
			}
	}
	
	public YamlConfiguration getConfig() {
		return config;
	}
	
	public int getInterval() {
		return interval;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public ArrayList<String> getAnnouncements() {
		return announcements;
	}
	
	public void setAnnouncements(List<String> announcements) {
		this.announcements = new ArrayList<String>();
		
		for (String announcement : announcements) {
			this.announcements.add(announcement);
		}
	}

}
