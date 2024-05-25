package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CosmosGame;
import com.mygdx.game.camera.Movecamera;

import sprites.Artifact;
import sprites.Bat;
import sprites.Diamond;
import sprites.Dragon;
import sprites.Enemy;
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
    private Texture background, ground, bridge;
    private Ore[] ores = new Ore[6];
    private Worm[] worms = new Worm[5];
    private Tree[] trees = new Tree[4];
    private Enemy[] enemy = new Enemy[2];
    private Bat[] bats = new Bat[2];
    private Diamond diamond;
    private Player player;
    private Dragon dragon;
    boolean isChecked = false;
    boolean isRespawned = false;
    private Artifact artifact;
    public Music music;
    //private Random random;
    private float time = 0f;


    public GameScreen(CosmosGame cosmosGame) {
        music = Gdx.audio.newMusic(Gdx.files.internal("game_back.mp3"));
        music.setVolume(0.55f);
        this.cosmosGame = cosmosGame;
        //random = new Random();

    }

    @Override
    public void show() {
        isChecked = false;
        isRespawned = false;
        movecamera = new Movecamera();
        if (cosmosGame.respawnX != 400) movecamera.respawn(cosmosGame.respawnX);
        player = new Player();

        background = new Texture("background.png");
        ground = new Texture("ground.png");
        bridge = new Texture("bridge.png");
        movecamera.setToOrtho(false, CosmosGame.SCREEN_WIDTH, CosmosGame.SCREEN_HEIGHT);
        gameInterface = new GameInterface(movecamera, cosmosGame);
        artifact = new Artifact(gameInterface.stage);
        for (int i = 0; i < 6; i++) {
            ores[i] = new Ore(cosmosGame.X[i], 90, movecamera, gameInterface.stage, cosmosGame, i);


        }
        for (int i = 0; i < 4; i++) {
            trees[i] = new Tree(cosmosGame.trX[i], i, gameInterface.stage, cosmosGame, this);
        }
        for (int i = 0; i < worms.length; i++) {
            worms[i] = new Worm(cosmosGame.X[i] - 30, gameInterface.stage);
        }
        for (int i = 0; i < 1; i++) {
            enemy[0] = new Enemy(2000, gameInterface.stage);
            enemy[1] = new Enemy(4000, gameInterface.stage);
        }
        bats[0] = new Bat(10500, gameInterface.stage);
        bats[1] = new Bat(11200, gameInterface.stage);
        dragon = new Dragon(gameInterface.stage);
        diamond = new Diamond(gameInterface.stage, cosmosGame);
        batch = cosmosGame.getSpriteBatch();
        cosmosGame.k = "Ship";
    }

    float nowTime = -1f;
    float y = 220f;
    int i = 1;
    float playTime = 30f;//random.nextFloat((180f-40f))+40f;

    @Override
    public void render(float speed) {
        batch.setProjectionMatrix(movecamera.combined);
        ScreenUtils.clear(Color.CLEAR);
        movecamera.update();
        if (cosmosGame.respawnX != 400 && !isRespawned && !isChecked) {
            movecamera.moveCamera(5800f);
            isRespawned = true;
        }
        time += Gdx.graphics.getDeltaTime();
        if(time>=playTime && !music.isPlaying()){
            playTime =  time + 120f;
            music.play();
        }
        else if(music.isPlaying()){
            playTime =  time + 120f;
        }
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(ground, 0, 0);
        for (int i = 0; i < ores.length; i++) {
            ores[i].drawO(movecamera, cosmosGame, i,player);
        }
        for (int i = 0; i < worms.length; i++) {
            worms[i].time = time;
            worms[i].jumping = player.jumping;
            worms[i].drawW(cosmosGame.X[i] - 30, movecamera, cosmosGame);
        }
        for (int i = 0; i < enemy.length; i++) {
            enemy[i].drawE(time, movecamera, cosmosGame, player);
        }
        for (int i = 0; i < bats.length; i++) {
            bats[i].drawB(time, movecamera, cosmosGame);
        }
        artifact.Draw(cosmosGame, dragon);
        if (cosmosGame.build) {
            batch.draw(bridge, 5200, 0);
        }


        if (gameInterface.needJump) {
            gameInterface.needJump = false;
            player.jumping = true;
        }
        if (player.jumping) {
            if (nowTime == -1f) nowTime = time;
            if (time >= nowTime + 0.1f) {
                y -= 3.2f;
                player.rendering(gameInterface.stage, movecamera, y, cosmosGame);
                nowTime = time;
            }
            if (y <= 90f) {
                nowTime = -1f;
                player.jumping = false;
                y = 220f;
                i = 1;
                player.rendering(gameInterface.stage, movecamera, 90f, cosmosGame);
            }
        } else {
            player.rendering(gameInterface.stage, movecamera, 90, cosmosGame);
        }
        dragon.DrawDragon(time, cosmosGame, movecamera, this, player);
        batch.end();
        gameInterface.drawUI(cosmosGame, movecamera);


        if (movecamera.position.x >= 6200 && !isChecked) {
            cosmosGame.respawnX = 5200;
            isChecked = true;
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

    private int count = 0;
    private float past = -1;

    public void feel(int id) {
        if (time - past >= 1f) {
            past = time;
            count++;
        }
        if (count == 3) {
            past = -1;
            count = 0;
            trees[id].t.setVisible(false);
            cosmosGame.treed[id] = true;
            cosmosGame.setCountTree(cosmosGame.getCountTree() + 1);
        }
    }

    public void battle() {
        if (past == -1) {
            past = time;
            return;
        }
        if (time - past <= 0.3f) {
            movecamera.moveCamera(-150f);
        } else {
            count++;
            if (count == 20) {
                past = -1;
                count = 0;
                dragon.wasDamaged = true;
            }
        }
        past = time;
    }

}
