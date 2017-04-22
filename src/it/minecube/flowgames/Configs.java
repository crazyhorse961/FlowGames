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
    public static void init() {
        folder = FlowGames.getInstance().getDataFolder();
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
}
