package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CosmosGame;
import com.mygdx.game.camera.Movecamera;

import sprites.Ore;
import sprites.Player;
import sprites.Tree;
import sprites.Worm;
import ui.GameInterface;

import java.util.Random;

public class GameScreen implements Screen {
    private final CosmosGame cosmosGame;
    private Movecamera movecamera;
    private GameInterface gameInterface;
    private SpriteBatch batch;
    private Texture background, ground,bridge;
    private Ore[] ores = new Ore[6];
    private Worm[] worms = new Worm[5];
    private Tree[] trees = new Tree[6];
    private Player player;
    Random random;
    private float time=0f;

    public GameScreen(CosmosGame cosmosGame) {
        this.cosmosGame = cosmosGame;
        random = new Random();

    }

    @Override
    public void show() {

        movecamera = new Movecamera();
        player = new Player();
        background = new Texture("background.png");
        ground = new Texture("ground.png");
        bridge = new Texture("bridge.png");
        movecamera.setToOrtho(false, CosmosGame.SCREEN_WIDTH, CosmosGame.SCREEN_HEIGHT);
        gameInterface = new GameInterface(movecamera, cosmosGame);
        for (int i = 0; i < 6; i++) {
            ores[i] = new Ore(cosmosGame.X[i], 90, movecamera, gameInterface.stage, cosmosGame,i);
            trees[i] = new Tree(cosmosGame.X[i]+5500,i,gameInterface.stage,cosmosGame,this);

        }
        for(int i=0;i<worms.length;i++){
            worms[i] = new Worm(cosmosGame.X[i]-30,gameInterface.stage);
        }
        batch = cosmosGame.getSpriteBatch();
        cosmosGame.k = "Ship";
    }
    float nowTime=-1f;
    float y=200f;int i=1;
    @Override
    public void render(float speed) {
        batch.setProjectionMatrix(movecamera.combined);
        ScreenUtils.clear(Color.CLEAR);
        movecamera.update();

        time+=Gdx.graphics.getDeltaTime();
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(ground, 0, 0);
        if(cosmosGame.build){
            batch.draw(bridge,5200,0);
        }



        if(gameInterface.needJump){
            gameInterface.needJump=false;
            player.jumping=true;
        }
        if(player.jumping) {
            if (nowTime == -1f) nowTime = time;
            time += Gdx.graphics.getDeltaTime();
            if (time >= nowTime + 0.1f) {
                y -= 3f;
                player.rendering(batch, movecamera, y);
                nowTime = time;
            }
            if(y<=90f){
                nowTime=-1f;
                player.jumping=false;
                y=200f;
                i=1;
                player.rendering(batch,movecamera,90f);
            }
        }
        else{
            player.rendering(batch, movecamera,90);
        }
        batch.end();
        gameInterface.drawUI(cosmosGame,movecamera);
        for(int i=0;i<worms.length;i++){
            worms[i].time=time;
            worms[i].jumping=player.jumping;
            worms[i].drawW(cosmosGame.X[i]-30,movecamera,cosmosGame);
        }
    }

    @Override
    public void dispose() {
        gameInterface.dispose();
        player.disposing();
        background.dispose();
        ground.dispose();
        bridge.dispose();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
    private int count=0;
    private float past = -1;
    public void feel(int id){
        if(time-past>=1f){
            past = time;
            count++;
        }
        if(past==-1)past = time;
        if(count==3){
            past=-1;
            trees[id].t.setVisible(false);
        }
    }

}
