package me.uramer.realbiomes;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Anton
 * Date: 03.06.12
 * Time: 2:19
 * To change this template use File | Settings | File Templates.
 */
public class PlayerListener implements Listener {
    public RealBiomes plugin;
    public PlayerListener(RealBiomes p) {
        plugin=p;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Entity ent=event.getEntity();
        if(ent instanceof Player) {
            String name=((Player)ent).getName();
            plugin.players.kill(name);
        }
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        String name=event.getPlayer().getName();
        RBPlayer player=plugin.players.get(name);
        if(player==null) {
            plugin.players.set(name,new RBPlayer(plugin.server.getPlayer(name)));
        }
        player.setTaskId(plugin.scheduler.scheduleAsyncRepeatingTask(plugin,new Work(plugin,player),plugin.config.delay,plugin.config.delay));

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String name=event.getPlayer().getName();
        RBPlayer player=plugin.players.get(name);
        if(player!=null) {
            plugin.scheduler.cancelTask(player.getTaskId());
        }
    }
}