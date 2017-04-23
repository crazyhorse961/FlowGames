package it.minecube.flowgames.api;

import it.minecube.flowgames.api.arenas.AbstractArena;
import it.minecube.flowgames.api.kits.Kit;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;

/**
 * Created by nini7 on 22.04.2017.
 */
public interface Minigame {

    String getName();
    List<String> getDescription();
    File getDataFolder();
    Player[] getPlayers();
    AbstractArena[] getPossibleArenas();
    AbstractArena getArena();
    void setArena(AbstractArena arena);
    Kit[] getKits();
}
