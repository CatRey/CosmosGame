package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.CosmosGame;

public class Diamond {
    public Diamond(Stage stage, CosmosGame cosmosGame){
        Drawable DB = new TextureRegionDrawable(new Texture("mine.png"));
        ImageButton t = new ImageButton(DB);
        t.setTransform(true);
        t.setScale(0.5f);
        t.setPosition(11600,90);
        t.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cosmosGame.setCountDiamond(1);
                t.setVisible(false);
            }
        });
        stage.addActor(t);
    }
}
