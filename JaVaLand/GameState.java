package io.github.JaVaLand;

import java.io.Serializable;
import java.util.*;

public class GameState implements Serializable {
    private LinkedHashMap<Bird, ArrayList<Float>> birds;
    private LinkedHashMap<Pig, ArrayList<Float>> pigs;
    private LinkedHashMap<Block, ArrayList<Float>> blocks;

    private ArrayList<Integer> count_arr;

    private int level_no;
    private int bird_index;

    public GameState(LinkedHashMap<Bird, ArrayList<Float>> birds, LinkedHashMap<Pig, ArrayList<Float>> pigs,  LinkedHashMap<Block, ArrayList<Float>> blocks, int level_no, int bird_index, ArrayList<Integer> count_arr){
        this.birds = birds;
        this.pigs = pigs;
        this.blocks = blocks;

        this.level_no = level_no;
        this.bird_index = bird_index;

        this.count_arr = count_arr;
    }

    public ArrayList<Integer> getCount_arr(){
        return count_arr;
    }

    public LinkedHashMap<Bird, ArrayList<Float>> getBirds(){
        return this.birds;
    }

    public LinkedHashMap<Pig, ArrayList<Float>> getPigs(){
        return this.pigs;
    }

    public  LinkedHashMap<Block, ArrayList<Float>> getBlocks(){
        return this.blocks;
    }

    public int getLevel_no(){
        return this.level_no;
    }

    public int getBird_index(){
        return this.bird_index;
    }
}
