package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.CosmosGame;
import com.mygdx.game.camera.Movecamera;

public class Player{
    private Texture playerTexture;
    public boolean mining = false;
    public boolean jumping = false;
    public boolean hasM = false;
    public float y;
    private ImageButton t,t1,t2;

    public Player(){
       t = new ImageButton(new TextureRegionDrawable(new Texture("player.png")));
       t1 = new ImageButton(new TextureRegionDrawable(new Texture("playerwith.png")));
       t2 = new ImageButton(new TextureRegionDrawable(new Texture("playerM.png")));
    }
    public void rendering(Stage stage, Movecamera movecamera, float y, CosmosGame cosmosGame){
        this.y = y;
        int u = 0;
        if(movecamera.position.x>12000 && cosmosGame.getCountTree()>=1 && cosmosGame.getCountDiamond()>=1){
            t1.setVisible(false);
            t.setVisible(false);
            t2.setVisible(true);
            r(t2,movecamera,y);
            u =2;
            hasM = true;
            stage.addActor(t2);
        }
        else if(movecamera.position.x>5200){
            t2.setVisible(false);
            t.setVisible(false);
            t1.setVisible(true);
            r(t1,movecamera,y);
            u =1;
            stage.addActor(t1);
        }
        else{
            t2.setVisible(false);
            t1.setVisible(false);
            t.setVisible(true);
            r(t,movecamera,y);
            stage.addActor(t);
        }


    }
    private void r(ImageButton t,Movecamera movecamera,float y){
        if(jumping)t.setPosition(movecamera.position.x,y);
        else t.setPosition(movecamera.position.x,90);
    }
    public void disposing(){
        playerTexture.dispose();
    }
}
