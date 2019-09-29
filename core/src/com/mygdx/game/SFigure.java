package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class SFigure extends Figure {

    SFigure(int color, int SpawnX, int SpawnY, int[][] mas) {
        super(color, SpawnX, SpawnY, mas);
        deltaCoords = new int[] {-1, 1, 0, 1, 1, 0};
        checkBottom = new int[] {-1, 2, 0, 2, 1, 1};
        checkLeft   = new int[] {-2, 1,-1, 0};
        checkRight  = new int[] { 2, 0, 1, 1};
        maxstate = 1;
        draw();
    }
    // изменить состояние
    void setState(int state){
        switch (state){
            case 0:{
                if(checkCollisions(-1, 1, 0, 1, 1, 0)){
                    deltaCoords = new int[] {-1, 1, 0, 1, 1, 0};
                    checkBottom = new int[] {-1, 2, 0, 2, 1, 1};
                    checkLeft   = new int[] {-2, 1,-1, 0};
                    checkRight  = new int[] { 2, 0, 1, 1};
                    this.state = state;
                }
                return;
            }
            case 1:{
                if(checkCollisions(0, -1, 1, 0, 1, 1)){
                    deltaCoords = new int[] { 0,-1, 1, 0, 1, 1};
                    checkBottom = new int[] { 0, 1, 1, 2};
                    checkLeft   = new int[] {-1,-1,-1, 0, 0, 1};
                    checkRight  = new int[] { 1,-1, 2, 0, 2, 1};
                    this.state = state;
                }
                return;
            }
        }

    }

}
