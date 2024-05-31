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

public class Enemy {
    private Stage stage;
    public ImageButton t;
    private  int x;
    public Enemy(int x,Stage stage){
        this.x = x;
        this.stage = stage;
        Drawable ED = new TextureRegionDrawable(new Texture("enemy.png"));
        t = new ImageButton(ED);
        t.setTransform(true);
        t.setPosition(x,90);
        stage.addActor(t);

    }
    private float pastTime = 0;
    public void drawE(float time, Movecamera movecamera, CosmosGame cosmosGame,Player player){
        if(time-pastTime>=0.07f){
            x-=2;
            t.setPosition(x,90);
            pastTime = time;
            stage.addActor(t);
        }
        if(movecamera.position.x-x<=30 && movecamera.position.x-x>=-30 && t.isVisible() ){
            if(player.y<=80+90) {
                cosmosGame.changeScreen("Menu");
            }
        }

    }
}
