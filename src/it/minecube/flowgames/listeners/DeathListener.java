package it.minecube.flowgames.listeners;/**
 * Created by nini7 on 24.04.2017.
 */

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * @author crazyhoorse961
 */
public class DeathListener implements Listener
{

    @EventHandler
    public void onDeath(PlayerDeathEvent ev){
        ev.getEntity().setGameMode(GameMode.SPECTATOR);
    }
}
