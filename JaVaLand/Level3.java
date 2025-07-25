package io.github.JaVaLand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.Group;
import java.util.*;

import static java.util.Arrays.asList;

public class Level3 implements Screen {
    Texture img, img2, img3;
    Sprite sprite, sprite2, sprite3;
    Core game;
    private BitmapFont font;
    private Stage stage;
    private Texture buttonTexture;
    private TextButton saveButton;
    private TextButton resumeButton;
    private TextButton forfeitButton;
    private Group menuGroup;
    private boolean isMenuVisible = false;

    private Bird bird;
    private int bird_index;

    private Red red, red1;
    private Chuck chuck;
    private Bomb bomb;

    private SmallPig sp1, sp2, sp3;
    private MediumPig mp1, mp2;
    private LargePig lp1;

    private Wood w1, w2, w3, w4;
    private Glass g1, g2, g3, g4, g5, g6;
    private Metal m1, m2, m3, m4, m5;

    private final ArrayList<Bird> birds_list = new ArrayList<>();
    private final ArrayList<Pig> pigs_list = new ArrayList<>();
    private final ArrayList<Block> block_list = new ArrayList<>();

    private static final int level_no = 3;
    private boolean is_loaded = false;

    private ArrayList<Integer> count_arr = new ArrayList<>(asList(0,0));

    private Music bgm;
    private Sound click_sound, bird_sound;

    private boolean shot_from_catapult;

    public Level3(Core game) {
        this.game = game;
    }

    public GameState get_context(){
        LinkedHashMap<Bird, ArrayList<Float>> birds = new LinkedHashMap<>();
        LinkedHashMap<Pig, ArrayList<Float>> pigs = new LinkedHashMap<>();
        LinkedHashMap<Block, ArrayList<Float>> blocks = new LinkedHashMap<>();

        for(Bird bird1: birds_list){
            birds.put(bird1, bird1.get_context());
        }

        for(Pig pig1: pigs_list){
            pigs.put(pig1, pig1.get_context());
        }

        for(Block block1: block_list){
            blocks.put(block1, block1.get_context());
        }

        GameState context = new GameState(birds, pigs, blocks, level_no, bird_index, count_arr);

        return context;
    }

    public void set_context(GameState context){
        birds_list.clear();
        pigs_list.clear();
        block_list.clear();

        for(Map.Entry<Bird, ArrayList<Float>> entry: context.getBirds().entrySet()){
            entry.getKey().set_context(entry.getValue());
            entry.getKey().setPath(entry.getKey().getPath());
            birds_list.add(entry.getKey());
        }

        for(Map.Entry<Pig, ArrayList<Float>> entry: context.getPigs().entrySet()){
            entry.getKey().set_context(entry.getValue());
            entry.getKey().setPath(entry.getKey().getPath());
            pigs_list.add(entry.getKey());
        }

        for(Map.Entry<Block, ArrayList<Float>> entry: context.getBlocks().entrySet()){
            entry.getKey().set_context(entry.getValue());
            entry.getKey().setPath(entry.getKey().getPath());
            block_list.add(entry.getKey());
        }

        this.count_arr = context.getCount_arr();

        this.bird_index = context.getBird_index();
    }

    public void setIs_loaded(){
        is_loaded = true;
    }

