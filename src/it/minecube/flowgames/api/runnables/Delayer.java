package it.minecube.flowgames.api.runnables;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * Created by Gio on 23/04/2017.
 */
public class Delayer extends Task {

    private int task;
    boolean cancelled;

    public Delayer(Plugin plugin) {
        super(plugin);
    }

    public Delayer exec(long ticks, FlowTask task) {
        this.task = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, task);
        return new Delayer(plugin);
    }

    @Override
    public void cancel() {
        Bukkit.getScheduler().cancelTask(task);
        cancelled = true;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }
}
