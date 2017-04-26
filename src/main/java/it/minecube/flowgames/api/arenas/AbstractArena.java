package it.minecube.flowgames.api.arenas;

import com.google.common.io.Files;
import it.minecube.flowgames.FlowGames;
import it.minecube.flowgames.external.Schematic;
import org.apache.commons.lang.SerializationUtils;
import org.bukkit.Location;
import org.bukkit.Material;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
        AreaManager manager = new AreaManager();
        String schem = file.getName();
        Schematic.paste(schem, location);
        Arena arena = new Arena(location, this);
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
