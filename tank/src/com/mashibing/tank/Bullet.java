package com.mashibing.tank;

import java.awt.*;

public class Bullet extends GameObject{

    private static final int SPEED = PropertyMgr.getInt("bulletSpeed");;

    private int x,y;

    public static int WIDTH=ResourceMgr.bulletU.getWidth();

    public static int HEIGH=ResourceMgr.bulletU.getHeight();

    private Dir dir;

    private boolean living = true;

    GameModel gm=null;

    public Group group = Group.BAD;

    Rectangle rect = new Rectangle();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public Bullet(int x, int y, Dir dir, Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.gm=gm;
        rect.x=this.x;
        rect.y=this.y;
        rect.width = WIDTH;
        rect.height = HEIGH;
        gm.add(this);

    }

    @Override
    public void paint(Graphics g){
        if(!living){
            gm.remove(this);
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
        //update rect
        rect.x=this.x;
        rect.y=this.y;

        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT ){
            living =false;
        }
    }

    public void die() {
        this.living=false;
    }
}
