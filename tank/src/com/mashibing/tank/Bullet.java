package com.mashibing.tank;

import java.awt.*;

public class Bullet {

    private static final int SPEED = PropertyMgr.getInt("bulletSpeed");;

    private int x,y;

    public static int WIDTH=ResourceMgr.bulletU.getWidth();

    public static int HEIGH=ResourceMgr.bulletU.getHeight();

    private Dir dir;

    private boolean living = true;

    TankFrame tf=null;

    private Group group = Group.BAD;

    Rectangle rect = new Rectangle();

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
        rect.x=this.x;
        rect.y=this.y;
        rect.width = WIDTH;
        rect.height = HEIGH;

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
        //update rect
        rect.x=this.x;
        rect.y=this.y;

        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT ){
            living =false;
        }
    }

    public void collideWith(Tank tank) {
        if(this.group==tank.getGroup()){
            return;
        }
        if(rect.intersects(tank.rect)){
            int eX = tank.getX() +Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() +Tank.HEIGH/2 - Explode.HEIGH/2;
            tf.explodes.add(new Explode(eX,eY,tf));
            tank.die();
            this.die();


        }

    }

    private void die() {
        this.living=false;
    }
}
