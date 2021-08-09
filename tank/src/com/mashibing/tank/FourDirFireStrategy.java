package com.mashibing.tank;

public class FourDirFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank t) {
        int bx = t.x +Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = t.y +Tank.HEIGH/2 - Bullet.HEIGH/2;

        Dir[] dirs=Dir.values();
        for (Dir dir : dirs) {
            new Bullet(bx, by, dir,t.group,t.tf);
        }


        if(t.group==Group.GOOD){
            new Thread(()-> new Audio("audio/tank_fire.wav").play()).start();
        }

    }
}
