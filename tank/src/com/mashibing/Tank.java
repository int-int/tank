package com.mashibing;

import java.awt.*;

public class Tank {

    private int x,y;

    private Dir dir=Dir.DOWN;

    private static final int SPEED = 5;

    public static int WIDTH=ResourceMgr.tankD.getWidth();

    public static int HEIGH=ResourceMgr.tankD.getHeight();

    private boolean moving=false;

    private TankFrame tf = null;

    private boolean living = true;

    public Tank(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf=tf;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics g) {
        if(!living){
            tf.tanks.remove(this);
        }
        switch (dir) {
            case LIFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
        }

        move();

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

    public void fire() {
        int bx = this.x +Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = this.y +Tank.HEIGH/2 - Bullet.HEIGH/2;
        tf.bullets.add(new Bullet(bx, by, this.dir,this.tf));    }

    public void die() {
        this.living=false;
    }
}
