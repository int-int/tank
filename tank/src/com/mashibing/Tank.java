package com.mashibing;

import java.awt.*;

public class Tank {

    private int x,y;

    private Dir dir=Dir.DOWN;

    private static final int SPEED=10;

    private boolean moving=false;

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        move();
        g.fillRect(x,y,50,50);

    }

    private void move() {
        if(!moving){
            return;
        }

        switch (dir) {
            case LIFT:
                x-=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
        }
    }
}
