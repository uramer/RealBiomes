package me.uramer.realbiomes;

import diseases.Disease;
import me.uramer.realbiomes.effects.Effect;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Anton
 * Date: 01.06.12
 * Time: 17:51
 * To change this template use File | Settings | File Templates.
 */
public class RealBiomes extends JavaPlugin {
    HashMap<String,Disease> diseases;
    HashMap<String,Effect> effects;
    Logger logger=this.getLogger();
    Config config;
    PlayerBase players;
    Server server;
    BukkitScheduler scheduler;
    PlayerListener listener;

    public void log(String s) {
        logger.info(s);
    }

    public void load(String s) {
        URLClassLoader loader;
        File[] files=(new File(getDataFolder(),s)).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.matches("^.*?\\.jar$");
            }
        });
        if(files==null) {
            loader=null;
            log("There is no effects to load!");
        }
        else {
            URL[] eff=new URL[files.length];
            for(int i=0;i<files.length;i++)
                try {
                    eff[i]=files[i].toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            URLClassLoader child = null;
            loader=new URLClassLoader(eff, this.getClass().getClassLoader());
            for(File f:files) {
                try {
                    if(s=="effects")
                        effects.put(f.getName(),(Effect)loader.loadClass("me.uramer.realbiomes.effects".concat(f.getName())).newInstance());
                    else {
                        diseases.put(f.getName(),(Disease)loader.loadClass("me.uramer.realbiomes.diseases".concat(f.getName())).newInstance());
                        diseases.get(f.getName()).load(f.getName(), this);
                    }
                } catch (ClassNotFoundException e) {
                    log(String.format("%s %s wasn't loaded!", f.getName(), s));
                } catch (InstantiationException e) {
                    log(String.format("%s %s is not correct!",f.getName(),s));
                } catch (IllegalAccessException e) {
                    log(String.format("%s %s is not accessable!", f.getName(), s));
                }
            }
            log("All effects loaded!");
        }

    }

    @Override
    public void onEnable() {
        config=new Config(this.getConfig());
        players=new PlayerBase(config);
        server=getServer();
        scheduler=server.getScheduler();
        diseases= new HashMap<String, Disease>();
        listener=new PlayerListener(this);
        server.getPluginManager().registerEvents(listener,this);
        load("effects");
        load("diseases");
        log("Enabled!");
    }

    @Override
    public void onDisable() {
        log("Disabled");
    }
}
