package com.romero.game.states;

import com.romero.game.entity.Player;
import com.romero.game.graphics.Font;
import com.romero.game.graphics.Sprite;
import com.romero.game.util.KeyHandler;
import com.romero.game.util.MouseHandler;
import com.romero.game.util.Vector2f;

import java.awt.Graphics2D;
import java.awt.Color;

public class PlayState extends GameState {

    private Font font;
    private Player player;

    public PlayState(GameStateManager gsm){

        super(gsm);
        // To fix the issue File > Project Structure > Modules: Click Res, then mark it as source. Apply and Save.
        font = new Font("font/font.png", 16, 16);
        player = new Player(new Sprite("entity/Player.png"), new Vector2f(300,300), 128); // Player
    }

    public void update(){
        player.update();
    }

    public void input(MouseHandler mouse, KeyHandler key){
        player.input(mouse, key);

    }

    public void render(Graphics2D g){
        Sprite.drawArray(g, font, "My Java Game", new Vector2f(100,100), 32, 32, 16, 0); //   Caps for now
        player.render(g);
    }


}
