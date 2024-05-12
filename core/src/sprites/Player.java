package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.camera.Movecamera;

public class Player{
    private Texture playerTexture;
    public boolean mining = false;
    public boolean jumping = false;

    public Player(){
        playerTexture = new Texture("player.png");
    }
    public void show(){
        playerTexture = new Texture("player.png");
    }
    public void rendering(SpriteBatch batch, Movecamera movecamera,float y){
        if(movecamera.position.x>5200){
            playerTexture = new Texture("playerwith.png");
        }
        if(jumping)batch.draw(playerTexture, movecamera.position.x, y);
        else batch.draw(playerTexture, movecamera.position.x, 90);
    }
    public void disposing(){
        playerTexture.dispose();
    }
}
