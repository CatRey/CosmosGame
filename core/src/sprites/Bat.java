package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.CosmosGame;
import com.mygdx.game.camera.Movecamera;


public class Bat {
    private final Stage stage;
    private ImageButton imageButton;
    private final int x;
    public Bat(int x,Stage stage){
        this.stage = stage;
        this.x = x;
        Drawable BD =  new TextureRegionDrawable(new Texture("bat.png"));
        imageButton = new ImageButton(BD);
        imageButton.setTransform(true);
        imageButton.setScale(0.2f);
        imageButton.setPosition(x,400f-166.5f);

    }
    public void drawB(float time, Movecamera movecamera, CosmosGame cs){
        if(((int)time)%3==0){
            imageButton.setVisible(true);
            imageButton.setPosition(x,300f-166.5f);
            if(x-movecamera.position.x<=20 && x-movecamera.position.x>=-20){
                cs.changeScreen("Menu");
            }
        }
        else{
            imageButton.setPosition(x,400f-166.5f);
        }
        stage.addActor(imageButton);
    }

}
