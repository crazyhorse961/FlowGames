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
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static it.minecube.flowgames.Configs.*;

/**
 * @author crazyhoorse961
 */
public class FlowGames extends JavaPlugin
{

    private static FlowGames instance;
    private Pool pool;

    private Map<UUID, Location[]> wandMap = new HashMap<>();
    private List<Minigame> minigames;


    public static FlowGames getInstance() {
        return instance;
    }

    public Pool getPool() {
        return pool;
    }

    public Map<UUID, Location[]> getWandMap() {
        return wandMap;
    }

    public List<Minigame> getMinigames() {
        return minigames;
    }

    @Override
    public void onEnable() {
        instance = this;
        createConfigs();
        init();
        pool = new Pool(CredentialPackageFactory.get(MYSQL.getString("mysql.username"), MYSQL.getString("mysql.password")), PoolDriver.MYSQL);
        pool.withMin(5).withMax(5).withMysqlUrl(MYSQL.getString("mysql.host"), MYSQL.getString("mysql.database"));
        pool.withProperty(PropertyFactory.connectionTimeout(50000));
        pool.build();
        Bukkit.getScheduler().runTaskAsynchronously(this, this::createTables);
    }

    public void hook(Minigame minigame){
        if(!minigame.getClass().isAssignableFrom(JavaPlugin.class)){
            throw new InvalidMinigameException(minigame.getClass().getName() + " must extends JavaPlugin");
        }
        String name = minigame.getName();
    }

    private void createConfigs() {
        copy("mysql.yml");
    }

    private void createTables(){
        try(Connection conn = pool.getConnection()){
            PreparedStatement pst = conn.prepareStatement("CREATE TABLE IF NOT EXISTS 'money'(uuid VARCHAR(255), coins INT ) ");
            pst.executeQuery();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public int registerMinigame(Minigame minigame) {
        minigames.add(minigame);
        return minigames.indexOf(minigame);
    }
}
