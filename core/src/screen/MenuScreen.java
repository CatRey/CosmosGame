package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CosmosGame;

import ui.MenuInterface;

public class MenuScreen implements Screen {
    private final CosmosGame cosmosGame;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture background;
    private MenuInterface menuInterface;
    private float nowTime;

    public MenuScreen(CosmosGame cosmosGame) {
        this.cosmosGame = cosmosGame;
    }

    @Override
    public void show() {
        batch = cosmosGame.getSpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, CosmosGame.SCREEN_WIDTH, CosmosGame.SCREEN_HEIGHT);

        background = new Texture(Gdx.files.internal("menuBack.png"));

        menuInterface = new MenuInterface(camera,
                () -> cosmosGame.changeScreen("Game")
            );


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.CLEAR);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        float deltaTime = Gdx.graphics.getDeltaTime();
        batch.begin();
        nowTime += deltaTime;
        batch.draw(background, 0, 0, CosmosGame.SCREEN_WIDTH, CosmosGame.SCREEN_HEIGHT);
        batch.end();
        menuInterface.drawUI();

    }
    @Override
    public void dispose(){
        background.dispose();
        menuInterface.dispose();
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
