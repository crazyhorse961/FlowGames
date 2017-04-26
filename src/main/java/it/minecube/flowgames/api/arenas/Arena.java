package it.minecube.flowgames.api.arenas;

import it.minecube.flowgames.api.players.MinigamePlayer;
import org.bukkit.Location;
import org.bukkit.block.Chest;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.HashMap;

/**
 * Created by Gio on 22/04/2017.
 */
public final class Arena implements Serializable {

    private Location location;
    private AbstractArena arena;

    HashMap<Location, MinigamePlayer> locations;

    Arena(Location location, AbstractArena arena) {
        this.location = location;
        this.arena = arena;
        this.locations = new HashMap<>();
    }

    public SpawnPoint[] getSpawnPoints() {
        try {
            String rawLine = Files.readAllLines(arena.getFile().toPath()).get(1).replace("spawnpoints=", "");
            String[] rawSpawnPoints = rawLine.split(";");
            SpawnPoint[] spawnPoints = new SpawnPoint[rawSpawnPoints.length];
            for(int i = 0; i<spawnPoints.length; i++) {
                spawnPoints[i] = new SpawnPoint(this, new Location(location.getWorld(),
                        Double.parseDouble(rawSpawnPoints[i].split(",")[0]),
                        Double.parseDouble(rawSpawnPoints[i].split(",")[1]),
                        Double.parseDouble(rawSpawnPoints[i].split(",")[2]))
                        .add(location));
            }
            return spawnPoints;
        }
        catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Chest[] getChests() {
        try {
            String rawLine = Files.readAllLines(arena.getFile().toPath()).get(2).replace("chests=", "");
            String[] rawChests = rawLine.split(";");
            Chest[] chests = new Chest[rawChests.length];
            for(int i = 0; i<rawChests.length; i++) {
                chests[i] = (Chest) location.getWorld().getBlockAt(new Location(location.getWorld(),
                        Double.parseDouble(rawChests[i].split(",")[0]),
                        Double.parseDouble(rawChests[i].split(",")[1]),
                        Double.parseDouble(rawChests[i].split(",")[2]))
                        .add(location));
            }
            return chests;
        }
        catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public AbstractArena getAbstractArena() {
        return arena;
    }
}
