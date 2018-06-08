/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sepgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 *
 * @author lulambert
 */
public class SepGame extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private final double UPDATE_CAP = 1.0 / 60.0;

    private Menu menu;

    public enum STATE {
        Menu,
        Aide,
        Jeu,
        Pause,
        Fin;
    }
    public STATE gameState = STATE.Menu;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new SepGame();
    }

    public SepGame() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput(this, handler));

        new Window(WIDTH + 6, HEIGHT + 28, "SEP Game", this);
        menu = new Menu(this, handler);
        this.addMouseListener(menu);

        System.out.println(gameState);
        if (gameState == STATE.Menu) {
            //Décoration
        }
    }

    public synchronized void start() {
        Thread thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.requestFocus();

        // Majoolwip
        running = true;
        System.out.println("");

        boolean render = false;
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        while (running) {
            render = false;

            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while (unprocessedTime >= UPDATE_CAP) {
                unprocessedTime -= UPDATE_CAP;
                render = true;

                tick();

                if (frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;

                    System.out.println("FPS: " + fps);
                }
            }

            if (render) {
                frames++;

                render();
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        stop();
    }

    private void tick() {
        handler.tick();
        if (gameState == STATE.Menu) {
            menu.tick();
        } else if (gameState == STATE.Jeu) {
            //hud.tick();
            //spawner.tick();
//            if (HUD.HEALTH <= 0) {
//                HUD.HEALTH = 100;
//                gameState = STATE.Fin;
//                handler.object.clear();
//            }
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH + 25, HEIGHT + 25);
        try {
            handler.render(g);
        } catch (Exception e) {
        }
        if ((gameState == STATE.Menu) || (gameState == STATE.Aide) || (gameState == STATE.Fin)) {
            menu.render(g);
        } else if (gameState == STATE.Jeu) {
            //hud.render(g);
        }

        //Pour TESTS pour centre ecran
        g.setColor(Color.PINK);
        g.drawLine(0, HEIGHT / 4, WIDTH, HEIGHT / 4);
        g.drawLine(0, HEIGHT / 4 * 3, WIDTH, HEIGHT / 4 * 3);
        g.drawLine(WIDTH / 4, 0, WIDTH / 4, HEIGHT);
        g.drawLine(WIDTH / 4 * 3, 0, WIDTH / 4 * 3, HEIGHT);

        g.setColor(Color.RED);
        g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
        g.drawLine(0, HEIGHT / 2, WIDTH, HEIGHT / 2);

        g.dispose();
        bs.show();
    }

    public static float clamp(float position, float min, float max) {
        if (position >= max) {
            return position = max;
        } else if (position <= min) {
            return position = min;
        } else {
            return position;
        }
    }
}
