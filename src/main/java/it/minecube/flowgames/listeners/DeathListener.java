package it.minecube.flowgames.listeners;/**
 * Created by nini7 on 24.04.2017.
 */

import it.minecube.flowgames.api.ItemBuilder;
import it.minecube.flowgames.api.Minigame;
import it.minecube.flowgames.api.players.MinigamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author crazyhoorse961
 */
public class DeathListener implements Listener
{

    List<String> toTrack;

    public DeathListener() {
        toTrack = new ArrayList<>();
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent ev){
        Player p = ev.getEntity();
        p.setGameMode(GameMode.ADVENTURE);
        p.setFlying(true);
        for (Player pls : Bukkit.getOnlinePlayers()) {
            if(!p.equals(pls)){
                pls.hidePlayer(p);
            }
        }
        ItemStack slime = ItemBuilder.type(Material.SLIME_BALL).withName(ChatColor.GREEN + "Cliccami per tornare all'hub").lore("").build();
        p.getInventory().setHeldItemSlot(8);
        /**
         * @Lucatro usa ancora la 1.8 ;(
         */
        p.setItemInHand(slime);
        toTrack.add(p.getName());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent ev){
        if(toTrack.contains(ev.getPlayer().getName())){
            ev.setCancelled(true);
            if(ev.getItem() != null && ev.getItem().getType() == Material.SLIME_BALL){
                new MinigamePlayer(ev.getPlayer()).connect("hub");
                toTrack.remove(ev.getPlayer().getName());
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent ev){
        if(toTrack.contains(ev.getPlayer().getName())){
            ev.setCancelled(true);
        }
    }

    @EventHandler
    public void onRemove(BlockBreakEvent ev){
        if(toTrack.contains(ev.getPlayer().getName())){
            ev.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent ev){
        if(toTrack.contains(ev.getPlayer().getName())){
            ev.setCancelled(true);
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent ev){
        if(ev.getDamager() instanceof Player && toTrack.contains(ev.getDamager().getName())){
            ev.setCancelled(true);
        }
    }
}
