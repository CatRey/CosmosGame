package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.CosmosGame;
import com.mygdx.game.camera.Movecamera;

public class Ore {
    private final Stage stage;
    public boolean mined = false;

    public Ore(float x, float y, Movecamera camera,Stage stage,CosmosGame cs,int i){
        this.stage = stage;
        Drawable OD = new TextureRegionDrawable(new Texture("mine.png"));
        ImageButton t = new ImageButton(OD);
        t.setTransform(true);
        t.setScale(0.1f);
        t.setPosition(x,y);
        t.setVisible(!cs.mined[i]);
        t.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                    mined = true;
                    t.setVisible(false);
                    cs.setCountOre(cs.getCountOre() + 1);
                    cs.mined[i] = true;

            }
        });
        stage.addActor(t);
    }
    public boolean getMined(){
        return mined;
    }
}
