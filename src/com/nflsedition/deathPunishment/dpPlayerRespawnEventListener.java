package com.nflsedition.deathPunishment;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class dpPlayerRespawnEventListener implements Listener{
	
	private final Plugin dp;
	
	public dpPlayerRespawnEventListener(Plugin plugin) {
		dp = plugin;
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		
		if (dp.getConfig().contains("Enabled")) {
			
			Player player = event.getPlayer();
			String deathworld = deathPunishment.loghash.getOrDefault(player.getName(), "");
			if (dp.getConfig().contains(deathworld)) {
				deathworld = deathworld+".";
			}else {
				deathworld = "";
			}
			final String worlddir = deathworld;
			boolean gamemode = true;
			
			if (dp.getConfig().getBoolean("Enabled")) {  //检查是否启用
				if (dp.getConfig().getBoolean("SurvivalOnly")) {  //检查是否仅限生存
					if (player.getGameMode().equals(GameMode.CREATIVE)) {
						gamemode = false;
					}
				}
				if (gamemode) {
					player.sendMessage(dpFile.getLocaleMessage("respawn", player.getLocale(),dp.getConfig().getString("Messages.respawn")));
				    new BukkitRunnable() {
                        
                        @Override
                        public void run() {
        					player.setHealth(20.0-dp.getConfig().getDouble(worlddir+"Health"));
        					player.setFoodLevel(20-dp.getConfig().getInt(worlddir+"Hunger"));
        					if (dp.getConfig().getBoolean(worlddir+"Potions.Enabled")){
        						for(int i=1;dp.getConfig().contains(worlddir+"Potions."+i);i++) {
        							PotionEffectType type = PotionEffectType.getByName(dp.getConfig().getString(worlddir+"Potions."+i+".Type"));
        							int duration = dp.getConfig().getInt(worlddir+"Potions."+i+".Duration");
        							int amplifier = dp.getConfig().getInt(worlddir+"Potions."+i+".Amplifier");
        							player.addPotionEffect(new PotionEffect(type, duration, amplifier));
        						}
        					}
                        }
				    }.runTaskLater(dp, 5L);
				}
			}
			
		}else {
			dp.saveDefaultConfig();
		}
	}
}
