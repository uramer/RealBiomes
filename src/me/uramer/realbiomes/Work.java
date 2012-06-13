package me.uramer.realbiomes;

import com.google.gson.internal.Pair;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Anton
 * Date: 02.06.12
 * Time: 1:23
 * To change this template use File | Settings | File Templates.
 */
public class Work implements Runnable {
    private RBPlayer player;
    private RealBiomes plugin;

    public Work(RealBiomes plu, RBPlayer p) {
        player=p;
        plugin=plu;
    }

    @Override
    public void run() {
        for(int i=0;i<player.states.size();i++) {
            plugin.diseases.get(i).run(player.player,player.states.get(i));
        }
    }
}