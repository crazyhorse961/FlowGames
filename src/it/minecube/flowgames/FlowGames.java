package it.minecube.flowgames;
/*
 * Created by nini7 on 21.04.2017.
 */

import co.insou.pool.CredentialPackageFactory;
import co.insou.pool.Pool;
import co.insou.pool.PoolDriver;
import co.insou.pool.properties.PropertyFactory;
import it.minecube.flowgames.api.ItemBuilder;
import it.minecube.flowgames.api.Minigame;
import it.minecube.flowgames.api.arenas.AreaManager;
import it.minecube.flowgames.commands.ArenaCommand;
import it.minecube.flowgames.commands.LobbyCommand;
import it.minecube.flowgames.commands.WandCommand;
import it.minecube.flowgames.exceptions.InvalidMinigameException;
import it.minecube.flowgames.listeners.LobbyListener;
import it.minecube.flowgames.listeners.WandListener;
import it.minecube.flowgames.placeholders.CoinPlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static it.minecube.flowgames.Configs.MYSQL;
import static it.minecube.flowgames.Configs.copy;

/**
 * @author crazyhoorse961
 */
public class FlowGames extends JavaPlugin
{

    private static FlowGames instance;
    private Pool pool;

    private Map<UUID, Location[]> wandMap = new HashMap<>();
    private Minigame minigame;
    private AreaManager areaManager;


    public static FlowGames getInstance() {
        return instance;
    }

    public Pool getPool() {
        return pool;
    }

    public AreaManager getAreaManager() {
        return areaManager;
    }

    public Map<UUID, Location[]> getWandMap() {
        return wandMap;
    }

    public Minigame getMinigame() {
        return minigame;
    }

    @Override
    public void onEnable() {
        instance = this;
        createConfigs();
        ItemBuilder.setInstance(new ItemBuilder());
        pool = new Pool(CredentialPackageFactory.get(MYSQL.getString("mysql.username"), MYSQL.getString("mysql.password")), PoolDriver.MYSQL);
        pool.withMin(5).withMax(5).withMysqlUrl(MYSQL.getString("mysql.host"), MYSQL.getString("mysql.database"));
        pool.withProperty(PropertyFactory.connectionTimeout(50000));
        pool.build();
        Bukkit.getScheduler().runTaskAsynchronously(this, this::createTables);
        areaManager = new AreaManager();
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            new CoinPlaceholder(this).hook();
        }
        registerCommands();
        registerListeners();

    }

    public void hook(Minigame minigame){
        if(!minigame.getClass().isAssignableFrom(JavaPlugin.class)){
            throw new InvalidMinigameException(minigame.getClass().getName() + " must extends JavaPlugin");
        }
        registerMinigame(minigame);
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

    private void registerMinigame(Minigame minigame) {
        this.minigame = minigame;
    }

    private void registerListeners(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new WandListener(), this);
        pm.registerEvents(new LobbyListener(), this);
    }

    private void registerCommands(){
        getCommand("arena").setExecutor(new ArenaCommand());
        getCommand("setlobby").setExecutor(new LobbyCommand());
        getCommand("wand").setExecutor(new WandCommand());
    }
}
