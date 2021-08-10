package com.mashibing.tank;

import com.mashibing.tank.strategy.FireStrategy;

import java.awt.*;
import java.util.Random;

public class Tank extends GameObject{

    public int x,y;

    public int oldX,oldY;

    public Dir dir=Dir.DOWN;

    private static final int SPEED = PropertyMgr.getInt("tankSpeed");

    public static int WIDTH=ResourceMgr.goodTankU.getWidth();

    public static int HEIGH=ResourceMgr.goodTankU.getHeight();

    private Random random = new Random();

    private boolean moving=true;

    private boolean living = true;

    public Group group = Group.BAD;

    Rectangle rect = new Rectangle();

    FireStrategy fs;

    public GameModel gm;

    public Tank(int x, int y, Dir dir,Group group,GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.gm=gm;
        rect.x=this.x;
        rect.y=this.y;
        rect.width = WIDTH;
        rect.height = HEIGH;
        String fsName;
        if(this.group==Group.GOOD){
            fsName = PropertyMgr.getString("goodFS");
        }else{
            fsName = PropertyMgr.getString("badFS");
        }
        try {
            fs=(FireStrategy)Class.forName(fsName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
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

    @Override
    public void paint(Graphics g) {
        if(!living){
            gm.remove(this);
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
        oldX=x;
        oldY=y;
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
        boundsCheck();
        rect.x=this.x;
        rect.y=this.y;
    }

    private void boundsCheck() {
        if(this.x < 2){
            x = 2;
        }
        if(this.y<28){
            y=28;
        }
        if(this.x > TankFrame.GAME_WIDTH-Tank.WIDTH-2){
            x=TankFrame.GAME_WIDTH-Tank.WIDTH-2;
        }
        if(this.y > TankFrame.GAME_HEIGHT-Tank.HEIGH-2){
            y=TankFrame.GAME_HEIGHT-Tank.HEIGH-2;
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        fs.fire(this);
    }

    public void die() {
        this.living=false;
    }

    public void stop(){
        this.x=oldX;
        this.y=oldY;
    }
}
