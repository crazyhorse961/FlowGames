package it.minecube.flowgames.api.arenas;

import it.minecube.flowgames.FlowGames;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

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

    public void export(File file, Location start, Location end, boolean calculateAir) {
        if(!start.getWorld().getName().equals(end.getWorld().getName())) {
            throw new IllegalArgumentException("Different worlds!");
        }

        List<Block> blocks = new ArrayList<>();

        Bukkit.getScheduler().runTaskAsynchronously(FlowGames.getInstance(), () -> {
            int maxX = start.getBlockX() >= end.getBlockX() ? start.getBlockX() : end.getBlockX();
            int minX = start.getBlockX() < end.getBlockX() ? start.getBlockX() : end.getBlockX();

            int maxY = start.getBlockY() >= end.getBlockY() ? start.getBlockY() : end.getBlockY();
            int minY = start.getBlockY() < end.getBlockY() ? start.getBlockY() : end.getBlockY();

            int maxZ = start.getBlockZ() >= end.getBlockZ() ? start.getBlockZ() : end.getBlockZ();
            int minZ = start.getBlockZ() < end.getBlockZ() ? start.getBlockZ() : end.getBlockZ();

            for(int x = 0; x <= maxX - minX; x++) {
                for(int y = 0; y <= maxY - minY; y++) {
                    for(int z = 0; z <= maxZ - maxZ; z++) {
                        blocks.add(start.getWorld().getBlockAt(x, y, z));
                    }
                }
            }

            try {
                file.createNewFile();
                List<String> lines = new ArrayList<>();
                lines.add(start.getWorld().getName());
                lines.add("spawnpoints=");
                for(Block block : blocks) {
                    if(block.getType() == Material.BEACON){
                        lines.set(1,
                                lines.get(1) + block.getLocation().getX() + "," + block.getLocation().getY() + block.getLocation().getZ() + ";");
                        continue;
                    }
                    if(block.getType() != Material.AIR || calculateAir) {
                        lines.add(block.getX() + ";" + block.getY() + ";" + block.getZ() + ";" + block.getType());
                    }
                }
                Files.write(file.toPath(), lines, Charset.forName("UTF-8"));
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        });
    }

    public HashMap<Location, Material> parse(File file) {
        HashMap<Location, Material> blocks = new HashMap<>();
        Bukkit.getScheduler().runTaskAsynchronously(FlowGames.getInstance(), () -> {
            try {
                List<String> lines = Files.readAllLines(file.toPath());
                for(int i = 2; i < lines.size(); i++) {
                    String[] parts = lines.get(i).split(";");
                    blocks.put(new Location(Bukkit.getWorld(lines.get(0)),
                            Integer.parseInt(parts[0]) + 0D,
                            Integer.parseInt(parts[1]) + 0D,
                            Integer.parseInt(parts[2]) + 0D), Material.valueOf(parts[3]));
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        });
        return blocks;
    }

    public String[] getSavedAreas() {
        return new File(FlowGames.getInstance().getDataFolder() + File.separator + "arenas").list();
    }

    public boolean exists(String area) {
        for(String a : getSavedAreas()) {
            if(a.equalsIgnoreCase(area + ".txt"))
                return true;
        }
        return false;
    }
}
