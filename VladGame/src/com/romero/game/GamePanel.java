package com.romero.game;

import com.romero.game.states.GameStateManager;
import com.romero.game.util.KeyHandler;
import com.romero.game.util.MouseHandler;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/*
 * @author Lango
 */

public class GamePanel extends JPanel implements Runnable{

    public static int width;
    public static int height;

    private Thread thread;
    private boolean running = false;

    private BufferedImage img;
    private Graphics2D g;

    private MouseHandler mouse;
    private KeyHandler key;

    private GameStateManager gsm;

    public GamePanel(int width, int height){
        this.width = width;
        this.height = height;

        setPreferredSize(new Dimension());
        // Allows input for panel
        setFocusable(true);
        requestFocus();
    }

    public void addNotify(){
        super.addNotify();

        if(thread == null){
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    public void init(){
        running = true;

        img= new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();

        mouse = new MouseHandler(this);
        key = new KeyHandler(this);

        gsm = new GameStateManager();
    }

    public void run(){
        init();

        final double GAME_HERTZ = 60.0;
        final double TBU = 1000000000 / GAME_HERTZ; // Time Before Update

        final int MUBR = 5; // Must Update Before Render

        double lastUpdateTime = System.nanoTime();  // 1*10^-9=0.000000001 Nano Second
        double lastRenderTime;

        final double TARGET_FPS = 60;
        final double TTBR = 1000000000 / TARGET_FPS; // Total Time Before Render

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime/ 1000000000);
        int oldFrameCount = 0;

        while(running){
            double now = System.nanoTime();
            int updateCount = 0;

            // Game Loop: Tick/Update Count
            while(((now - lastUpdateTime) > TBU) && (updateCount < MUBR)) {
                update(); // can fix lag on other computers by giving it an argument speed/delta
                input(mouse, key);
                lastUpdateTime += TBU;
                updateCount++;
            }

            if(now- lastUpdateTime > TBU) {
                lastUpdateTime = now - TBU;
            }
            input(mouse, key);
            render(); // can fix lag on other computers by giving it an argument speed/delta
            draw();

            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime/ 1000000000);
            if(thisSecond > lastSecondTime){
                if(frameCount != oldFrameCount){
                    System.out.println("NEW SECOND: " + thisSecond + "\n" + " FPS: " + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while(now - lastRenderTime < TTBR && now - lastUpdateTime < TBU){
                Thread.yield();

                try{
                    Thread.sleep(1);
                }catch (Exception e){
                    System.out.println("ERROR: yielding thread");
                }
                now = System.nanoTime();
            }

        }
    }



    public void update(){
        gsm.update();

    }

    public void input(MouseHandler mouse, KeyHandler key){
        gsm.input(mouse, key);

    }

    public void render(){
        if(g != null){
            g.setColor(new Color(66, 134, 244)); // Color for Background
            g.fillRect(0,0, width, height); // Rectangle Background
            gsm.render(g);
        }
    }

    public void draw(){
        Graphics g2 = (Graphics) this.getGraphics();
        g2.drawImage(img, 0,0, width,  height, null);
        g2.dispose();
    }



}
