package it.minecube.flowgames.api.players;

import it.minecube.flowgames.FlowGames;
import it.minecube.flowgames.api.Minigame;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        try(Connection conn = FlowGames.getInstance().getPool().getConnection()){
            PreparedStatement pst = conn.prepareStatement("SELECT 'coins' FROM 'money' WHERE 'uuid'='" + player.getUniqueId() + "'");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                return rs.getInt("coins");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public void setCoins(int coins) {
        try(Connection conn = FlowGames.getInstance().getPool().getConnection()){
            PreparedStatement pst = conn.prepareStatement("UPDATE 'money' SET 'coins'= " + coins + " WHERE 'uuid'='" + player.getUniqueId() + "'");
            pst.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public void addCoins(int coins){
        setCoins(getCoins() + coins);
    }
    public void removeCoins(int coins){
        setCoins(getCoins() - coins);
    }
}