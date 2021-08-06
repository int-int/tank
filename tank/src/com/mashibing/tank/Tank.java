package com.mashibing.tank;

import java.awt.*;
import java.util.Random;

public class Tank {

    private int x,y;

    private Dir dir=Dir.DOWN;

    private static final int SPEED = 5;

    public static int WIDTH=ResourceMgr.goodTankU.getWidth();

    public static int HEIGH=ResourceMgr.goodTankU.getHeight();

    private Random random = new Random();

    private boolean moving=true;

    private TankFrame tf = null;

    private boolean living = true;

    private Group group = Group.BAD;

    public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if(!living){
            tf.tanks.remove(this);
        }
        switch (dir) {
            case LIFT:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankL:ResourceMgr.badTankL,x,y,null);
                break;
            case UP:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankU:ResourceMgr.badTankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankR:ResourceMgr.badTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankD:ResourceMgr.badTankD,x,y,null);
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

        if(this.group==Group.BAD && random.nextInt(100)>95){
            this.fire();
        }
        if(this.group==Group.BAD&& random.nextInt(100)> 95){
            randomDir();
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bx = this.x +Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = this.y +Tank.HEIGH/2 - Bullet.HEIGH/2;
        tf.bullets.add(new Bullet(bx, by, this.dir,this.group,this.tf));
        //new Thread(()-> new Audio("audio/tank_fire.wav").play()).start();
    }

    public void die() {
        this.living=false;
    }
}
