package it.minecube.flowgames;
/*
 * Created by nini7 on 21.04.2017.
 */

import co.insou.pool.CredentialPackageFactory;
import co.insou.pool.Pool;
import co.insou.pool.PoolDriver;
import co.insou.pool.properties.PropertyFactory;
import it.minecube.flowgames.api.Minigame;
import it.minecube.flowgames.exceptions.InvalidMinigameException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * @author crazyhoorse961
 */
public class FlowGames extends JavaPlugin
{

    private static FlowGames instance;

    private Pool pool;
    private File mysqlFile;
    private FileConfiguration mysqlConfig;


    public static FlowGames getInstance() {
        return instance;
    }

    public Pool getPool() {
        return pool;
    }

    @Override
    public void onEnable() {
        instance = this;
        createMysqlFile();
        pool = new Pool(CredentialPackageFactory.get(mysqlConfig.getString("mysql.username"),mysqlConfig.getString("mysql.password")), PoolDriver.MYSQL);
        pool.withMin(5).withMax(5).withMysqlUrl(mysqlConfig.getString("mysql.host"), mysqlConfig.getString("mysql.database"));
        pool.withProperty(PropertyFactory.connectionTimeout(50000));
        pool.build();
    }

    public void hook(Minigame minigame){
        if(!minigame.getClass().isAssignableFrom(JavaPlugin.class)){
            throw new InvalidMinigameException(minigame.getClass().getName() + " must extends JavaPlugin");
        }
        String name = minigame.getName();
    }

    private void createMysqlFile(){
        if(!getDataFolder().exists()){
            getDataFolder().mkdirs();
            mysqlFile = new File(getDataFolder(), "mysql.yml");
            try {
                Files.copy(getClass().getResourceAsStream("mysql.yml"), mysqlFile.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
                mysqlConfig = YamlConfiguration.loadConfiguration(mysqlFile);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
