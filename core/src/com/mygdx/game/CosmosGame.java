package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

import screen.GameScreen;
import screen.MapScreen;
import screen.MenuScreen;
import screen.ShipScreen;

public class CosmosGame extends Game {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 480;
    public int WORLD_WIDTH = 800;
    public int WORLD_HEIGHT = 480;
    public static final int SHIP_WIDTH = 800;
    public static final int SHIP_HEIGHT = 480;
    SpriteBatch batch;
    private MenuScreen menuScreen;
    private GameScreen gameScreen;
    private ShipScreen shipScreen;
    private MapScreen mapScreen;
    private int countOre = 0;
    private int countMoney = 0;
    private int countDiamond = 0;
    public String k = "Ship", to = "Game";
    public boolean build = false;
    private int IdScene;
    private int countTree = 0;
    public int[] X = new int[6];
    public int[] trX = new int[4];
    public boolean[] mined = new boolean[6];
    public boolean[] treed = new boolean[4];

    public boolean hasAr = false;
    public  Music sound;
    public int respawnX=400;
    private Random random;

    @Override
    public void create() {
        random = new Random();
        sound  = Gdx.audio.newMusic(Gdx.files.internal("back.mp3"));
        sound.setLooping(true);
        int x = random.nextInt((1500 - 500) + 1) + 500;
        for (int i = 0; i < 6; i++) {
            X[i] = x;
            x += random.nextInt((1500 - 500) + 1) + 500;
            if (x >= 5200) {
                x = 50000;
            }
        }
        x = random.nextInt((923 - 307) + 1) + 307;
        for (int i = 0; i < 4; i++) {
            trX[i] = 5600 + x;
            x += random.nextInt((923 - 307) + 1) + 307;
        }
        menuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this);
        shipScreen = new ShipScreen(this);
        mapScreen = new MapScreen(this);
        batch = new SpriteBatch();


        this.setScreen(menuScreen);
        sound.play();
        IdScene = 0;
    }

    public SpriteBatch getSpriteBatch() {
        return batch;
    }

    public void changeScreen(String screenName) {
        if (screenName.equals("Menu")) {
            sound.play();
            gameScreen.music.stop();
            IdScene = 0;
            setScreen(menuScreen);

        } else if (screenName.equals("Game")) {

            IdScene = 1;
            setScreen(gameScreen);

        } else if (screenName.equals("Ship")) {
            sound.stop();
            IdScene = 2;
            setScreen(shipScreen);

        } else if (screenName.equals("Map")) {
            IdScene = 3;
            setScreen(mapScreen);
        }
    }

    public int getIdScene() {
        return IdScene;
    }

    public int getCountTree() {
        return countTree;
    }

    public void setCountTree(int countTree) {
        this.countTree = countTree;
    }

    public int getCountDiamond() {
        return countDiamond;
    }

    public void setCountDiamond(int countDiamond) {
        this.countDiamond = countDiamond;
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        menuScreen.dispose();
        gameScreen.dispose();
        shipScreen.dispose();
        mapScreen.dispose();
        sound.dispose();
    }

    public void setWORLD_WIDTH(int WORLD_WIDTH) {
        this.WORLD_WIDTH = WORLD_WIDTH;
    }

    public int getCountOre() {
        return countOre;
    }

    public void setCountOre(int countOre) {
        this.countOre = countOre;
    }

    public int getCountMoney() {
        return countMoney;
    }

    public void setCountMoney(int countMoney) {
        this.countMoney = countMoney;
    }

    public int getWORLD_WIDTH() {
        return WORLD_WIDTH;
    }
}
