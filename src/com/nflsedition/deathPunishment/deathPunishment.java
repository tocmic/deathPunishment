package com.nflsedition.deathPunishment;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

public class deathPunishment extends JavaPlugin{
	
	public static HashMap<String, String> loghash = new HashMap<>();
	public static HashMap<String, JSONObject> langhash = new HashMap<>();
	
	public void onEnable() {
		if (!(getDataFolder().exists())){
			this.getDataFolder().mkdir();
		}
		
		//配置文件
		File config = new File(getDataFolder(),"config.yml");
		if (!(config.exists())) {
			this.saveDefaultConfig();
		}
		reloadConfig();
		
		//玩家死亡世界数据
		
		File logfile = new File(getDataFolder(),"log.bin");
		if (logfile.exists()) {
			loghash = dpFile.loadMap(getDataFolder().getPath()+"\\log.bin");
		}else {
			dpFile.saveMap(loghash, getDataFolder().getPath()+"\\log.bin");
		}
		this.getLogger().info("数据文件"+getDataFolder().getPath()+"\\log.bin"+"已挂载");
		
		//语言文件
		
		File langfile = new File(getDataFolder(),"lang");
		if (!(langfile.exists())) {
			langfile.mkdir();
			InputStream inputStream = getResource("zh_cn.lang");
			Path path = Paths.get(getDataFolder().toString(),"\\lang\\zh_cn.lang");
			try {
				Files.copy(inputStream,path);
				inputStream.close();
			}catch (Exception e) {
				getLogger().info("Error occured");;
				e.printStackTrace();
			}
			inputStream = getResource("en_gb.lang");
			path = Paths.get(getDataFolder().toString(),"\\lang\\en_gb.lang");
			try {
				Files.copy(inputStream,path);
				inputStream.close();
			}catch (Exception e) {
				getLogger().info("Error occured");;
				e.printStackTrace();
			}
			inputStream = getResource("zh_tw.lang");
			path = Paths.get(getDataFolder().toString(),"\\lang\\zh_tw.lang");
			try {
				Files.copy(inputStream,path);
				inputStream.close();
			}catch (Exception e) {
				getLogger().info("Error occured");;
				e.printStackTrace();
			}
		}
		langhash = dpFile.loadLangs(getDataFolder().getPath()+"\\lang");
		
		//注册命令执行类
		this.getCommand("deathpunishment").setExecutor(new dpCommandExecutor(this));
		//注册事件
		this.getServer().getPluginManager().registerEvents(new dpPlayerDeathEventListener(this), this);
		this.getServer().getPluginManager().registerEvents(new dpPlayerRespawnEventListener(this), this);
		
		getLogger().info("deathPunishment死亡惩罚插件已启用!");
	}
	public void onDisable() {
		if (!(getDataFolder().exists())){
			this.getDataFolder().mkdir();
		}
		dpFile.saveMap(loghash, getDataFolder().getPath()+"\\log.bin");
		loghash.clear();
		langhash.clear();
		HandlerList.unregisterAll();
	}
}
