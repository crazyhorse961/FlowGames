package it.minecube.flowgames.commands;/**
 * Created by nini7 on 22.04.2017.
 */

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * @author crazyhoorse961
 */
public class WandCommand implements CommandExecutor{


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            ItemStack wand = new ItemStack(Material.GOLD_AXE);
            ItemMeta wandMeta = wand.getItemMeta();
            wandMeta.setDisplayName(ChatColor.GREEN + "Minigame Wand");
            wandMeta.setLore(Arrays.asList(ChatColor.GRAY + "Usa quest'ascia per impostare i bordi dell'arena"));
            wand.setItemMeta(wandMeta);
            p.getInventory().addItem(wand);
            p.sendMessage(ChatColor.GREEN + "Seleziona i vertici dell'arena, poi scrivi /arena export <nome> per esportare l'area!");
            return true;
        }else{
            commandSender.sendMessage(ChatColor.RED + "Non puoi usare la console per questo comando!");
            return true;
        }
    }
}
