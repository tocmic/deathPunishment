package com.nflsedition.deathPunishment;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;

public class dpExpDrop {
	
	public static int dropExpDefault(Player player,int limit) {
		int amount = 0;
		Location location = player.getLocation();
		ExperienceOrb orb;
		for (int i=0;i<player.getLevel();i++) {
			if (amount<limit) {
				orb =  (ExperienceOrb) location.getWorld().spawnEntity(location, EntityType.EXPERIENCE_ORB);
				orb.setExperience(7);
				amount += 7;
			}else {
				orb =  (ExperienceOrb) location.getWorld().spawnEntity(location, EntityType.EXPERIENCE_ORB);
				orb.setExperience(limit-amount);
				amount = limit;
				break;
			}
		}
		int lv = player.getLevel();
		player.setLevel(0);
		return lv;
	}
	
	public static int dropExpFixed(Player player, int level, Boolean spawnorb, int orbperlevel, int orbvalue) {
			Location location = player.getLocation();
			ExperienceOrb orb;
			if (player.getLevel() >= level) {
				player.giveExpLevels(-level);
				if (spawnorb) {
					for(int i=0;i<level*orbperlevel;i++) {
						orb =  (ExperienceOrb) location.getWorld().spawnEntity(location, EntityType.EXPERIENCE_ORB);
						orb.setExperience(orbvalue);
					}
				}
			}else {
				if (spawnorb) {
					for(int i=0;i<player.getLevel()*orbperlevel;i++) {
						orb =  (ExperienceOrb) location.getWorld().spawnEntity(location, EntityType.EXPERIENCE_ORB);
						orb.setExperience(orbvalue);
						
					}
				}
				player.setLevel(0);
			}
		return level;
	}
	
	public static int dropExpRandom(Player player, int levelmin, int levelmax, Boolean spawnorb, int orbperlevel, int orbvalue) {
			Random r = new Random();
			int level = levelmin + r.nextInt(levelmax-levelmin);
			Location location = player.getLocation();
			ExperienceOrb orb;
			if (player.getLevel() >= level) {
				player.giveExpLevels(-level);
				if (spawnorb) {
					for(int i=0;i<level*orbperlevel;i++) {
						orb =  (ExperienceOrb) location.getWorld().spawnEntity(location, EntityType.EXPERIENCE_ORB);
						orb.setExperience(orbvalue);
					}
				}
			}else {
				if (spawnorb) {
					for(int i=0;i<player.getLevel()*orbperlevel;i++) {
						orb =  (ExperienceOrb) location.getWorld().spawnEntity(location, EntityType.EXPERIENCE_ORB);
						orb.setExperience(orbvalue);
						
					}
				}
				player.setLevel(0);
			}
		return level;
	}

	public static int dropExpRate(Player player, Double levelRate, Boolean spawnorb, int orbperlevel, int orbvalue) {
		int level = (int) Math.ceil(levelRate * player.getLevel());
		player.sendMessage(""+level);
		Location location = player.getLocation();
		ExperienceOrb orb;
		if (player.getLevel() >= level) {
			player.giveExpLevels(-level);
			if (spawnorb) {
				for(int i=0;i<level*orbperlevel;i++) {
					orb =  (ExperienceOrb) location.getWorld().spawnEntity(location, EntityType.EXPERIENCE_ORB);
					orb.setExperience(orbvalue);
				}
			}
		}else {
			if (spawnorb) {
				for(int i=0;i<player.getLevel()*orbperlevel;i++) {
					orb =  (ExperienceOrb) location.getWorld().spawnEntity(location, EntityType.EXPERIENCE_ORB);
					orb.setExperience(orbvalue);
					
				}
			}
			player.setLevel(0);
		}
	return level;
}
}
