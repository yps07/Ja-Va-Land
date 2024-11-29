package io.github.JaVaLand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Pig implements Serializable {
    private float health_points;
    private transient Texture img;
    private String path;

    private transient Vector2 position;
    private float width, height;
    private boolean isDestroyed = false;

    public Pig(float width, float height, float health_points, String path) {
        this.path = path;
        this.img = new Texture(Gdx.files.internal(path));
        this.health_points = health_points;

        this.width = width;
        this.height = height;
    }

    public Pig(float width, float height, float health_points){
        this.health_points = health_points;

        this.width = width;
        this.height = height;
    }

    public void setPath(String path){
        this.path = path;
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
        return health_points;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

    public void takeDamage(float damage) {
        if (isDestroyed) return;

        health_points -= damage;

        if (health_points <= 0) {
            health_points = 0;
            isDestroyed = true;
        }
    }

    public void draw(SpriteBatch batch) {
        if (!isDestroyed) {
            batch.draw(img, position.x - width / 2, position.y - height / 2, width, height);
        }
    }

    public void dispose(){
        img.dispose();
    }
}
