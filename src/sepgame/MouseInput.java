/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sepgame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import sepgame.SepGame.STATE;

/**
 *
 * @author lulambert
 */
public class MouseInput extends MouseAdapter {

    private Handler handler;
    private SepGame game;

    public MouseInput(SepGame game, Handler handler) {
        this.handler = handler;
        this.game = game;
    }

    public void mousePressed(MouseEvent e) {
        float mx = e.getX();
        float my = e.getY();

        if (game.gameState == STATE.Jeu) {
            System.out.println(e.getClickCount() + " click(s)");
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);

                if (tempObject.getType() == TYPE.Player) {
                    // Todo au clique
                }
            }
        }
    }
}
