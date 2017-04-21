package it.minecube.flowgames;
/*
 * Created by nini7 on 21.04.2017.
 */

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author crazyhoorse961
 */
public class FlowGames extends JavaPlugin
{

    private static FlowGames instance;

    public static FlowGames getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
    }
}
