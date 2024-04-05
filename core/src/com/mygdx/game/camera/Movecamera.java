package com.mygdx.game.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Logger;
public class Movecamera extends OrthographicCamera {
    Logger logger;
    public void moveCamera(float speed){
        position.x+=speed;
        System.out.println(position.x);
    }
}
