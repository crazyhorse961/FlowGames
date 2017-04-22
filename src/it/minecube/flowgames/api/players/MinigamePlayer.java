package it.minecube.flowgames.api.players;

import it.minecube.flowgames.api.Minigame;
import org.bukkit.entity.Player;

/**
 * Created by Gio on 22/04/2017.
 */
public class MinigamePlayer {

    private final Player player;


    public MinigamePlayer(Player player){
        this.player = player;
    }

    @Override
    public String toString() {
        return player.getName() + " " +  player.hashCode();
    }

    public Player getPlayer() {
        return player;
    }

    public void join(Minigame minigame) {
        //TODO
    }

    public int getCoins() {
        return 0; //TODO
    }

    public void setCoins(int coins) {
        //TODO
    }
}