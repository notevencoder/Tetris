package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class OFigure extends Figure {

    OFigure(int color, int SpawnX, int SpawnY, int[][] mas) {
        super(color, SpawnX, SpawnY, mas);
        deltaCoords = new int[] { 0, 1, 1, 0, 1, 1};
        checkBottom = new int[] { 0, 2, 1, 2};
        checkLeft   = new int[] {-1, 0,-1, 1};
        checkRight  = new int[] { 2, 0, 2, 1};
        maxstate = 0;
        draw();
    }
    // изменить состояние
    void setState(int state){}

}
