package com.romero.game.states;

import com.romero.game.GamePanel;
import com.romero.game.entity.Player;
import com.romero.game.graphics.Font;
import com.romero.game.graphics.Sprite;
import com.romero.game.util.KeyHandler;
import com.romero.game.util.MouseHandler;
import com.romero.game.util.Vector2f;
import com.romero.game.GamePanel;

import java.awt.Graphics2D;
import java.awt.Color;

public class PlayState extends GameState {

    private Font font;
    private Player player;

    public PlayState(GameStateManager gsm){

        super(gsm);
        // To fix the issue File > Project Structure > Modules: Click Res, then mark it as source. Apply and Save.
        font = new Font("font/Font2.png", 10, 10);
        player = new Player(new Sprite("entity/Player.png"), new Vector2f(300,300), 128); // Player
    }

    public void update(){
        player.update();
    }

    public void input(MouseHandler mouse, KeyHandler key){
        player.input(mouse, key);
    }

    private String test = "Vlad is so sexy :P";

    public void render(Graphics2D g){
        Sprite.drawArray(g, font, GamePanel.oldFrameCount + " FPS", new Vector2f(GamePanel.width - 192, 32), 32, 32, 28, 0);

        Sprite.drawArray(g, font, test, new Vector2f(0, 64), 32, 32, 24, 0);

        player.render(g);
    }


}
