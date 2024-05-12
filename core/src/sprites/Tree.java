package sprites;

import com.badlogic.gdx.Gdx;
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

public class Tree {
    private Stage stage;
    private CosmosGame cosmosGame;
    private Movecamera movecamera;
    public float nowtime =0;
    private int position;
    private int id;
    public ImageButton t;
    public Tree(int x,int id, Stage stage, CosmosGame cs, GameScreen gm){
        this.stage = stage;
        this.cosmosGame =cs;
        this.position = x;
        this.id = id;
        Drawable treeD = new TextureRegionDrawable(new Texture("tree.png"));
        t = new ImageButton(treeD);
        t.setTransform(true);
        t.setPosition(x,90);
        t.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                gm.feel(id);

            }
        });
        stage.addActor(t);

    }

}
