package it.minecube.flowgames;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Created by Gio on 22/04/2017.
 */
public final class Configs {

    public static final FileConfiguration MYSQL;

    private static File folder;
    static {
        folder = FlowGames.getInstance().getDataFolder();
    }

    static {
        MYSQL = YamlConfiguration.loadConfiguration(new File(FlowGames.getInstance().getDataFolder(), "mysql.yml"));
    }

    static void copy(String path) {
        FlowGames.getInstance().saveResource(path, false);
    }
}
