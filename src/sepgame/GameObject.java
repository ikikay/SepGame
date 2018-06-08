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

    protected float life;
    protected float lifeMax;
    protected float shield;

    public GameObject(float x, float y, TYPE type) {
        this.x = x;
        this.y = y;
        this.type = type;

        this.life = 100;
        this.lifeMax = this.life;
        this.shield = 0;
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

    public float getLife() {
        return life;
    }

    public void setLife(float life) {
        this.life = life;
    }

    public float getShield() {
        return shield;
    }

    public void setShield(float shield) {
        this.shield = shield;
    }

    public float getLifeMax() {
        return lifeMax;
    }

    public void setLifeMax(float lifeMax) {
        this.lifeMax = lifeMax;
    }

    public float getPourcentLife() {
        return this.life * 100 / this.lifeMax; 
    }
}
