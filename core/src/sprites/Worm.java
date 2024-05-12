package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.CosmosGame;
import com.mygdx.game.camera.Movecamera;

public class Worm {
    private final Stage stage;
    public float time=0;
    public boolean jumping =false;
    private ImageButton t ;
    public Worm(int x,Stage stage){
        this.stage=stage;
        Drawable WD =  new TextureRegionDrawable(new Texture("worm.png"));
        t = new ImageButton(WD);
        t.setTransform(true);
        t.setScale(0.5f);
        t.setPosition(x,90);


    }
    public void drawW(int x,Movecamera movecamera, CosmosGame cs){
        if(((int)time)%2==0){
            t.setVisible(true);
            if(x-movecamera.position.x<20 && x-movecamera.position.x>0 && !jumping){
                cs.changeScreen("Menu");
            }

        }
        else{
            t.setVisible(false);
        }
        stage.addActor(t);
    }

}
