package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

public class MenuInterface {
    private final ImageButton button;
    private Stage stage;
    private final OrthographicCamera camera;
    private final BtnListener btnListener;

    public MenuInterface(OrthographicCamera camera, BtnListener btnListener) {
        this.camera = camera;
        this.btnListener = btnListener;
        Texture BST = new Texture("btnStart.png");
        Drawable BSB = new TextureRegionDrawable(BST);
        button = new ImageButton(BSB);
        button.setPosition(CosmosGame.SCREEN_WIDTH / 2f - button.getWidth() / 2f,
                CosmosGame.SCREEN_HEIGHT / 2f - button.getHeight() / 2f
                );
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnListener.onClick();
            }
        });
        Viewport fit = new StretchViewport(CosmosGame.SCREEN_WIDTH, CosmosGame.SCREEN_HEIGHT, camera);
        stage = new Stage(fit);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(button);

    }

    public void drawUI() {
        stage.act();
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }
}
