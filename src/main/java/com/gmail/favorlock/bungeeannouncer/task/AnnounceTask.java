package com.gmail.favorlock.bungeeannouncer.task;

import java.util.ArrayList;
import java.util.TimerTask;

import com.gmail.favorlock.bungeeannouncer.BungeeAnnouncer;
import com.gmail.favorlock.bungeeannouncer.utils.FontFormat;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class AnnounceTask extends TimerTask {
	private int counter = 0;
	private String prefix = "";
	private ArrayList<String> announcements = new ArrayList<String>();
	
	public AnnounceTask(BungeeAnnouncer plugin) {
		this.prefix = plugin.getConfigStorage().getPrefix();
		this.announcements = plugin.getConfigStorage().getAnnouncements();
	}
	
	@Override
	public void run() {
		if (announcements.size() > 0) {
			announcements.get(counter);
			for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
				player.sendMessage(FontFormat.translateString(prefix +
						announcements.get(counter)));
			}
			next();
		}
	}
	
	public void next() {
		counter++;
		
		if (counter == announcements.size()) {
			counter = 0;
		}
	}
}
