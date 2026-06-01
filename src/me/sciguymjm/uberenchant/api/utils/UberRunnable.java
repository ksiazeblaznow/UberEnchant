package me.sciguymjm.uberenchant.api.utils;

import me.sciguymjm.uberenchant.UberEnchant;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class UberRunnable extends BukkitRunnable {

    private static UberRunnable instance;

    private static final Map<String, UberTask> actions = new HashMap<>();

    private UberRunnable() {
        start();
    }

    public static synchronized UberRunnable getInstance() {
        if (instance == null)
            instance = new UberRunnable();
        return instance;
    }

    public synchronized static void addTask(String id, UberTask task) {
        actions.put(id, task);
    }

    public synchronized boolean isRunning() {
        return instance != null && !isCancelled();
    }

    public synchronized void start() {
        if (!isRunning())
            runTaskTimer(UberEnchant.instance(), 0, 1L);
    }

    public synchronized void stop() {
        if (isRunning())
            cancel();
    }

    @Override
    public void run() {
        synchronized (this) {
            if (!actions.isEmpty()) {
                actions.values().removeIf(action -> !action.update());
            }
        }
    }
}
