package com.mashibing.tank;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    GameModel gm=new GameModel();
    static final int GAME_WIDTH = PropertyMgr.getInt("gameWidth"),GAME_HEIGHT=PropertyMgr.getInt("gameHeight");




    public TankFrame() throws HeadlessException {

        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("坦克大战");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if(offScreenImage==null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_WIDTH);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        gm.paint(g);
    }


    class MyKeyListener extends KeyAdapter{
        boolean bL=false;
        boolean bU=false;
        boolean bR=false;
        boolean bD=false;

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL=true;
                    break;
                case KeyEvent.VK_UP:
                    bU=true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR=true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD=true;
                    break;
                case KeyEvent.VK_CONTROL:
                    gm.getMainTank().fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL=false;
                    break;
                case KeyEvent.VK_UP:
                    bU=false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR=false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD=false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
        private void setMainTankDir() {

            if(!bL && !bU && !bR && !bD){
                gm.getMainTank().setMoving(false);
            }else{
                gm.getMainTank().setMoving(true);
                if (bL){
                    gm.getMainTank().setDir(Dir.LIFT);
                }
                if (bU){
                    gm.getMainTank().setDir(Dir.UP);
                }
                if (bR){
                    gm.getMainTank().setDir(Dir.RIGHT);
                }
                if (bD){
                    gm.getMainTank().setDir(Dir.DOWN);
                }
            }
        }
    }


}
