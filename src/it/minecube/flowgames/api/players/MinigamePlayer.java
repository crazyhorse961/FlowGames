package it.minecube.flowgames.api.players;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import it.minecube.flowgames.FlowGames;
import it.minecube.flowgames.api.Minigame;
import it.minecube.flowgames.api.lobbies.Lobby;
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

    public void join() {
        Minigame minigame = FlowGames.getInstance().getMinigame();
        //TODO
    }

    public boolean joinLobby() {
        Lobby lobby = FlowGames.getInstance().getMinigame().getLobby();
        if(lobby.getPlayers().size() == lobby.getCapacity() || lobby.containsPlayer(this)) {
            return false;
        }
        lobby.addPlayer(this);
        player.teleport(lobby.getLocation());
        return true;
    }

    public boolean isInLobby() {
        return FlowGames.getInstance().getMinigame().getLobby().containsPlayer(this);
    }

    public boolean isInGame() {
        return FlowGames.getInstance().getMinigame().containsPlayer(this);
    }

    public int getCoins() {
        try(Connection conn = FlowGames.getInstance().getPool().getConnection()){
            PreparedStatement pst = conn.prepareStatement("SELECT 'coins' FROM 'money' WHERE 'uuid'='" + player.getUniqueId() + "'");
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
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

    public void connect(String bungeeServer){
        ByteArrayDataOutput bado = ByteStreams.newDataOutput();
        bado.writeUTF("Connect");
        bado.writeUTF(bungeeServer);
        player.sendPluginMessage(FlowGames.getInstance(), "BungeeCord", bado.toByteArray());

    }
}