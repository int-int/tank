package com.mashibing;

import java.awt.*;

public class Bullet {

    private static final int SPEED = 10;

    private int x,y;

    private int WIDTH=30, HEIGH=30;

    private Dir dir;

    private boolean live = true;

    TankFrame tf=null;

    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf=tf;
    }

    public void paint(Graphics g){
        if(!live){
            tf.bullets.remove(this);
        }
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH,HEIGH);
        g.setColor(c);
        move();
    }
    private void move() {
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
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT ){
            live=false;
        }
    }
}