    @Override
    public void show() {
        bgm = Gdx.audio.newMusic(Gdx.files.internal("audio/BossLevelMusic.mp3"));
        click_sound = Gdx.audio.newSound(Gdx.files.internal("audio/Sound Effects - ButtonProceed.mp3"));
        bird_sound = Gdx.audio.newSound(Gdx.files.internal("audio/Sound Effects - bird 03 flying.mp3"));

        bgm.setLooping(true);
        bgm.play();

        img = new Texture("bg.jpg");
        img2 = new Texture("planet.png");
        img3 = new Texture("slingshot.png");

        sprite = new Sprite(img);
        sprite2 = new Sprite(img2);
        sprite3 = new Sprite(img3);

        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite.setPosition(0, 0);

        sprite2.setSize(2500, 1200);
        sprite2.setPosition(-740, -980);

        sprite3.setSize(100, 100);
        sprite3.setPosition(170, 50);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("abfont1.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        font = generator.generateFont(parameter);
        generator.dispose();

        stage = new Stage(new ScreenViewport());

        buttonTexture = new Texture(Gdx.files.internal("menu.png"));
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(buttonTexture);
        ImageButton button = new ImageButton(buttonDrawable);

        button.setPosition(30, 530);
        button.setSize(45, 45);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                toggleMenu();
            }
        });

        stage.addActor(button);

        menuGroup = new Group();
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.WHITE;
        buttonStyle.downFontColor = new Color(0.5f, 0, 0, 1);

        saveButton = new TextButton("Save & Exit", buttonStyle);
        resumeButton = new TextButton("Resume", buttonStyle);
        forfeitButton = new TextButton("Forfeit", buttonStyle);

        resumeButton.setPosition(30, 480);
        saveButton.setPosition(30, 440);
        forfeitButton.setPosition(30, 400);

        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                saveGame();
            }
        });

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resumeGame();
            }
        });

        forfeitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Menu(game));
            }
        });

        menuGroup.addActor(saveButton);
        menuGroup.addActor(resumeButton);
        menuGroup.addActor(forfeitButton);

        stage.addActor(menuGroup);

        menuGroup.setVisible(false);

        Gdx.input.setInputProcessor(stage);

        if(!is_loaded) {
            red = new Red();
            red1 = new Red();
            chuck = new Chuck();
            bomb = new Bomb();

            bomb.setPosition(50, 75);
            chuck.setPosition(105, 75);
            red.setPosition(230, 150);
            red1.setPosition(160, 75);

            sp1 = new SmallPig();
            sp2 = new SmallPig();
            sp3 = new SmallPig();

            mp1 = new MediumPig();
            mp2 = new MediumPig();

            lp1 = new LargePig();

            sp1.setPosition(843, 55);
            sp2.setPosition(633, 55);
            sp3.setPosition(794, 137);
            mp1.setPosition(741, 60);
            mp2.setPosition(691, 139);
            lp1.setPosition(738, 238);

            w1 = new Wood("wood1.png");
            w1.setSize(20, 70);
            w2 = new Wood("wood.png");
            w2.setSize(115, 20);
            w3 = new Wood("wood1.png");
            w3.setSize(20, 70);
            w4 = new Wood("wood.png");
            w4.setSize(120, 20);

            g1 = new Glass("glass1.png");
            g1.setSize(20, 72);
            g2 = new Glass("glass1.png");
            g2.setSize(20, 70);
            g3 = new Glass("glass.png");
            g3.setSize(110, 20);
            g4 = new Glass("glass1.png");
            g4.setSize(20, 73);
            g5 = new Glass("glass1.png");
            g5.setSize(20, 68);
            g6 = new Glass("glass.png");
            g6.setSize(115, 19);


            m1 = new Metal("metal1.png");
            m1.setSize(20, 70);
            m2 = new Metal("metal1.png");
            m2.setSize(20, 70);
            m3 = new Metal("metal.png");
            m3.setSize(109, 20);
            m4 = new Metal("metal1.png");
            m4.setSize(20, 71);
            m5 = new Metal("metal.png");
            m5.setSize(109, 20);

            w1.setPosition(890, 75);
            w2.setPosition(845, 110);
            w3.setPosition(575, 75);
            w4.setPosition(625, 110);

            g1.setPosition(730, 153);
            g2.setPosition(640, 155);
            g3.setPosition(685, 199);
            g4.setPosition(785, 243);
            g5.setPosition(695, 242);
            g6.setPosition(740, 285);

            m1.setPosition(785, 75);
            m2.setPosition(690, 75);
            m3.setPosition(740, 110);
            m4.setPosition(840, 155);
            m5.setPosition(795, 199);

            birds_list.add(red);
            birds_list.add(red1);
            birds_list.add(chuck);
            birds_list.add(bomb);

            pigs_list.add(sp1);
            pigs_list.add(sp2);
            pigs_list.add(sp3);
            pigs_list.add(mp1);
            pigs_list.add(mp2);
            pigs_list.add(lp1);

            block_list.add(w1);
            block_list.add(w2);
            block_list.add(w3);
            block_list.add(w4);
            block_list.add(g1);
            block_list.add(g2);
            block_list.add(g3);
            block_list.add(g4);
            block_list.add(g5);
            block_list.add(g6);
            block_list.add(m1);
            block_list.add(m2);
            block_list.add(m3);
            block_list.add(m4);
            block_list.add(m5);

            bird_index = 0;
        }

        bird = birds_list.get(bird_index);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(bird);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void toggleMenu() {
        isMenuVisible = !isMenuVisible;
        menuGroup.setVisible(isMenuVisible);
    }

    private void saveGame() {
        FileHandler<GameState> file = new FileHandler<>("files/saved_games.ser");

        ArrayList<GameState> arr = new ArrayList<>();

        arr.add(get_context());

        try {
            file.add_content(arr);
        }

        catch (Exception e) {
            ;
        }

        game.setScreen(new Menu(game));
    }

    private void resumeGame() {
        isMenuVisible = false;
        menuGroup.setVisible(false);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        sprite.draw(game.batch);
        sprite2.draw(game.batch);
        sprite3.draw(game.batch);

        font.draw(game.batch, "Level 3", 480, 557);

        for(Pig pig: pigs_list){
            pig.draw(game.batch);
        }

        for(Block block: block_list){
            block.draw(game.batch);
        }

        for(Bird bird1: birds_list){
            bird1.draw(game.batch);
        }

        if (bird != null) {
            bird.draw(game.batch);
        }

        game.batch.end();

        stage.act();
        stage.draw();

        win();
        loose();

        if (bird != null) {
            bird.update(delta, pigs_list, block_list, count_arr);

            if (bird.is_done()) {
                moveToNextBird();
            }
        }

        if(Gdx.input.justTouched()){
            click_sound.play(0.9f);
        }

        if(!shot_from_catapult && bird != null && bird.isLaunched()){
            shot_from_catapult = true;
            bird_sound.play(0.75f);
        }
    }

    private void moveToNextBird() {
        if (bird_index < birds_list.size() - 1) {
            bird_index++;
            bird = birds_list.get(bird_index);
            bird.setPosition(230, 150);
            shot_from_catapult = false;

            InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
            inputMultiplexer.removeProcessor(1);
            inputMultiplexer.addProcessor(bird);
        } else {
            bird = null;
        }
    }

    private void loose(){
        if(count_arr.get(0) == birds_list.size() && count_arr.get(1) < pigs_list.size()){
            game.setScreen(new LoseScreen(game, 3));
        }
    }

    private void win(){
        if(count_arr.get(1) == pigs_list.size()){
            game.setScreen(new WinScreen(game, -1));
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        bgm.stop();
        bgm.dispose();
        click_sound.dispose();
    }

    @Override
    public void dispose() {
        img.dispose();
        img2.dispose();
        img3.dispose();
        stage.dispose();
        bgm.stop();
        bgm.dispose();
        click_sound.dispose();
    }
}
