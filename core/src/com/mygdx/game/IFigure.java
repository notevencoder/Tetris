package com.mygdx.game;

public class IFigure extends Figure {

    IFigure(int color, int SpawnX, int SpawnY, int[][] mas) {
        super(color, SpawnX, SpawnY+2, mas);
        deltaCoords = new int[] { 0,-1, 0, 1, 0, 2};
        checkBottom = new int[] {0, 3};
        checkLeft   = new int[] {-1,-1,-1, 0,-1, 1,-1, 2};
        checkRight  = new int[] { 1,-1, 1, 0, 1, 1, 1, 2};
        maxstate = 1;
        draw();
    }
    // изменить состояние
    void setState(int state){
        switch (state){
            case 0:{
                if(checkCollisions(0,-1, 0, 1, 0, 2)){
                    deltaCoords = new int[] { 0,-1, 0, 1, 0, 2};
                    checkBottom = new int[] { 0, 3};
                    checkLeft   = new int[] {-1,-1,-1, 0,-1, 1,-1, 2};
                    checkRight  = new int[] { 1,-1, 1, 0, 1, 1, 1, 2};
                    this.state = state;
                }
                return;
            }
            case 1:{
                if(checkCollisions(-2, 0,-1, 0, 1, 0)){
                    deltaCoords = new int[] {-2, 0,-1, 0, 1, 0};
                    checkBottom = new int[] {-2, 1,-1, 1, 0, 1, 1, 1};
                    checkLeft   = new int[] {-3, 0};
                    checkRight  = new int[] { 2, 0};
                    this.state = state;
                }
                return;
            }
        }

    }

}
