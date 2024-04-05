package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import screen.GameScreen;
import screen.MenuScreen;

public class CosmosGame extends Game {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 480;
    public static final int WORLD_WIDTH = 5200;
    public static final int WORLD_HEIGHT = 480;
    SpriteBatch batch;
    private MenuScreen menuScreen;
    private GameScreen gameScreen;
    @Override
    public void create() {
        menuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this);
        batch = new SpriteBatch();


    this.setScreen(menuScreen);
    }
    public SpriteBatch getSpriteBatch() {
        return batch;
    }
    public void changeScreen(String screenName) {
         if (screenName.equals("Menu")) {
            setScreen(menuScreen);
        }
         else if(screenName.equals("Game")){
             setScreen(gameScreen);
         }
    }


    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        menuScreen.dispose();
    }
}
