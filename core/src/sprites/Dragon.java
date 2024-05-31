package sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.CosmosGame;
import com.mygdx.game.camera.Movecamera;

import screen.GameScreen;

public class Dragon {
    private final Stage stage;
    private ImageButton t1, t2,fire;
    public boolean wasDamaged = false;
    private float attackTime = -1f;

    public Dragon(Stage stage) {
        this.stage = stage;
        Drawable DD = new TextureRegionDrawable(new Texture("dragon.png"));
        Drawable DgD = new TextureRegionDrawable(new Texture("dragonDG.png"));
        Drawable fD = new TextureRegionDrawable(new Texture("fire.png"));
        t1 = new ImageButton(DD);
        t2 = new ImageButton(DgD);
        fire = new ImageButton(fD);
        t1.setTransform(true);
        t1.setScale(0.8f);
        t1.setPosition(14000, 90);
        //
        t2.setTransform(true);
        t2.setScale(0.8f);
        t2.setPosition(14000, 90);
        t2.setVisible(false);
        fire.setTransform(true);
        fire.setScale(2.7f);
        fire.setPosition(14000-250,90);
        fire.setVisible(false);

        stage.addActor(t1);

    }
    public void DrawDragon(float time, CosmosGame cosmosGame, Movecamera movecamera, GameScreen gm,Player player){
        if(wasDamaged){
            t1.setVisible(false);
            t2.setVisible(false);
            fire.setVisible(false);
        }
        else {
            if ((int) time % 8 == 0) {
                attackTime = time;
            }
            if (attackTime != -1) {
                t1.setVisible(false);
                t2.setVisible(true);

                if (time - attackTime >= 2f && time - attackTime <= 3f) {
                    fire.setVisible(true);
                    if (14000 - movecamera.position.x <= 250 && 14000 - movecamera.position.x >= -250) {
                        cosmosGame.changeScreen("Menu");
                    }
                }
                if (time - attackTime >= 3f) {
                    fire.setVisible(false);
                    t2.setVisible(false);
                    t1.setVisible(true);
                    attackTime = -1;
                }
            }
            t1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (player.hasM && movecamera.position.x - 14000 <= 40 && movecamera.position.x - 14000 >= -100) {
                        gm.battle();
                    }
                }
            });
        }
        stage.addActor(t1);
        stage.addActor(t2);
        stage.addActor(fire);
    }
}
