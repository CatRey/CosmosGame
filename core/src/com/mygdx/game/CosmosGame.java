package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import screen.GameScreen;
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
    private int countOre = 0;
    private int IdScene;
    @Override
    public void create() {
        menuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this);
        shipScreen = new ShipScreen(this);
        batch = new SpriteBatch();


    this.setScreen(menuScreen);
    IdScene = 0;
    }
    public SpriteBatch getSpriteBatch() {
        return batch;
    }
    public void changeScreen(String screenName) {
         if (screenName.equals("Menu")) {
             IdScene = 0;
            setScreen(menuScreen);

        }
         else if(screenName.equals("Game")){
             IdScene = 1;
             setScreen(gameScreen);

         }
         else if(screenName.equals("Ship")){
             IdScene = 2;
             setScreen(shipScreen);

         }
    }

    public int getIdScene() {
        return IdScene;
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        menuScreen.dispose();
        gameScreen.dispose();
        shipScreen.dispose();
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

    public int getWORLD_WIDTH() {
        return WORLD_WIDTH;
    }
}
