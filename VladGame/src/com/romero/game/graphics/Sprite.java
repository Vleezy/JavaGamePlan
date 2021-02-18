package com.romero.game.graphics;

import com.romero.game.util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;


public class Sprite {

    private final BufferedImage SPRITESHEET = null;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 32;
    public int w;
    public int h;
    public int wSprite;
    private int hSprite;

    public Sprite(String file){
        w = TILE_SIZE;
        h = TILE_SIZE;

        System.out.println("Loading: " + file + "...");
        SPRITESHEET = loadSprite(file);

        wSprite = SPRITESHEET.getWidth() / w;
        hSprite = SPRITESHEET.getHeight() / h;
        loadSpriteArray();
    }

    public Sprite(String file, int w, int h){
        this.w = w;
        this.h = h;

        System.out.println("Loading: " + file + "...");
        SPRITESHEET = loadSprite(file);

        wSprite = SPRITESHEET.getWidth() / w;
        hSprite = SPRITESHEET.getHeight() / h;
        loadSpriteArray();
    }

    public void setSize(int width, int height){
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i){
        w = i;
        wSprite = SPRITESHEET.getWidth() / w;
    }

    public  void setHeight(int i){
        h = i;
        wSprite = SPRITESHEET.getHeight() / h;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight(){
        return h;
    }

    private BufferedImage loadSprite(String file){
        BufferedImage sprite = null;
        try{
            sprite = ImageIO.read(getClass().getClassLoader().getResource(file));
        }catch(Exception e){
           System.out.println("ERROR: Could not load file: " + file);
        }
        return sprite;
    }

    public void loadSpriteArray(){
        spriteArray = new BufferedImage[wSprite][hSprite]; //     Total number w: columns, h: rows in spritesheet

                for(int x = 0; x < wSprite; x++){
                    for(int y = 0; y < hSprite; y++){
                        spriteArray[x][y] = getSprite(x, y);
                    }
                }
             }

    public BufferedImage getSpriteSheet(){
        return SPRITESHEET;
    }

    public BufferedImage getSprite(int x, int y){
        return SPRITESHEET.getSubimage(x * w, y * h, w, h);
    }

    // Animation class
    public BufferedImage[] getSpriteArray(int i){
        return spriteArray[i];
    }

    // Animation class
    public BufferedImage[][] getSpriteArray2(int i){
        return spriteArray;
    }

//     Graphing images in a line
    public static void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Vector2f pos, int width, int height, int xOffset, int yOffset){
        float x = pos.x;
        float y = pos.y;

        for(int i = 0; i < img.size(); i++){
            if(img.get(i) != null){
                g.drawImage(img.get(i), (int) x, (int) y, width, height, null);
            }
            x += xOffset;
            y += yOffset;
        }
    }

//    Font
    public static void drawArray(Graphics2D g, Font f, String word, Vector2f pos, int width, int height, int xOffset, int yOffset){
        float x = pos.x;
        float y = pos.y;

        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) != 32)
                g.drawImage(f.getFont(word.charAt(i)), (int) x, (int) y, width, height, null);
        }

        x += xOffset;
        y += yOffset;
    }

}
