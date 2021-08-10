package com.mashibing.tank.strategy;

import com.mashibing.tank.Audio;
import com.mashibing.tank.Bullet;
import com.mashibing.tank.Group;
import com.mashibing.tank.Tank;

public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        int bx = t.x +Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = t.y +Tank.HEIGH/2 - Bullet.HEIGH/2;
        new Bullet(bx, by, t.dir,t.group,t.gm);
        if(t.group== Group.GOOD){
            new Thread(()-> new Audio("audio/tank_fire.wav").play()).start();
        }

    }
}
