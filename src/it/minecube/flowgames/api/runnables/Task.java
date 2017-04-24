package it.minecube.flowgames.api.runnables;

import org.bukkit.plugin.Plugin;

/**
 * Created by Gio on 23/04/2017.
 */
abstract class Task {

    Plugin plugin;
    boolean cancelled;
    int task;

    Task(Plugin plugin) {
        this.plugin = plugin;
    }

    public abstract Task exec(long ticks, FlowTask task);
    public abstract void cancel();

    public boolean isCancelled() {
        return cancelled;
    }
}
