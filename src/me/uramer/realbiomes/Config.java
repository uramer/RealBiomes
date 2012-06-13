package me.uramer.realbiomes;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Anton
 * Date: 01.06.12
 * Time: 18:08
 * To change this template use File | Settings | File Templates.
 */
public class Config {
    int initialMapSize;
    long delay;

    public Config(FileConfiguration configuration) {
        initialMapSize=configuration.getInt("System.AveragePlayers");
        delay=configuration.getLong("System.CheckDelay")*1000;
    }
}
