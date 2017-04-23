package it.minecube.flowgames.api.lobbies;

import it.minecube.flowgames.FlowGames;
import it.minecube.flowgames.api.players.MinigamePlayer;
import it.minecube.flowgames.api.runnables.Delayer;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gio on 23/04/2017.
 */
public class Lobby {

    private Location location;
    private String name = "Waiting Lobby - No name";
    private long waitingTicks = 100*20;
    private int capacity = 10;
    private List<MinigamePlayer> players;

    private Delayer delayer;

    public Lobby(Location location) {
        this.location = location;
        this.players = new ArrayList<>();
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void startTimer() {
        delayer = new Delayer(FlowGames.getInstance()).exec(waitingTicks, this::transferToGame);
    }

    public void stopTimer() {
        delayer.cancel();
    }

    public boolean isTimerStopped() {
        return delayer.isCancelled();
    }

    public long getWaitingTicks() {
        return waitingTicks;
    }

    public void setWaitingTicks(long waitingTicks) {
        this.waitingTicks = waitingTicks;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<MinigamePlayer> getPlayers() {
        return players;
    }

    public void addPlayer(MinigamePlayer player) {
        players.add(player);
    }

    public void removePlayer(MinigamePlayer player) {
        players.remove(player);
    }

    public boolean containsPlayer(MinigamePlayer player) {
        return players.contains(player);
    }

    public void transferToGame() {
        players.forEach(FlowGames.getInstance().getMinigame()::addPlayer);
        players.clear();
    }
}
