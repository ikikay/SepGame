/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sepgame;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author lulambert
 */
public class Hud {

    private Handler handler;

    public static int level = 1;
    public static int score = 0;

    public static float playerLifePourcent = 100;

    private float lifeGreenValue = 255;

    public Hud(Handler handler) {
        this.handler = handler;
    }

    public void tick() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getType() == TYPE.Player) {
                playerLifePourcent = tempObject.getPourcentLife();
            }
        }

        //lifeGreenValue = SepGame.clamp(lifeGreenValue, 0, 255);
        //lifeGreenValue = 100 * 2;
        score++;
    }

    public void render(Graphics g) {
        //Scores
        g.setColor(Color.white);
        g.drawString("Level: " + level, 5, SepGame.HEIGHT - 50);
        g.drawString("Score: " + score, 5, SepGame.HEIGHT - 35);

        //Armements      
        g.setColor(Color.black);
        g.fillRect(SepGame.WIDTH - 55, SepGame.HEIGHT - 85, 50, 50);
        g.fillRect(SepGame.WIDTH - 105, SepGame.HEIGHT - 85, 50, 50);
        g.fillRect(SepGame.WIDTH - 155, SepGame.HEIGHT - 85, 50, 50);

        g.setColor(Color.white);
        g.drawRect(SepGame.WIDTH - 55, SepGame.HEIGHT - 85, 50, 50);
        g.drawRect(SepGame.WIDTH - 105, SepGame.HEIGHT - 85, 50, 50);
        g.drawRect(SepGame.WIDTH - 155, SepGame.HEIGHT - 85, 50, 50);

        //Bouclier
        g.setColor(Color.white);//BG
        g.fillRect(0, SepGame.HEIGHT - 30, SepGame.WIDTH, 15);//BG
        g.setColor(Color.gray);
        g.fillRect(0, SepGame.HEIGHT - 30, SepGame.WIDTH, 15);
        g.setColor(Color.white);//contours
        g.drawRect(0, SepGame.HEIGHT - 30, SepGame.WIDTH, 15);//contours

        //Vie
        g.setColor(Color.red);//BG
        g.fillRect(0, SepGame.HEIGHT - 15, SepGame.WIDTH, 15);//BG
        g.setColor(Color.green);
        g.fillRect(0, SepGame.HEIGHT - 15, (int) (playerLifePourcent * ((float) SepGame.WIDTH / 100)), 15);
        g.setColor(Color.white);//contours
        g.drawRect(0, SepGame.HEIGHT - 15, SepGame.WIDTH, 15);//contours
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
