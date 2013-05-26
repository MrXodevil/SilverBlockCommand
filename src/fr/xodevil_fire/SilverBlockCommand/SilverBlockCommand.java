package fr.xodevil_fire.SilverBlockCommand;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SilverBlockCommand extends JavaPlugin implements Listener {
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new Listener() {
			@EventHandler
			public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
				if (event.getMessage().contains("@p")) {
					event.setMessage(event.getMessage().replaceAll("@p", event.getPlayer().getName()));
				}
				
				if (event.getMessage().contains("@r")) {
					Random random = new Random();
					Integer i = random.nextInt(Bukkit.getOnlinePlayers().length);
					event.setMessage(event.getMessage().replaceAll("@r", Bukkit.getOnlinePlayers()[i].getName()));
				}
				
				if (event.getMessage().contains("@a")) {
					event.setCancelled(true);
					for (Player player : Bukkit.getOnlinePlayers()) {
						Bukkit.getPlayer(event.getPlayer().getName()).performCommand(event.getMessage().replaceAll("@a", player.getName()).substring(1));
					}
				}
			}
			
			@EventHandler
			public void onServerCommand(ServerCommandEvent event) {
				if (event.getCommand().contains("@r")) {
					Random random = new Random();
					Integer i = random.nextInt(Bukkit.getOnlinePlayers().length);
					event.setCommand(event.getCommand().replaceAll("@r", Bukkit.getOnlinePlayers()[i].getName()));
				}
				
				if (event.getCommand().contains("@a")) {
					for (Player player : Bukkit.getOnlinePlayers()) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), event.getCommand().replaceAll("@a", player.getName()));
					}
				}
			}
		}, this);
	}

}
