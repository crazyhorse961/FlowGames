package it.minecube.flowgames.api.arenas;

import com.google.common.io.Files;
import it.minecube.flowgames.FlowGames;
import it.minecube.flowgames.external.Schematic;
import org.apache.commons.lang.SerializationUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gio on 22/04/2017.
 */
public abstract class AbstractArena {

    public abstract String getName();
    public abstract int getMinPlayers();
    public abstract int getMaxPlayers();

    private File file;
    private List<Arena> arenas;

    protected AbstractArena(File file) {
        this.file = file;
        this.arenas = new ArrayList<>();
    }

    protected Arena paste(Location location) {
        String schem = file.getName();
        Location[] limits = Schematic.paste(schem, location);
        Location max = limits[0], min = limits[1];
        List<SpawnPoint> spawnPoints = new ArrayList<>();
        List<Chest> chests = new ArrayList<>();

        class OrdinationUtil {
            private int[] order(int i1, int i2) {
                int max = i1 > i2 ? i1 : i2;
                int min = max == i1 ? i2 : i1;
                return new int[] {min, max};
            }
        }

        OrdinationUtil util = new OrdinationUtil();

        int minX = util.order(min.getBlockX(), max.getBlockX())[0];
        int maxX = util.order(min.getBlockX(), max.getBlockX())[1];

        int minY = util.order(min.getBlockY(), max.getBlockY())[0];
        int maxY = util.order(min.getBlockY(), max.getBlockY())[1];

        int minZ = util.order(min.getBlockZ(), max.getBlockZ())[0];
        int maxZ = util.order(min.getBlockZ(), max.getBlockZ())[1];

        Arena arena = new Arena(location, this);

        for(int x = minX; x <= maxX; x++) {
            for(int y = minY; y <= maxY; y++) {
                for(int z = minZ; z <= maxZ; z++) {
                    Block block = location.getWorld().getBlockAt(x, y, z);
                    Location loc = new Location(location.getWorld(), x + 0D, y + 0D, z + 0D);
                    if(block.getType() == Material.BEACON)
                        spawnPoints.add(new SpawnPoint(arena, loc));
                    else if(block.getType() == Material.CHEST)
                        chests.add((Chest) block);
                }
            }
        }

        arena.setSpawnPoints(spawnPoints.toArray(new SpawnPoint[spawnPoints.size()]));
        arena.setChests(chests.toArray(new Chest[chests.size()]));
        arenas.add(arena);
        return arena;
    }

    public File getFile() {
        return file;
    }

    public List<Arena> getArenas() {
        return arenas != null ? arenas : deserialize();
    }

    public void serialize(){
        String appender = "";
        for(Arena arena : getArenas()){
            appender.concat(new String(SerializationUtils.serialize(arena)) + ";");
        }
        byte[] bytes = appender.getBytes();
        File f = new File(FlowGames.getInstance().getMinigame().getDataFolder() + File.separator + "arenas", getName() + "-arenas.txt");
        try {
            f.createNewFile();
            Files.write(bytes, f);
        }catch(IOException ex){

        }
        return;
    }

    private List<Arena> deserialize(){
        List<Arena> toReturn = new ArrayList<>();
        File f = new File(FlowGames.getInstance().getMinigame().getDataFolder() + File.separator + "arenas", getName() + "-arenas.txt");
        try {
            byte[] bytes = java.nio.file.Files.readAllBytes(f.toPath());
            for(String arenas : new String(bytes).split(";")){
                Arena arena = (Arena) SerializationUtils.deserialize(arenas.getBytes());
                toReturn.add(arena);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return toReturn;
    }
}
