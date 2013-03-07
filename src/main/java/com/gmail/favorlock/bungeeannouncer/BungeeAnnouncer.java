package com.gmail.favorlock.bungeeannouncer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import com.gmail.favorlock.bungeeannouncer.task.AnnounceTask;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeAnnouncer extends Plugin {
	
	private MainConfig config;
	private Timer timer = new Timer();
	
	public void onEnable() {
		
		 try {
			 config = new MainConfig(this); // create config
			 config.init(); // load config file if it exists, create it if it doesn't
		} catch(Exception ex) {
			 ProxyServer.getInstance().getLogger().log(Level.SEVERE, "FAILED TO LOAD CONFIG!!!", ex);
			 return;
		}
		
		// Schedule Announcement Timer
		sendAnnouncement();
		
	}
	
	public void onDisable() {
		timer.cancel();
	}
	
	public void sendAnnouncement() {
		TimerTask task = new AnnounceTask(this);
		timer.schedule(task, 0, this.getConfigStorage().settings_interval * 1000);
	}
	
	public MainConfig getConfigStorage() {
		return config;
	}

}
