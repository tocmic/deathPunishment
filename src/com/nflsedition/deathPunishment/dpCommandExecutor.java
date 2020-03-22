package com.nflsedition.deathPunishment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class dpCommandExecutor implements CommandExecutor{

    private final Plugin dp;

    public dpCommandExecutor(Plugin plugin) {
        this.dp = plugin; // Store the plugin in situations where you need it.
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ((label.equalsIgnoreCase("deathpunishment")) || (label.equalsIgnoreCase("dp"))){ //是不是dp的侦听
			
			//"/dp help"帮助指令
			
			if ((args.length==0)||(args[0].equalsIgnoreCase("help"))) { //帮助指令
				if (sender instanceof Player) {
					Player p=(Player)sender;
					if (p.hasPermission("deathpunishment.admin.help")) {
						p.sendMessage("§c[§f死亡惩罚插件指令帮助§c]");
						p.sendMessage("c使用§f\"/deathpunishment help\"§c显示本页面");
						p.sendMessage(dp.getConfig().getString("DropSlots.Count"));
					}else{
						p.sendMessage("§c[§f死亡惩罚插件§c]§f:§c你的权限不足！");
					}
				}else{
					sender.sendMessage("§c[§f死亡惩罚插件指令帮助§c]");
					sender.sendMessage("§c使用§f\"/deathpunishment help\"§c显示本页面");
				}
			}
			
			//"/dp what"查询指令
			
			if (args[0].equalsIgnoreCase("what")) { //查看物品名称
				if (sender instanceof Player) {
					Player p=(Player)sender;
					if (p.hasPermission("deathpunishment.admin.what")) {
						p.sendMessage("§c[§f死亡惩罚插件§c]§f:§8你手上的物品id是 §f"+p.getInventory().getItemInMainHand().getType().toString());
						p.sendMessage("§c[§f死亡惩罚插件§c]§f:§8注意大小写！ §f");
						p.sendMessage("§c[§f死亡惩罚插件§c]§f:§8你的语言是 §f"+p.getLocale());
						//显示附魔的名字
						if ((p.getInventory().getItemInMainHand().hasItemMeta())&&((p.getInventory().getItemInMainHand().getItemMeta().hasEnchants()))){
							p.sendMessage("§c[§f死亡惩罚插件§c]§f:§8你手上物品的附魔为§f:"+p.getInventory().getItemInMainHand().getItemMeta().getEnchants().toString());
						}
					}else {
						p.sendMessage("§c[§f死亡惩罚插件§c]§f:§c你的权限不足！");
					}
				}else{
					sender.sendMessage("§c这个命令只有玩家能用！");
				}
			}
			
			//"/dp reload"重载配置
			
			if (args[0].equalsIgnoreCase("reload")) { //重载插件配置
				
				if (sender.hasPermission("deathpunishment.admin.reload")) {
					try {
						if (!(dp.getDataFolder().exists())){
							dp.getDataFolder().mkdir();
						}
						
						//配置文件
						File config = new File(dp.getDataFolder(),"config.yml");
						if (!(config.exists())) {
							dp.saveDefaultConfig();
						}
						dp.reloadConfig();
						sender.sendMessage("§c[§f死亡惩罚插件§c]§f:§8插件配置已重载！");
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else {
					sender.sendMessage("§c[§f死亡惩罚插件§c]§f:§c你的权限不足！");
				}
			}
			
			//"/dp lore"修改Lore
			
			if (args[0].equalsIgnoreCase("lore")) { //查看物品名称
				if (sender instanceof Player) {
					Player p=(Player)sender;
					if (p.hasPermission("deathpunishment.admin.lore")) {
						if (args.length>1) {
							if (args[1].equalsIgnoreCase("add")) {
								if (args.length>=3) {
									if (p.getInventory().getItemInMainHand().getType()!=Material.AIR) {
										if (p.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
											List<String> lorelist = p.getInventory().getItemInMainHand().getItemMeta().getLore();
											for(int i=2;i<args.length;i++) {
												lorelist.add(StringUtils.replace(args[i], "&", "§"));
											}
											ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
											meta.setLore(lorelist);
											p.getInventory().getItemInMainHand().setItemMeta(meta);
										}else {
											List<String> lorelist = new ArrayList<>();
											for(int i=2;i<args.length;i++) {
												lorelist.add(StringUtils.replace(args[i], "&", "§"));
											}
											ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
											meta.setLore(lorelist);
											p.getInventory().getItemInMainHand().setItemMeta(meta);
										}
										p.sendMessage("§c[§f死亡惩罚插件§c]§f:§c成功添加标签！");
										return true;
									}else{
										p.sendMessage("§c[§f死亡惩罚插件§c]§f:§c你是想给空气加上标签吗（迷惑）？");
										return true;
									}
								}else {
									p.sendMessage("§c[§f死亡惩罚插件§c]§f:§c格式错误！使用方式：/dp lore add <标签文本> <标签文本> <...>");
									return true;
								}
							}
							if (args[1].equalsIgnoreCase("set")) {
								if (args.length==4) {
									if (p.getInventory().getItemInMainHand().getType()!=Material.AIR) {
										if (p.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
											try {
												List<String> lorelist = p.getInventory().getItemInMainHand().getItemMeta().getLore();
												lorelist.set(Integer.valueOf(args[2])-1, StringUtils.replace(args[3], "&", "§"));
												ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
												meta.setLore(lorelist);
												p.getInventory().getItemInMainHand().setItemMeta(meta);
												p.sendMessage(String.format("§c[§f死亡惩罚插件§c]§f:§7成功将第§f%s§7行的标签修改为 %s",args[2],StringUtils.replace(args[3], "&", "§")));
											}catch (Exception e) {
												p.sendMessage("§c[§f死亡惩罚插件§c]§f:§c指令执行过程发生错误！你有正确地设置变量吗？使用方式：/dp lore set <序号> <标签文本>");
												e.printStackTrace();
												return false;
											}
										}else {
											p.sendMessage("§c[§f死亡惩罚插件§c]§f:§c物品连标签都没有，你在设置啥（迷惑）？");
										}
										return true;
									}else{
										p.sendMessage("§c[§f死亡惩罚插件§c]§f:§c你是想给空气加上标签吗（迷惑）？");
										return true;
									}
								}else {
									p.sendMessage("§c[§f死亡惩罚插件§c]§f:§c格式错误！使用方式：/dp lore set <序号> <标签文本>");
									return true;
								}
							}
							if (args[1].equalsIgnoreCase("remove")) {
								if (args.length==3) {
									if (p.getInventory().getItemInMainHand().getType()!=Material.AIR) {
										if (p.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
											try {
												List<String> lorelist = p.getInventory().getItemInMainHand().getItemMeta().getLore();
												lorelist.remove(Integer.valueOf(args[2])-1);
												ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
												meta.setLore(lorelist);
												p.getInventory().getItemInMainHand().setItemMeta(meta);
												p.sendMessage(String.format("§c[§f死亡惩罚插件§c]§f:§7成功将第§f%s§7移除",args[2]));
											}catch (Exception e) {
												p.sendMessage("§c[§f死亡惩罚插件§c]§f:§c指令执行过程发生错误！你有正确地设置变量吗？使用方式：/dp lore remove <序号>");
												e.printStackTrace();
												return false;
											}
										}else {
											p.sendMessage("§c[§f死亡惩罚插件§c]§f:§c物品连标签都没有，你在设置啥（迷惑）？");
										}
										return true;
									}else{
										p.sendMessage("§c[§f死亡惩罚插件§c]§f:§c你是想给空气加上标签吗（迷惑）？");
										return true;
									}
								}else {
									p.sendMessage("§c[§f死亡惩罚插件§c]§f:§c格式错误！使用方式：/dp lore remove <序号>");
									return true;
								}
							}
							p.sendMessage("§c[§f死亡惩罚插件§c]§f:§c格式错误！使用方式：/dp lore <add/set/remove>");
						}else {
							p.sendMessage("§c[§f死亡惩罚插件§c]§f:§c格式错误！使用方式：/dp lore <add/set/remove>");
						}
					}else {
						p.sendMessage("§c[§f死亡惩罚插件§c]§f:§c你的权限不足！");
					}
				}else{
					sender.sendMessage("§c[§f死亡惩罚插件§c]§f:§c这个命令只有玩家能用！");
				}
			}
			
			return true;
		}
		return false;
    }
}
