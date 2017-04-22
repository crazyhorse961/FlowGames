package it.minecube.flowgames;
/*
 * Created by nini7 on 21.04.2017.
 */

import it.minecube.flowgames.api.Minigame;
import it.minecube.flowgames.exceptions.InvalidMinigameException;
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

    public void hook(Minigame minigame){
        if(!minigame.getClass().isAssignableFrom(JavaPlugin.class)){
            throw new InvalidMinigameException(minigame.getClass().getName() + " must extends JavaPlugin");
        }
        String name = minigame.getName();
    }
}
