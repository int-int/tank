package com.mashibing;

import java.awt.*;

public class Bullet {

    private static final int SPEED = 10;

    private int x,y;

    public static int WIDTH=ResourceMgr.bulletD.getWidth();

    public static int HEIGH=ResourceMgr.bulletD.getHeight();

    private Dir dir;

    private boolean living = true;

    TankFrame tf=null;

    private Group group = Group.BAD;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.tf=tf;
    }

    public void paint(Graphics g){
        if(!living){
            tf.bullets.remove(this);
        }
        switch (dir) {
            case LIFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
        }

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
            living =false;
        }
    }

    public void collideWith(Tank tank) {
        if(this.group==tank.getGroup()){
            return;
        }
        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGH);
        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), tank.WIDTH, tank.HEIGH);
        if(rect1.intersects(rect2)){
            tank.die();
            this.die();
        }

    }

    private void die() {
        this.living=false;
    }
}
