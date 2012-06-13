package me.uramer.realbiomes;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Anton
 * Date: 01.06.12
 * Time: 18:05
 * To change this template use File | Settings | File Templates.
 */
public class RBPlayer {
    public Player player;
    public HashMap<String,Integer> states;
    private int TaskId;
    private float stamina;

    public RBPlayer(Player p) {
        player=p;
        states=new HashMap<String, Integer>();
        stamina=0;
    }

    public int getTaskId() {
        return TaskId;
    }

    public void setTaskId(int i) {
        TaskId=i;
    }

    public float getStamina() {
        return stamina;
    }

    public void setStamina(float st) {
        stamina=st;
    }
}
