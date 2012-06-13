package me.uramer.realbiomes.effects;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Anton
 * Date: 02.06.12
 * Time: 1:35
 * To change this template use File | Settings | File Templates.
 */
public interface Effect {
    public void run(Player player, ConfigurationSection param);
}
