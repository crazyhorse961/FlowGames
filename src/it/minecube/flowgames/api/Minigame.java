package it.minecube.flowgames.api;

import org.bukkit.entity.Player;

/**
 * Created by nini7 on 22.04.2017.
 */
public interface Minigame
{
    String getName();
    Player[] getPlayers();
    int getMinPlayers();
    int getMaxPlayers();
    Arena[] getPossibleArenas();
    Arena getArena();
    void setArena(Arena arena);
}
