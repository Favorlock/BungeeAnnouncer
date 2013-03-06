package com.gmail.favorlock.bungeeannouncer;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import com.gmail.favorlock.bungeeannouncer.task.AnnounceTask;

import net.md_5.bungee.api.plugin.Plugin;

public class BungeeAnnouncer extends Plugin {
	
	private Config config;
	public BungeeAnnouncer plugin = this;
	private File configdir;
	private Timer timer = new Timer();
	
	public void onEnable() {
		
		configdir = new File("plugins" + File.separator + this.getDescription().getName());
		
		if (!configdir.exists()) {
			configdir.mkdirs();
		}
		
		config = new Config(configdir);
		
		// Schedule Announcement Timer
		sendAnnouncement();
		
	}
	
	public void onDisable() {
		timer.cancel();
	}
	
	public void sendAnnouncement() {
		TimerTask task = new AnnounceTask(plugin);
		timer.schedule(task, 0, this.getConfigStorage().getInterval() * 1000);
	}
	
	public Config getConfigStorage() {
		return config;
	}

}
