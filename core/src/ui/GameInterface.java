package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.CosmosGame;
import com.mygdx.game.camera.Movecamera;

import javax.swing.DropMode;

public class GameInterface {
    private final ImageButton left;
    private final ImageButton right;
    private final ImageButton exit;
    private final ImageButton ship;
    private final ImageButton map;
    private final ImageButton vob;
    private final ImageButton table;
    private final Movecamera camera;
    public Stage stage;
    private CosmosGame cosmosGame;
    public boolean needJump = false;
    private boolean trans = false;

    public GameInterface(Movecamera camera, CosmosGame cosmosGame) {
        this.camera = camera;
        Drawable leftD = new TextureRegionDrawable(new Texture("btnLeft.png"));
        Drawable rightD = new TextureRegionDrawable(new Texture("btnRight.png"));
        Drawable exitD = new TextureRegionDrawable(new Texture("btnExit.png"));
        Drawable shipD = new TextureRegionDrawable(new Texture("btnShip.png"));
        Drawable mapD = new TextureRegionDrawable(new Texture("btnS.png"));
        Drawable vobD = new TextureRegionDrawable(new Texture("vob.png"));
        Drawable taD = new TextureRegionDrawable(new Texture("table.png"));

        left = new ImageButton(leftD);
        right = new ImageButton(rightD);
        exit = new ImageButton(exitD);
        ship = new ImageButton(shipD);
        map = new ImageButton(mapD);
        vob = new ImageButton(vobD);
        table = new ImageButton(taD);
        float x = camera.position.x;
        left.setPosition(0 + x - 400, 10);
        left.setTransform(true);
        left.setScale(0.3f);
        right.setPosition(100 + x - 400, 10);
        right.setTransform(true);
        right.setScale(0.3f);
        ship.setPosition(200 + x - 400, 10);
        ship.setTransform(true);
        ship.setScale(0.2f);
        exit.setPosition(CosmosGame.SCREEN_WIDTH - 50 + x - 400, CosmosGame.SCREEN_HEIGHT - 50);
        exit.setTransform(true);
        exit.setScale(0.1f);
        map.setTransform(true);
        map.setPosition(500+x-400, 10);
        map.setScale(0.2f);
        vob.setTransform(true);
        vob.setScale(0.3f);
        vob.setPosition(5000, 90);
        table.setTransform(true);
        table.setScale(0.4f);
        table.setPosition(9050, 90);
        if (cosmosGame.getIdScene() == 1) {
            if (cosmosGame.build) {
                if(trans){
                    cosmosGame.setWORLD_WIDTH(16000);
                }
                else cosmosGame.setWORLD_WIDTH(9300);
            } else cosmosGame.setWORLD_WIDTH(5200);
        }
        if (cosmosGame.getIdScene() == 2) {
            cosmosGame.setWORLD_WIDTH(800);
        }
        left.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (camera.position.x > 0 + CosmosGame.SCREEN_WIDTH / 2f) {
                    camera.moveCamera(-20F);

                }
            }
        });
        right.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y,int pointer,int button) {
                if (camera.position.x < cosmosGame.getWORLD_WIDTH() - CosmosGame.SCREEN_WIDTH / 2f) {
                    camera.moveCamera(20F);

                }
                return true;
            }
        });
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cosmosGame.changeScreen("Menu");
            }
        });
        ship.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                needJump = true;
            }
        });
        map.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cosmosGame.changeScreen(cosmosGame.k);
            }
        });
        vob.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (cosmosGame.getCountOre() >= 3) {
                    cosmosGame.setCountOre(cosmosGame.getCountOre() - 3);
                    cosmosGame.build = true;
                }
            }
        });
        table.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (cosmosGame.getCountTree() >= 2) {
                    cosmosGame.setCountTree(cosmosGame.getCountTree() - 2);
                    trans = true;
                }
            }
        });
        Viewport fitViewport = new StretchViewport(CosmosGame.SCREEN_WIDTH, CosmosGame.SCREEN_HEIGHT, camera);
        stage = new Stage(fitViewport);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(left);
        stage.addActor(right);
        stage.addActor(exit);
        stage.addActor(ship);
        if (cosmosGame.getIdScene() == 1) {
            stage.addActor(vob);
            stage.addActor(table);
        }
    }

    public void drawUI(CosmosGame cosmosGame, Movecamera camera) {
        if(cosmosGame.getIdScene()==1){
            cosmosGame.k = "Ship";
        }
        else{
            cosmosGame.k = "Game";
        }
        float x = camera.position.x;
        if (cosmosGame.getIdScene() == 1) {
            if (cosmosGame.build) {
                if(trans){
                    cosmosGame.setWORLD_WIDTH(16000);
                }
                else cosmosGame.setWORLD_WIDTH(9300);
            } else cosmosGame.setWORLD_WIDTH(5200);
        }
        ship.setPosition(200 + x - 400, 10);
        left.setPosition(0 + x - 400, 10);
        right.setPosition(100 + x - 400, 10);
        map.setPosition(500+x-400,10);
        exit.setPosition(CosmosGame.SCREEN_WIDTH - 50 + x - 400, CosmosGame.SCREEN_HEIGHT - 50);
        stage.addActor(left);
        stage.addActor(right);
        stage.addActor(exit);
        stage.addActor(ship);
        if(camera.position.x>400){
            map.setVisible(false);
        }
        else{
            map.setVisible(true);
        }
        stage.addActor(map);
        stage.act();
        stage.draw();

    }

    public void dispose() {
        stage.dispose();
    }

}
