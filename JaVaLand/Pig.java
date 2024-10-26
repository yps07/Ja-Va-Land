package io.github.JaVaLand;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Pig {
    private float health_points;
    private Texture img;
    private Sprite sprite;
    private Core game;

    public Pig(Core game, float width, float height, float health_points, Texture img) {
        this.img = img;
        sprite = new Sprite(img);
        this.health_points = health_points;
        this.game = game;
        sprite.setSize(width, height);
    }

    public void health_reduction(){

    }

    public boolean is_dead(){
        return (health_points == 0);
    }

    public void setPosition(float x, float y){
        sprite.setPosition(x, y);
    }

    public void draw(){
        sprite.draw(game.batch);
    }

    public void dispose(){
        img.dispose();
    }
}
