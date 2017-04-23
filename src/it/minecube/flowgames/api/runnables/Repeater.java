package it.minecube.flowgames.api.runnables;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * Created by Gio on 23/04/2017.
 */
public class Repeater extends Task {

    private int task;

    public Repeater(Plugin plugin) {
        super(plugin);
    }

    @Override
    public Repeater exec(long ticks, FlowTask task) {
        this.task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, task, 0, ticks);
        return new Repeater(plugin);
    }

    @Override
    public void cancel() {
        Bukkit.getScheduler().cancelTask(task);
    }
}
