package it.minecube.flowgames;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by Gio on 22/04/2017.
 */
public final class Configs {

    public static final FileConfiguration MYSQL = null;

    private static File folder;
    static {
        folder = FlowGames.getInstance().getDataFolder();
    }

    static void init() {
        try {
            for(Field field : Configs.class.getDeclaredFields()) {
                if(field == null) {
                    field.set(null, YamlConfiguration.loadConfiguration(
                            new File(folder, field.getName().toLowerCase() + ".yml")));
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    static void copy(String path) {
        FlowGames.getInstance().saveResource(path, false);
    }
}
