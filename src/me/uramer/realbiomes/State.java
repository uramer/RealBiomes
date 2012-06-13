package me.uramer.realbiomes;

import diseases.Disease;
import me.uramer.realbiomes.effects.Effect;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Anton
 * Date: 02.06.12
 * Time: 1:35
 * To change this template use File | Settings | File Templates.
 */
public class State {
    public Disease parent;
    private ArrayList<Effect> effects;
    private ArrayList<ConfigurationSection> params;

    public State(Disease p, ConfigurationSection c) {
        Class ef;
        Set<String> keys=c.getKeys(false);

    }

    public void run(Player p) {
       for(int i=0;i<effects.size();i++) {
           effects.get(i).run(p, params.get(i));
       }
    }

}
