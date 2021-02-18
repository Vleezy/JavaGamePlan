package com.romero.game.states;

import com.romero.game.util.KeyHandler;
import com.romero.game.util.MouseHandler;

import java.awt.Graphics2D;

public class PlayState extends GameState {

    public PlayState(GameStateManager gsm){
        super(gsm);
    }

    public void update(){

    }

    public void input(MouseHandler mouse, KeyHandler key){

    }

    public void render(Graphics2D g){
        g.setColor(Color.RED);
        g.fillRect(100,100,64,64);
    }


}
