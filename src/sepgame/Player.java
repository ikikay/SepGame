/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sepgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Random;

/**
 *
 * @author lulambert
 */
public class Player extends GameObject {

    private Handler handler;
    Random r = new Random();

    public Player(float x, float y, TYPE type, Handler handler) {
        super(x, y, type);
        this.handler = handler;
    }

    @Override
    public void tick() {
        if (vitesse >= 2) {
            vitesse = 2f;
        }

        if (handler.isUp()) {
            velY = -5 * vitesse;
        } else if (!handler.isDown()) {
            velY = 0;
        }

        if (handler.isDown()) {
            velY = 5 * vitesse;
        } else if (!handler.isUp()) {
            velY = 0;
        }

        if (handler.isLeft()) {
            velX = -5 * vitesse;
        } else if (!handler.isRight()) {
            velX = 0;
        }

        if (handler.isRight()) {
            velX = 5 * vitesse;
        } else if (!handler.isLeft()) {
            velX = 0;
        }

        x += velX;
        y += velY;
        x = SepGame.clamp(x, 0 + 16, SepGame.WIDTH - 16 - 1);
        y = SepGame.clamp(y, 0 + 16, SepGame.HEIGHT - 16 - 1 - 30);

        collision();
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            switch (tempObject.getType()) {
                case Player:

                    break;
            }
        }
    }

    @Override
    public void render(Graphics g
    ) {
        g.setColor(Color.gray);
        int xPolyShadow[] = {(int) x - 11, (int) x, (int) x + 11};
        int yPolyShadow[] = {(int) y + 21, (int) y - 11, (int) y + 21};
        Polygon pShadow = new Polygon(xPolyShadow, yPolyShadow, xPolyShadow.length);
        g.fillPolygon(pShadow);
        g.drawPolygon(pShadow);

        g.setColor(Color.white);
        int xPoly[] = {(int) x - 16, (int) x, (int) x + 16};
        int yPoly[] = {(int) y + 16, (int) y - 16, (int) y + 16};
        Polygon p = new Polygon(xPoly, yPoly, xPoly.length);
        g.fillPolygon(p);
        g.drawPolygon(p);
    }

    @Override
    public Polygon getBounds() {
        int xPoly[] = {(int) x - 16, (int) x, (int) x + 16};
        int yPoly[] = {(int) y + 16, (int) y - 16, (int) y + 16};

        return new Polygon(xPoly, yPoly, xPoly.length);
    }
}
