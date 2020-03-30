package com.nflsedition.deathPunishment;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.Plugin;

@SuppressWarnings("deprecation")
public class dpPlayerPickupItemEventListener implements Listener{
	
	private final Plugin dp;
	
	public dpPlayerPickupItemEventListener(Plugin plugin) {
		dp = plugin;
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onEntityPickupItem(PlayerPickupItemEvent event) {
		if ((event.getItem().getItemStack().hasItemMeta())&&(event.getItem().getItemStack().getItemMeta().hasLore())&&(event.getItem().getItemStack().getItemMeta().getLore().contains("¡ìc½ðÇ®µôÂä"))) {
			if (deathPunishment.economyEnabled) {
				OfflinePlayer offlinePlayer = (OfflinePlayer)event.getPlayer();
				double money = Double.parseDouble(event.getItem().getItemStack().getItemMeta().getLore().get(1));
				
				String locale = "zh_cn";
				if (dp.getConfig().getBoolean("Locale")) {
					locale = ((Player) event.getPlayer()).getLocale();
				}
				
				deathPunishment.economy.depositPlayer(offlinePlayer, money);
				event.getItem().remove();
				event.getPlayer().sendMessage(String.format(dpFile.getLocaleMessage("lootpickup", locale, dp.getConfig().getString("Messages.lootpickup")),""+money));
				event.setCancelled(true);
			}
		}
	}
}
