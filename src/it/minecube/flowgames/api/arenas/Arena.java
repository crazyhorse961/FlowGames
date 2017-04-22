package it.minecube.flowgames.api.arenas;

import org.bukkit.Location;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by Gio on 22/04/2017.
 */
public final class Arena {

    private Location location;
    private File file;

    Arena(Location location, File file) {
        this.location = location;
        this.file = file;
    }

    public Location[] getSpawnPoints() {
        try {
            String rawLine = Files.readAllLines(file.toPath()).get(1).replace("spawnpoints=", "");
            String[] rawSpawnPoints = rawLine.split(";");
            Location[] spawnPoints = new Location[rawSpawnPoints.length];
            for(int i = 0; i<spawnPoints.length; i++) {
                spawnPoints[i] = new Location(location.getWorld(),
                        Double.parseDouble(rawSpawnPoints[i].split(",")[0]),
                        Double.parseDouble(rawSpawnPoints[i].split(",")[1]),
                        Double.parseDouble(rawSpawnPoints[i].split(",")[2]))
                        .add(location);
            }
            return spawnPoints;
        }
        catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
