package it.minecube.flowgames.listeners;

import it.minecube.flowgames.FlowGames;
import it.minecube.flowgames.api.Minigame;
import it.minecube.flowgames.api.players.MinigamePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Gio on 23/04/2017.
 */
public class QuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Minigame minigame = FlowGames.getInstance().getMinigame();
        MinigamePlayer minigamePlayer = new MinigamePlayer(e.getPlayer());

        if(minigamePlayer.isInLobby())
            minigame.getLobby().removePlayer(minigamePlayer);
        else if(minigamePlayer.isInGame())
            minigame.removePlayer(minigamePlayer);
    }
}
