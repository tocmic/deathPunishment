package com.nflsedition.deathPunishment;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.plugin.Plugin;

public class dpEntityPickupItemEventListener implements Listener{
	
	private final Plugin dp;
	
	public dpEntityPickupItemEventListener(Plugin plugin) {
		dp = plugin;
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onEntityPickupItem(EntityPickupItemEvent event) {
		if ((event.getItem().getItemStack().hasItemMeta())&&(event.getItem().getItemStack().getItemMeta().hasLore())&&(event.getItem().getItemStack().getItemMeta().getLore().contains("¡ìc½ðÇ®µôÂä"))) {
			if (deathPunishment.economyEnabled) {
				if (event.getEntityType().equals(EntityType.PLAYER)) {
					OfflinePlayer offlinePlayer = (OfflinePlayer)event.getEntity();
					double money = Double.parseDouble(event.getItem().getItemStack().getItemMeta().getLore().get(1));
					
					String locale = "zh_cn";
					if (dp.getConfig().getBoolean("Locale")) {
						locale = ((Player) event.getEntity()).getLocale();
					}
					
					deathPunishment.economy.depositPlayer(offlinePlayer, money);
					event.getItem().remove();
					event.getEntity().sendMessage(String.format(dpFile.getLocaleMessage("lootpickup", locale, dp.getConfig().getString("Messages.lootpickup")),""+money));
					event.setCancelled(true);
				}else {
					event.setCancelled(true);
				}
			}
		}
	}
}
