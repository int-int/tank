package com.mashibing.tank.cor;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.Explode;
import com.mashibing.tank.GameObject;
import com.mashibing.tank.Tank;

public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank){
            Bullet b1=(Bullet)o1;
            Tank t1=(Tank)o2;
            if(b1.group==t1.getGroup()){
                return true;
            }
            if(b1.getRect().intersects(t1.getRect())){
                int eX = t1.getX() +Tank.WIDTH/2 - Explode.WIDTH/2;
                int eY = t1.getY() +Tank.HEIGH/2 - Explode.HEIGH/2;
                t1.gm.add(new Explode(eX,eY,t1.gm));
                t1.die();
                b1.die();
                return false;
            }
        }else if(o1 instanceof Tank && o2 instanceof Bullet){
            return collide(o2,o1);
        }
        return true;
    }


}
