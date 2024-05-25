package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CosmosGame;
import com.mygdx.game.camera.Movecamera;

import java.util.Random;

import sprites.Player;
import ui.GameInterface;

public class ShipScreen implements Screen {
    private final CosmosGame cosmosGame;
    private boolean hasP = false;
    private Movecamera movecamera;
    private GameInterface gameInterface;
    private SpriteBatch batch;
    private Texture background, ground,map;
    private Player player;
    public ShipScreen(CosmosGame cosmosGame) {
        this.cosmosGame = cosmosGame;
    }
    @Override
    public void show(){
        movecamera = new Movecamera();
        player = new Player();
        background = new Texture("Spacesipback.png");
        map = new Texture("mapBack.png");
        movecamera.setToOrtho(false, CosmosGame.SCREEN_WIDTH, CosmosGame.SCREEN_HEIGHT);
        gameInterface = new GameInterface(movecamera, cosmosGame);
        batch = cosmosGame.getSpriteBatch();
        cosmosGame.k = cosmosGame.to;
        if(!hasP) {
            Music music = Gdx.audio.newMusic(Gdx.files.internal("dict1.mp3"));
            music.play();
            hasP = true;
        }
    }
    @Override
    public void render(float speed) {
        batch.setProjectionMatrix(movecamera.combined);
        ScreenUtils.clear(Color.CLEAR);
        movecamera.update();

        float speedTime = Gdx.graphics.getDeltaTime();
        batch.begin();
        batch.draw(background, 0, 0);
        player.rendering(gameInterface.stage, movecamera,90,cosmosGame);
        batch.end();
        gameInterface.drawUI(cosmosGame, movecamera);
    }
    @Override
    public void dispose() {
        gameInterface.dispose();
        player.disposing();
        background.dispose();

    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


}
