package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CosmosGame;
import com.mygdx.game.camera.Movecamera;

import sprites.Ore;
import sprites.Player;
import ui.GameInterface;

import java.util.Random;

public class GameScreen implements Screen {
    private final CosmosGame cosmosGame;
    private Movecamera movecamera;
    private GameInterface gameInterface;
    private SpriteBatch batch;
    private Texture background, ground;
    private Ore[] ores = new Ore[6];
    private Player player;
    Random random;

    public GameScreen(CosmosGame cosmosGame) {
        this.cosmosGame = cosmosGame;
        random = new Random();

    }

    @Override
    public void show() {
        movecamera = new Movecamera();
        player = new Player();
        background = new Texture("background.png");
        ground = new Texture("ground.png");
        movecamera.setToOrtho(false, CosmosGame.SCREEN_WIDTH, CosmosGame.SCREEN_HEIGHT);
        for (int i = 0; i < 6; i++) {
            ores[i] = new Ore(random.nextInt((4500 - 100) + 1) + 100, 90, movecamera, player);
        }
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
        batch.draw(ground, 0, 0);
        player.rendering(batch, movecamera);
        batch.end();
        for (int i = 0; i < 6; i++) {
            if (!ores[i].mined) {
                ores[i].drawSprite();
            }
        }
        gameInterface.drawUI();


    }

    @Override
    public void dispose() {
        gameInterface.dispose();
        player.disposing();
        background.dispose();
        ground.dispose();
        for (int i = 0; i < 6; i++) ores[i].dispose();

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
