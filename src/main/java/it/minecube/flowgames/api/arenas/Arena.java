package it.minecube.flowgames.api.arenas;

import it.minecube.flowgames.api.players.MinigamePlayer;
import org.bukkit.Location;
import org.bukkit.block.Chest;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Gio on 22/04/2017.
 */
public final class Arena implements Serializable {

    private Location location;
    private AbstractArena arena;
    private SpawnPoint[] spawnPoints;
    private Chest[] chests;

    HashMap<Location, MinigamePlayer> locations;

    Arena(Location location, AbstractArena arena) {
        this.location = location;
        this.arena = arena;
        this.locations = new HashMap<>();
    }

    public SpawnPoint[] getSpawnPoints() {
        return spawnPoints;
    }

    public void setSpawnPoints(SpawnPoint[] spawnPoints) {
        this.spawnPoints = spawnPoints;
    }

    public Chest[] getChests() {
        return chests;
    }

    public void setChests(Chest[] chests) {
        this.chests = chests;
    }

    public AbstractArena getAbstractArena() {
        return arena;
    }
}
