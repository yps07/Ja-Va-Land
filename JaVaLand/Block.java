package io.github.JaVaLand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Block implements Serializable {
    private float durability;
    private transient Texture img;
    private String path;

    private transient Vector2 position;
    private float width, height;
    private boolean isSmashed;

    public Block(float durability, String path) {
        this.path = path;
        this.img = new Texture(Gdx.files.internal(path));
        this.durability = durability;
    }

    public Block(float durability){
        this.durability = durability;
    }

    public void setPath(String path){
        img = new Texture(Gdx.files.internal(path));
    }

    public String getPath(){
        return path;
    }

    public ArrayList<Float> get_context(){
        ArrayList<Float> arr = new ArrayList<>();

        arr.add(position.x);
        arr.add(position.y);

        return arr;
    }

    public void set_context(ArrayList<Float> arr){
        position = new Vector2(arr.get(0), arr.get(1));
    }

    public void setSize(float x, float y){
        this.width = x;
        this.height = y;
    }

    public void setPosition(float x, float y){
        position = new Vector2(x, y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getHealth() {
        return durability;
    }

    public boolean isSmashed() {
        return isSmashed;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

    public void takeDamage(float damage) {
        if (isSmashed) return;

        durability -= damage;

        if (durability <= 0) {
            durability = 0;
            isSmashed = true;
        }
    }

    public void draw(SpriteBatch batch) {
        if (!isSmashed) {
            batch.draw(img, position.x - width / 2, position.y - height / 2, width, height);
        }
    }

    public void dispose(){
        img.dispose();
    }
}
