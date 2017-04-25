package it.minecube.flowgames.api.arenas;

import it.minecube.flowgames.FlowGames;
import it.minecube.flowgames.external.Schematic;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gio on 22/04/2017.
 */
public class AreaManager {

    public void export(Player player, String name) {
        Schematic.save(player, name);
    }

    public String[] getSavedAreas() {
        return new File(FlowGames.getInstance().getDataFolder() + File.separator + "schematics").list();
    }

    public boolean exists(String area) {
        for(String a : getSavedAreas()) {
            if(a.equalsIgnoreCase(area + ".schematic"))
                return true;
        }
        return false;
    }
}
