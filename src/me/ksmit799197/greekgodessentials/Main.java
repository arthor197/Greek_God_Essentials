package me.ksmit799197.greekgodessentials;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	Logger logger = Logger.getLogger("Minecraft");
	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info("&3[Greek God Essentials]" + pdfFile.getName() + " v" + pdfFile.getVersion() + " Has Been &c&oDisabled!");
		getServer().getPluginManager().removePermission(
				new Permissions().canPreferomCommand);
		saveConfig();
	}

	@Override
	public void onEnable(){
		PluginManager pm = this.getServer().getPluginManager();
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info("&3[Greek God Essentials] " + pdfFile.getName() + " v" + pdfFile.getVersion() + " Has Been &a&oEnabled!");
		pm.addPermission(new Permissions().canPreferomCommand);
		saveConfig();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args){
		if(sender instanceof Player){
			Player player = (Player) sender;
			Block blockid = player.getTargetBlock(null, 10000);
			if(commandLabel.equalsIgnoreCase("setwp")){
				if(sender.hasPermission(new Permissions().canPreferomCommand)){
					getConfig().set(player.getName() + ".x", player.getLocation().getBlockX());
					getConfig().set(player.getName() + ".y", player.getLocation().getBlockY());
					getConfig().set(player.getName() + ".z", player.getLocation().getBlockZ());
					saveConfig();
				}
				else{
					player.sendMessage(ChatColor.RED + "[ERROR] You dont have Permission to do this!");
				}
			
			}else if(commandLabel.equalsIgnoreCase("tpwp")){
				int x = getConfig().getInt(player.getName() + ".x"), y = getConfig().getInt(player.getName() + ".y"), z = getConfig().getInt(player.getName() + ".z");
				player.teleport(new Location(player.getWorld(), x, y, z));
			}else if(commandLabel.equalsIgnoreCase("heal") || commandLabel.equalsIgnoreCase("h")){
				if(args.length == 0){
					player.setHealth(20);
					player.setFireTicks(0);
					player.sendMessage(ChatColor.GREEN + "You Have Been Healed by the Gods!");
				}else if(args.length == 1){
					if(player.getServer().getPlayer(args [0])!=null){
						Player targetPlayer = player.getServer().getPlayer(args [0]);
						targetPlayer.setHealth(20);
						targetPlayer.setFireTicks(0);
						targetPlayer.sendMessage(ChatColor.GREEN + " You Have Been Healed by the Gods!");
					}else{
						player.sendMessage(ChatColor.RED + "[ERROR] Sorry but the Gods cant seem to find that player!");
					}
				}	
			}else if(commandLabel.equalsIgnoreCase("feed")){
				if(args.length == 0){
					player.setFoodLevel(20);
					player.sendMessage(ChatColor.GREEN + "The gods have banished your hunger!");	
				}
				else if(args.length == 1){
					if(player.getServer().getPlayer(args [0])!=null){
						Player targetPlayer = player.getServer().getPlayer(args [0]);
						targetPlayer.setFoodLevel(20);
						player.sendMessage(ChatColor.GREEN + "Set Hunger to 20!");
						targetPlayer.sendMessage(ChatColor.GREEN + "The gods have banished your hunger!");
					}
					else{
						player.sendMessage(ChatColor.RED + "[ERROR] Sorry but the Gods cant seem to find that player!");
					}
				}	
				}else if(commandLabel.equalsIgnoreCase("starve")){
					if(args.length == 0){
						player.setFoodLevel(0);
						player.sendMessage(ChatColor.GREEN + "The Gods have Starved you!");	
					}
					else if(args.length == 1){
						if(player.getServer().getPlayer(args [0])!=null){
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							targetPlayer.setFoodLevel(0);
							player.sendMessage(ChatColor.GREEN + "Set Player's Food to 0!");
							targetPlayer.sendMessage(ChatColor.GREEN + "The Gods have Starved you!");
						}else{
							player.sendMessage(ChatColor.RED + "[ERROR] Sorry but the Gods cant seem to find that player!");
						}
					}
				}else if((commandLabel.equalsIgnoreCase("info"))){
					player.sendMessage(ChatColor.GREEN + "Block ID; " + blockid.getTypeId());
				}
				else if(commandLabel.equalsIgnoreCase("ignite")){
					if(args.length == 0){
						player.setFireTicks(500);
						player.sendMessage(ChatColor.GREEN + "The Gods have set you on fire!");	
					}
					else if(args.length == 1){
						if(player.getServer().getPlayer(args [0])!=null){
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							targetPlayer.setFireTicks(500);
							targetPlayer.sendMessage(ChatColor.GREEN + "The Gods have set you on fire!");
							player.sendMessage(ChatColor.GREEN + "Set Player On Fire!");
						}else{
							player.sendMessage(ChatColor.RED + "[ERROR] Sorry but the Gods cant seem to find that player!");
						}
					}
				}
				else if(commandLabel.equalsIgnoreCase("slap")){
					if(args.length == 0){
						player.damage(1);
						player.sendMessage(ChatColor.GREEN + "The Gods have slapped you!");	
					}
					else if(args.length == 1){
						if(player.getServer().getPlayer(args [0])!=null){
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							player.damage(1);
							targetPlayer.sendMessage(ChatColor.GREEN + "The Gods have slapped you!");
							player.sendMessage(ChatColor.GREEN + "Slapped Player!");
						}else{
							player.sendMessage(ChatColor.RED + "[ERROR] Sorry but the Gods cant seem to find that player!");
						}
					}
				}
				else if(commandLabel.equalsIgnoreCase("kill")){
					if(args.length == 0){
						player.damage(20);
						player.sendMessage(ChatColor.GREEN + "The Gods have killed you!");	
					}
					else if(args.length == 1){
						if(player.getServer().getPlayer(args [0])!=null){
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							targetPlayer.damage(20);
							targetPlayer.sendMessage(ChatColor.GREEN + "The Gods hae killed you!");
							player.sendMessage(ChatColor.GREEN + "Killed Player!");
						}else{
							player.sendMessage(ChatColor.RED + "[ERROR] Sorry but the Gods cant seem to find that player!");
						}
					}
				}
		}return false;
	}
}