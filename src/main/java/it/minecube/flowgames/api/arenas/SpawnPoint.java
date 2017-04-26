package it.minecube.flowgames.api.arenas;

import it.minecube.flowgames.api.players.MinigamePlayer;
import org.bukkit.Location;

/**
 * Created by Gio on 24/04/2017.
 */
public class SpawnPoint {

    private Arena arena;
    private Location location;

    SpawnPoint(Arena arena, Location location) {
        this.arena = arena;
        this.location = location;
    }

    public boolean isUsed() {
        return arena.locations.containsKey(location);
    }

    public void setUsed(MinigamePlayer player) {
        arena.locations.put(location, player);
    }

    public Location getLocation() {
        return location;
    }

    public static SpawnPoint nextFree(SpawnPoint[] spawnPoints) {
        for(SpawnPoint spawnPoint : spawnPoints) {
            if(!spawnPoint.isUsed())
                return spawnPoint;
        }
        return null;
    }
}
