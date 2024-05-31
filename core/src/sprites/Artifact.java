package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.CosmosGame;

public class Artifact {
    private ImageButton t;
    private Stage stage;
    public Artifact(Stage stage){
        this.stage = stage;
        t = new ImageButton(new TextureRegionDrawable(new Texture("artefact.png")));
        t.setPosition(15000,90);
        t.setTransform(true);
        t.setScale(0.6f);

    }
    public void Draw(CosmosGame cosmosGame,Dragon dragon){
        t.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(dragon.wasDamaged){
                    t.setVisible(false);
                    cosmosGame.hasAr = true;
                }
            }
        });
        stage.addActor(t);
    }

}
