package com.gmail.favorlock.bungeeannouncer;

import java.io.File;
import java.util.ArrayList;
import net.craftminecraft.bungee.bungeeyaml.supereasyconfig.Config;
import net.md_5.bungee.api.plugin.Plugin;

public class MainConfig extends Config {
	
	public MainConfig(Plugin plugin) {
		CONFIG_FILE = new File("plugins" + File.separator + plugin.getDescription().getName(), "config.yml");
		CONFIG_HEADER = "BungeeAnnouncer - Global Server Announcments";
	}
	
	public int settings_interval = 60;
	public String settings_prefix = "Announcer: ";
	public ArrayList<String> announcements_global = new ArrayList<String>(){{
		add("&6Hello there&f, welcome to the server!");
		add("&4Enjoy your stay!");
		add("&4Vote for &amoney!");
	}};

}
