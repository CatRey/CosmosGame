package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CosmosGame;
import com.mygdx.game.camera.Movecamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ui.MapInterface;

public class MapScreen implements Screen {
    private final CosmosGame cosmosGame;
    private Movecamera movecamera;
    private SpriteBatch batch;
    private Texture map;
    private MapInterface mapInterface;
    public MapScreen(CosmosGame cosmosGame){
        this.cosmosGame = cosmosGame;
    }
    @Override
    public void show(){
        movecamera = new Movecamera();
        movecamera.setToOrtho(false, CosmosGame.SCREEN_WIDTH, CosmosGame.SCREEN_HEIGHT);
        map = new Texture("MapBacki.png");
        mapInterface = new MapInterface(movecamera,cosmosGame);
        batch = cosmosGame.getSpriteBatch();
        cosmosGame.k = "Map";

    }
    @Override
    public void render(float speed){
        batch.setProjectionMatrix(movecamera.combined);
        ScreenUtils.clear(Color.CLEAR);
        movecamera.update();
        float speedTime = Gdx.graphics.getDeltaTime();
        batch.begin();
        batch.draw(map,0,0);
        batch.end();
        mapInterface.drawUI();
    }
    @Override
    public void dispose(){
        map.dispose();
        mapInterface.dispose();
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
