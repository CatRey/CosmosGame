package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.CosmosGame;
import com.mygdx.game.camera.Movecamera;

public class MapInterface {
    private final ImageButton jahve;
    private final ImageButton germes;
    public Stage stage;
    private CosmosGame cosmosGame;
    public MapInterface(Movecamera camera,CosmosGame cosmosGame){
        Drawable jahD = new TextureRegionDrawable(new Texture("Jahve.png"));
        Drawable gerD = new TextureRegionDrawable(new Texture("Germes.png"));
        jahve = new ImageButton(jahD);
        jahve.setTransform(true);
        jahve.setPosition(150,200);
        jahve.setScale(0.4f);
        germes = new ImageButton(gerD);
        germes.setTransform(true);
        germes.setScale(0.4f);
        germes.setPosition(500,80);
        jahve.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cosmosGame.to = "Game";
                cosmosGame.changeScreen("Ship");
            }
        });
        germes.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cosmosGame.changeScreen("Ship");
            }
        });
        Viewport fitViewport = new StretchViewport(CosmosGame.SCREEN_WIDTH, CosmosGame.SCREEN_HEIGHT, camera);
        stage = new Stage(fitViewport);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(jahve);
        stage.addActor(germes);
    }
    public void drawUI(){
        stage.act();
        stage.draw();
    }
    public void dispose() {
        stage.dispose();
    }
}
