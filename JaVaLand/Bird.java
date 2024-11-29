package io.github.JaVaLand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;
import java.util.*;

public abstract class Bird extends InputAdapter implements Serializable {
    private float speed;
    private float impact_damage;
    private transient Texture img;
    private String path;

    private transient Vector2 position;
    private transient Vector2 velocity;
    private transient Vector2 initialPosition;

    private boolean isDragging = false;
    private boolean isLaunched = false;
    private boolean isOnSlingshot = true;
    private float maxDragDistance = 75f;

    private float width, height;
    private final float gravity = 100f;

    private boolean is_done = false;

    public Bird(float speed, float impact_damage, String path) {
        this.path = path;
        this.img = new Texture(Gdx.files.internal(path));
        this.speed = speed;
        this.impact_damage = impact_damage;

        velocity = new Vector2(0, 0);
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

        arr.add(velocity.x);
        arr.add(velocity.y);

        arr.add(initialPosition.x);
        arr.add(initialPosition.y);

        return arr;
    }

    public void set_context(ArrayList<Float> arr){
        position = new Vector2(arr.get(0), arr.get(1));
        velocity = new Vector2(arr.get(2), arr.get(3));
        initialPosition = new Vector2(arr.get(4), arr.get(5));
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (isLaunched || !isOnSlingshot) {
            return false;
        }

        Vector2 touchPosition = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
        if (touchPosition.dst(position) < width / 2f) {
            isDragging = true;
            initialPosition = new Vector2(position);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (isLaunched || !isOnSlingshot) {
            return false;
        }

        if (isDragging) {
            Vector2 touchPosition = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
            Vector2 dragVector = new Vector2(touchPosition).sub(initialPosition);

            if (dragVector.x > 0) {
                dragVector.x = 0;
            }

            if (dragVector.len() > maxDragDistance) {
                dragVector.setLength(maxDragDistance);
            }

            position.set(initialPosition.x + dragVector.x, initialPosition.y + dragVector.y);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (isLaunched || !isOnSlingshot) {
            return false;
        }

        if (isDragging) {
            Vector2 releasePosition = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
            Vector2 launchVector = new Vector2(initialPosition).sub(releasePosition);

            if (launchVector.x < 0) {
                launchVector.x = 0;
            }

            velocity.set(launchVector.scl(speed));
            isLaunched = true;
            isOnSlingshot = false;
            position.set(initialPosition);
            isDragging = false;
        }
        return true;
    }

    public void update(float delta, List<Pig> pigs, List<Block> blocks, ArrayList<Integer> count_arr){
        if (!isDragging && isLaunched) {
            velocity.y -= gravity * delta;
            position.add(velocity.x * delta, velocity.y * delta);

            if(this.position.x > 1000 || this.position.y > 600 || this.position.y < 50){
                is_done = true;
                count_arr.set(0, count_arr.get(0)+1);
            }

            for (Block block : blocks) {
                if (!block.isSmashed() && position.dst(block.getPosition()) < (width / 2 + block.getWidth() / 2)) {
                    block.takeDamage(impact_damage);
                    if(!block.isSmashed()){
                        position.x=2000;
                        position.y=2000;
                        is_done = true;
                        count_arr.set(0, count_arr.get(0)+1);
                    }
                }
            }

            for (Pig pig : pigs) {
                if (!pig.isDestroyed() && position.dst(pig.getPosition()) < (width / 2 + pig.getWidth() / 2)) {
                    pig.takeDamage(impact_damage);
                    pig.setPath("injured.png");
                    if(!pig.isDestroyed()){
                        position.x=2000;
                        position.y=2000;
                        is_done = true;
                        count_arr.set(0, count_arr.get(0)+1);
                    }
                    else{
                        count_arr.set(1, count_arr.get(1)+1);
                    }
                }
            }
        }
    }

    public boolean isLaunched(){
        return isLaunched;
    }

    public boolean is_done(){
        return is_done;
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void setPosition(float x, float y) {
        initialPosition = new Vector2(x, y);
        position = new Vector2(x, y);
    }

    public void draw(SpriteBatch batch) {
        if(!is_done) {
            batch.draw(img, position.x - width / 2f, position.y - height / 2f, width, height);
        }
    }

    public void dispose() {
        img.dispose();
    }
}
