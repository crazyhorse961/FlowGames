package it.minecube.flowgames.listeners;/**
 * Created by nini7 on 22.04.2017.
 */

import it.minecube.flowgames.FlowGames;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Map;
import java.util.UUID;

/**
 * @author crazyhoorse961
 */
public class WandListener implements Listener
{

    private Map<UUID, Location[]> wandMap = FlowGames.getInstance().getWandMap();

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().contains("Minigame Wand")){
            if(e.getAction().name().contains("LEFT")){
                FlowGames.getInstance().getWandMap().put(p.getUniqueId(),new Location[]{ e.getClickedBlock().getLocation(), wandMap.get(p.getUniqueId()) == null ? null : wandMap.get(p.getUniqueId())[1]});
                p.sendMessage(ChatColor.GREEN + "Position 1 selected !");
                e.setCancelled(true);
                return;
            }else{
                FlowGames.getInstance().getWandMap().put(p.getUniqueId(),new Location[]{wandMap.get(p.getUniqueId()) == null ? null : wandMap.get(p.getUniqueId())[0], e.getClickedBlock().getLocation()});
                p.sendMessage(ChatColor.GREEN + "Position 2 selected !");
                e.setCancelled(true);
                return;
            }
        }
    }
}
