package com.romero.game.tiles;


import com.romero.game.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;

public class TileMapNorm extends TileMap {

    private ArrayList<Block> blocks;

public TileMapNorm(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns){
    blocks = new ArrayList<Block>();

    String[] block = data.split(","); //   Removing commas
    for(int i = 0 ; i < (width * height); i++){
        int temp = Integer.parseInt(block[i].replaceAll("\\s+","")); //   Replacing with no space
            if(temp != 0){
                blocks.add(new NormBlock());
            }
    }
}

public void render(Graphics2D g){
    for(int i = 0; i < blocks.size(); i++){
        blocks.get(i).render(g);
    }
}

}
