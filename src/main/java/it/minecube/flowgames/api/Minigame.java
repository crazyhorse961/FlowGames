package it.minecube.flowgames.api;

import it.minecube.flowgames.api.arenas.AbstractArena;
import it.minecube.flowgames.api.kits.Kit;
import it.minecube.flowgames.api.lobbies.Lobby;
import it.minecube.flowgames.api.players.MinigamePlayer;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.List;

/**
 * Created by nini7 on 22.04.2017.
 */
public interface Minigame {

    String getName();
    List<String> getMiniDescription();
    int getMinPlayers();
    int getMaxPlayers();
    List<MinigamePlayer> getPlayers();
    boolean addPlayer(MinigamePlayer player);
    void removePlayer(MinigamePlayer player);
    boolean containsPlayer(MinigamePlayer player);
    File getDataFolder();
    AbstractArena[] getPossibleArenas();
    AbstractArena getArena();
    Lobby getLobby();
    Kit[] getKits();
    FileConfiguration getConfig();

}
