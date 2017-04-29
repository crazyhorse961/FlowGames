package it.minecube.flowgames.api.players;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.sun.istack.internal.Nullable;
import it.minecube.flowgames.FlowGames;
import it.minecube.flowgames.api.Minigame;
import it.minecube.flowgames.api.arenas.Arena;
import it.minecube.flowgames.api.arenas.SpawnPoint;
import it.minecube.flowgames.api.kits.Kit;
import it.minecube.flowgames.api.lobbies.Lobby;
import it.minecube.flowgames.api.runnables.Repeater;
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
    private Kit kit;

    private Repeater repeater;

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

    public boolean join(Arena arena) {
        Minigame minigame = FlowGames.getInstance().getMinigame();
        if(minigame.getPlayers().size() == minigame.getMaxPlayers())
            return false;
        SpawnPoint point = SpawnPoint.nextFree(arena.getSpawnPoints());
        player.teleport(point.getLocation());
        point.setUsed(this);
        return true;
    }

    public boolean joinLobby() {
        Lobby lobby = FlowGames.getInstance().getMinigame().getLobby();
        if(lobby.getPlayers().size() == lobby.getCapacity() || lobby.containsPlayer(this)) {
            return false;
        }
        lobby.addPlayer(this);
        if(lobby.isTimerStopped())
            lobby.startTimer();
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

    public Kit getKit() {
        return kit;
    }

    public void setKit(@Nullable Kit kit) {
        if(kit == null)
            player.getInventory().clear();
        this.kit = kit;

        if(kit != null) {
            this.repeater = new Repeater(FlowGames.getInstance()).exec(10,
                    () -> kit.getAbilities().forEach(player::addPotionEffect));
        }
        else this.repeater.cancel();
    }

    public boolean hasKit() {
        return this.kit != null;
    }
}