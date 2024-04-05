package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
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

import javax.swing.DropMode;

public class GameInterface {
    private final ImageButton left;
    private final ImageButton right;
    private final ImageButton exit;
    private final Movecamera camera;
    private final Stage stage;
    private CosmosGame cosmosGame;

    public GameInterface(Movecamera camera, CosmosGame cosmosGame) {
        this.camera = camera;
        Drawable leftD = new TextureRegionDrawable(new Texture("btnLeft.png"));
        Drawable rightD = new TextureRegionDrawable(new Texture("btnRight.png"));
        Drawable exitD = new TextureRegionDrawable(new Texture("btnExit.png"));
        left = new ImageButton(leftD);
        right = new ImageButton(rightD);
        exit = new ImageButton(exitD);
       float x = camera.position.x;
        left.setPosition(0+x-400, 10);
        left.setTransform(true);
        left.setScale(0.3f);
        right.setPosition(100+x-400, 10);
        right.setTransform(true);
        right.setScale(0.3f);
        exit.setPosition(CosmosGame.SCREEN_WIDTH - 50+x-400, CosmosGame.SCREEN_HEIGHT - 50);
        exit.setTransform(true);
        exit.setScale(0.1f);
        left.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (camera.position.x > 0+CosmosGame.SCREEN_WIDTH/2f) {
                    camera.moveCamera(-10F);

                }
            }
        });
        right.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                    if (camera.position.x < CosmosGame.WORLD_WIDTH-CosmosGame.SCREEN_WIDTH/2f) {
                camera.moveCamera(10F);

            }
            }
        });
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cosmosGame.changeScreen("Menu");
            }
        });
        Viewport fitViewport = new StretchViewport(CosmosGame.SCREEN_WIDTH, CosmosGame.SCREEN_HEIGHT, camera);
        stage = new Stage(fitViewport);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(left);
        stage.addActor(right);
        stage.addActor(exit);
    }

    public void drawUI() {
        float x = camera.position.x;
        left.setPosition(0+x-400, 10);
        right.setPosition(100+x-400, 10);
        exit.setPosition(CosmosGame.SCREEN_WIDTH - 50+x-400, CosmosGame.SCREEN_HEIGHT - 50);
        stage.addActor(left);
        stage.addActor(right);
        stage.addActor(exit);
        stage.act();
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }

}