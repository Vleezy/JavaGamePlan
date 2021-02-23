package com.romero.game.tiles;

import java.awt.*;
import java.util.HashMap;
import com.romero.game.graphics.Sprite;

public class TileMapObj{

    public static HashMap<String, Block> tmo_blocks;

    public TileMapObj(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        Block tempBlock;
//        "character" -> block_1
        tmo_blocks = new HashMap<String, Block>();

        String[] block = data.split(","); //   Removing commas
        for (int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+", "")); //   Replacing with no space
            if (temp != 0) {
                if (temp == 172) {  // 172 is empty space outside walking area (for map 1)
                    tempBlock = new HoleBlock();
                } else {
                    tempBlock = new ObjBlock();
                }
                tmo_blocks.put(String.valueOf((int) (i % width)) + "," + String.valueOf((int) (i / height)), tempBlock);
            }
        }
    }

    public void render(Graphics2D g){
        for(Block block: tmo_blocks.value()){
            block.render(g);
        }
    }

}
