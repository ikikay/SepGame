/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sepgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 *
 * @author ikika
 */
public class Bullet extends GameObject {

    protected Handler handler;

    public Bullet(float x, float y, TYPE type, Handler handler, float mx, float my) {
        super(x, y, type);
        this.handler = handler;
        
        
        velX = (mx - x) / 20;
        velY = (my - y) / 20;
    }

    public void tick() {
        x += velX;
        y += velY;

        x = SepGame.clamp(x, 0, SepGame.WIDTH - 8);
        y = SepGame.clamp(y, 0, SepGame.HEIGHT - 8);
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        //g.fillOval((int) x, (int) y, 8, 8);

        int xPoly[] = {(int) x - 4, (int) x + 4, (int) x - 4, (int) x + 4};
        int yPoly[] = {(int) y - 4, (int) y - 4, (int) y + 4, (int) y + 4};

        Polygon p = new Polygon(xPoly, yPoly, xPoly.length);
        g.fillPolygon(p);
        g.drawPolygon(p);
    }

    @Override
    public Polygon getBounds() {
        int xPoly[] = {(int) x - 4, (int) x + 4, (int) x - 4, (int) x + 4};
        int yPoly[] = {(int) y - 4, (int) y - 4, (int) y + 4, (int) y + 4};

        return new Polygon(xPoly, yPoly, xPoly.length);
    }

}
