package sprites;

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

public class Ore {
    private final Stage stage;
    public boolean mined = false;

    public Ore(float x, float y, Movecamera camera,Player player){
        Drawable OD = new TextureRegionDrawable(new Texture("mine.png"));
        ImageButton t = new ImageButton(OD);
        t.setTransform(true);
        t.setScale(0.1f);
        t.setPosition(x,y);
        t.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                player.mining = true;
                mined = true;
            }
        });
        Viewport fitViewport = new StretchViewport(CosmosGame.SCREEN_WIDTH, CosmosGame.SCREEN_HEIGHT, camera);
        stage = new Stage(fitViewport);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(t);
    }
    public void drawSprite(){
        stage.act();
        stage.draw();
    }
    public void dispose(){
        stage.dispose();
    }
}
