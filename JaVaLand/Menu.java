package io.github.JaVaLand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.io.IOException;
import java.util.ArrayList;

public class Menu implements Screen {

    private Texture img;
    private Sprite sprite;
    private final Core game;
    private BitmapFont font;
    private Skin skin;
    private Stage stage;

    private Music bgm;
    private Sound click_sound;

    public Menu(Core game) {
        this.game = game;
    }

    @Override
    public void show() {
        bgm = Gdx.audio.newMusic(Gdx.files.internal("audio/AB_space_level_menu.mp3"));
        click_sound = Gdx.audio.newSound(Gdx.files.internal("audio/Sound Effects - ButtonProceed.mp3"));

        bgm.setLooping(true);
        bgm.play();

        img = new Texture("bg.jpg");
        sprite = new Sprite(img);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite.setPosition(0, 0);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("abfont1.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 48;
        font = generator.generateFont(parameter);
        generator.dispose();

        skin = new Skin();
        skin.add("default", font);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.downFontColor = new Color(0.5f, 0, 0, 1);

        TextButton button1 = new TextButton("New Game", textButtonStyle);
        button1.setPosition(410, 375);

        stage.addActor(button1);
        button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Level1(game));
            }
        });

        TextButton button2 = new TextButton("Load Game", textButtonStyle);
        button2.setPosition(410, 275);
        stage.addActor(button2);

        button2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                FileHandler<GameState> file = new FileHandler<>("files/saved_games.ser");

                ArrayList<GameState> arr = null;

                try {
                    arr = file.get_content();
                }

                catch (Exception e) {
                    ;
                }

                if(arr == null){
                    game.setScreen(new MsgScreen(game));
                    return;
                }

                GameState context = arr.get(0);

                if(context.getLevel_no() == 1){
                    Level1 level1 = new Level1(game);

                    level1.set_context(context);

                    level1.setIs_loaded();

                    game.setScreen(level1);
                }

                else if(context.getLevel_no() == 2){
                    Level2 level2 = new Level2(game);

                    level2.set_context(context);

                    level2.setIs_loaded();

                    game.setScreen(level2);
                }

                else{
                    Level3 level3 = new Level3(game);

                    level3.set_context(context);

                    level3.setIs_loaded();

                    game.setScreen(level3);
                }
            }
        });

        TextButton button3 = new TextButton("Exit", textButtonStyle);
        button3.setPosition(410, 175);
        stage.addActor(button3);
        button3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        sprite.draw(game.batch);
        game.batch.end();

        stage.act(delta);
        stage.draw();

        if(Gdx.input.justTouched()){
            click_sound.play(0.9f);
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        bgm.stop();
        bgm.dispose();
        click_sound.dispose();
    }

    @Override
    public void dispose() {
        img.dispose();
        font.dispose();
        skin.dispose();
        stage.dispose();
        bgm.stop();
        bgm.dispose();
        click_sound.dispose();
    }
}
