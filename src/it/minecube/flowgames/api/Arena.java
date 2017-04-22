package it.minecube.flowgames.api;

import org.bukkit.Location;
import org.bukkit.Material;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Gio on 22/04/2017.
 */
public abstract class Arena {

    public abstract int getMinPlayers();
    public abstract int getMaxPlayers();
    public abstract Location[] getSpawnPoints();

    public void paste(Location location, File file) {
        AreaManager manager = new AreaManager();
        HashMap<Location, Material> blocks = manager.parse(file);

    }
}
