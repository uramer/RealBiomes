package diseases;

import me.uramer.realbiomes.RBPlayer;
import me.uramer.realbiomes.RealBiomes;
import me.uramer.realbiomes.State;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Anton
 * Date: 02.06.12
 * Time: 1:35
 * To change this template use File | Settings | File Templates.
 */
public abstract class Disease {
    private String name;
    private ArrayList<State> states;

    public Disease() {
    }

    public String getName() {
        return name;
    }

    public void load(String name, RealBiomes p) {
        YamlConfiguration config=new YamlConfiguration();
        try {
            config.load(new File(p.getDataFolder(),"diseases/".concat(name)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            p.log(String.format("Configuration of disease %s is incorrect!",name));
        }
        Set<String> st=config.getDefaultSection().getKeys(false);
        for(String s:st) {
            states.add(new State(this,config.getConfigurationSection(s)));
        }
    }

    public abstract void nextStage(RBPlayer p);

    public void run(Player p, int state) {
        if(states.size()>state && state>=0)
            states.get(state).run(p);
    }
}