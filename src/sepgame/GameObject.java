/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sepgame;

import java.awt.Graphics;
import java.awt.Polygon;

/**
 *
 * @author lulambert
 */
public abstract class GameObject {

    protected float x, y;
    protected TYPE type;
    protected float velX, velY;

    public GameObject(float x, float y, TYPE type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Polygon getBounds();

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
    }
}
