package me.uramer.realbiomes;

import java.io.*;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Anton
 * Date: 02.06.12
 * Time: 0:53
 * To change this template use File | Settings | File Templates.
 */
public class PlayerBase {
    private HashMap<String,RBPlayer>  map;
    private RealBiomes plugin;

    private static final String filename="PlayerBase.bs";

    public PlayerBase(RealBiomes plugin, Config config) {
     map=new HashMap<String, RBPlayer>(config.initialMapSize);
    }

    public RBPlayer get(String name) {
        if(map.containsKey(name)) return map.get(name);
        else return null;
    }

    public void kill(String name) {
        if(map.containsKey(name)) {
            RBPlayer p=map.get(name);
            for(int i=0;i<p.states.size();i++) {
                p.states.put(plugin.diseases.get(i).getName(),0);
            }
        }
    }

    public void set(String name,RBPlayer player) {
        map.put(name,player);
    }

    static public PlayerBase load() {
        ObjectInputStream os=null;
        PlayerBase t=null;
        try {
            os=new ObjectInputStream(new FileInputStream(filename));
            t=(PlayerBase) os.readObject();
            os.close();
        } catch (IOException e) {
            t=null;
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            t=null;
            e.printStackTrace();
        }
        return t;
    }

    public void save() {
        ObjectOutputStream os=null;
        try {
            os=new ObjectOutputStream(new FileOutputStream(filename));
            os.writeObject(this);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
