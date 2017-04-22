package it.minecube.flowgames.api.arenas;

import org.bukkit.Location;
import org.bukkit.Material;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Gio on 22/04/2017.
 */
public abstract class AbstractArena {

    public abstract int getMinPlayers();
    public abstract int getMaxPlayers();

    private File file;
    private HashMap<Location, Material> blocks;

    protected AbstractArena(File file) {
        this.file = file;
    }

    protected Arena paste(Location location) {
        AreaManager manager = new AreaManager();
        blocks = manager.parse(file);
        for(Location loc : blocks.keySet()) {
            Material material = blocks.get(loc);
            loc = loc.add(location);
            location.getWorld().getBlockAt(loc).setType(material);
        }
        return new Arena(location, file);
    }
}
