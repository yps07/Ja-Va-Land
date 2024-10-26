package io.github.JaVaLand;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Bird {
    private float speed;
    private float impact_damage;
    private Texture img;
    private Sprite sprite;
    private Core game;

    public Bird(Core game, float speed, float impact_damage, Texture img){
        this.img = img;
        sprite = new Sprite(img);
        this.speed = speed;
        this.impact_damage = impact_damage;
        this.game = game;
    }

    public void launch(){
    }

    public void setSize(float x, float y){
        sprite.setSize(x, y);
    }

    public void setPosition(float x, float y){
        sprite.setPosition(x, y);
    }

    public void draw(){
        sprite.draw(game.batch);
    }

    public Vector2 trajectory(){
        return null;
    }

    public void dispose(){
        img.dispose();
    }
}
