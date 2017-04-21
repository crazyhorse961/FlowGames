package main.java.it.minecube.flowgames;/**
 * Created by nini7 on 21.04.2017.
 */

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author crazyhoorse961
 */
public class FlowGames extends JavaPlugin
{

    private static FlowGames flowGames;

    public static FlowGames getFlowGames() {
        return flowGames;
    }

    @Override
    public void onEnable(){
        flowGames = this;
    }
}
