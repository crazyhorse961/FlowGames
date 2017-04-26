package it.minecube.flowgames.commands;/**
 * Created by nini7 on 23.04.2017.
 */

import it.minecube.flowgames.FlowGames;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * @author crazyhoorse961
 */
public class LobbyCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            Location loc = p.getLocation();
            FileConfiguration current = FlowGames.getInstance().getMinigame().getConfig();
            current.set("lobby.x", loc.getX());
            current.set("lobby.y", loc.getY());
            current.set("lobby.z", loc.getZ());
            current.set("lobby.pitch", loc.getPitch());
            current.set("lobby.yaw", loc.getYaw());
            p.sendMessage(ChatColor.GREEN + "Lobby impostata!");
            return true;
        }else{
            commandSender.sendMessage(ChatColor.RED + "Devi essere un player per eseguire questo comando!");
            return true;
        }
    }
}
