package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
    private Movecamera movecamera;
    private GameInterface gameInterface;
    private SpriteBatch batch;
    private Texture background, ground;
    private Player player;
    public ShipScreen(CosmosGame cosmosGame) {
        this.cosmosGame = cosmosGame;
    }
    @Override
    public void show(){
        movecamera = new Movecamera();
        player = new Player();
        background = new Texture("Spacesipback.png");
        movecamera.setToOrtho(false, CosmosGame.SCREEN_WIDTH, CosmosGame.SCREEN_HEIGHT);
        gameInterface = new GameInterface(movecamera, cosmosGame);
        batch = cosmosGame.getSpriteBatch();
    }
    @Override
    public void render(float speed) {
        batch.setProjectionMatrix(movecamera.combined);
        ScreenUtils.clear(Color.CLEAR);
        movecamera.update();

        float speedTime = Gdx.graphics.getDeltaTime();
        batch.begin();
        batch.draw(background, 0, 0);
        player.rendering(batch, movecamera);
        batch.end();
        gameInterface.drawUI(cosmosGame);
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
