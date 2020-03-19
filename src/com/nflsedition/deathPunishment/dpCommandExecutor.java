package com.nflsedition.deathPunishment;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
					}else {
						p.sendMessage("§c[§f死亡惩罚插件§c]§f:§c你的权限不足！");
					}
				}else{
					sender.sendMessage("§c这个命令只有玩家能用！");
				}
			}
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
			return true;
		}
		return false;
    }
}
