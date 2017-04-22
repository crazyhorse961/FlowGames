package it.minecube.flowgames.commands;/**
 * Created by nini7 on 22.04.2017.
 */

import it.minecube.flowgames.FlowGames;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * @author crazyhoorse961
 */
public class ArenaCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(ChatColor.GREEN + "You should be a player to execute this command!");
            return true;
        }
        Player p = (Player) commandSender;
        switch(strings.length){
            case 0:
                //Help menu
            case 1:
                //TODO Comandi
            case 2:
                switch(strings[0]){
                    case "export":
                        if(FlowGames.getInstance().getWandMap().get(p.getUniqueId()).length < 1){
                            p.sendMessage(ChatColor.GREEN + "Seleziona i bordi con l'ascia!");
                            return true;
                        }
                        File f = new File(Bukkit.getWorldContainer() + File.separator + "plugins" + File.separator + FlowGames.getInstance().getMinigames().get(FlowGames.getInstance().getMinigameNumber()).getName()
                        + File.separator + "arenas", strings[1] + ".txt");
                    FlowGames.getInstance().getAreaManager().export(f, FlowGames.getInstance().getWandMap().get(p.getUniqueId())[0], FlowGames.getInstance().getWandMap().get(p.getUniqueId())[1], true);
                    p.sendMessage(ChatColor.GREEN + "Arena exported!");
                    return true;
                }
        }
        return true;
    }
}
