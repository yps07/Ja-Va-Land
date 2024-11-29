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

public class WinScreen implements Screen {

    private Texture img;
    private final int level;
    private Sprite sprite;
    private final Core game;
    private BitmapFont font, font1;
    private Skin skin, skin1;
    private Stage stage;

    private Music bgm;
    private Sound click_sound, bird_sound;

    public WinScreen(Core game, int level) {
        this.game = game;
        this.level = level;
    }

    @Override
    public void show() {
        bgm = Gdx.audio.newMusic(Gdx.files.internal("audio/Sound Effects - level clear military a1.mp3"));
        click_sound = Gdx.audio.newSound(Gdx.files.internal("audio/Sound Effects - ButtonProceed.mp3"));

        bgm.setLooping(true);
        bgm.play();

        img = new Texture("bg.jpg");
        sprite = new Sprite(img);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite.setPosition(0, 0);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("abfont1.ttf")); // Replace with your TTF file
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 40;
        font = generator.generateFont(parameter);

        FreeTypeFontParameter parameter1 = new FreeTypeFontParameter();
        parameter1.size = 80;
        font1 = generator.generateFont(parameter1);
        generator.dispose();

        skin = new Skin();
        skin.add("default", font);

        skin1 = new Skin();
        skin1.add("default", font1);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.downFontColor = new Color(0.5f, 0, 0, 1);

        TextButton next = new TextButton("Next", textButtonStyle);
        next.setPosition(580, 210);

        stage.addActor(next);
        Gdx.input.setInputProcessor(stage);

        next.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(level == 2){
                    game.setScreen(new Level2(game));
                }

                else if(level == 3){
                    game.setScreen(new Level3(game));
                }

                else{
                    game.setScreen(new Menu(game));
                }
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        sprite.draw(game.batch);

        font1.draw(game.batch, "YOU WON!", 340, 350);

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
