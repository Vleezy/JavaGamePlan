package com.romero.game.graphics;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

//Copied from Sprite class

public class Font {

    private BufferedImage FONTSHEET = null;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 32;
    public int w;
    public int h;
    public int wLetter;
    private int hLetter;

    public Font(String file){
        w = TILE_SIZE;
        h = TILE_SIZE;

        System.out.println("Loading: " + file + "...");
        FONTSHEET = loadFont(file);

        wLetter = FONTSHEET.getWidth() / w;
        hLetter = FONTSHEET.getHeight() / h;
        loadFontArray();
    }

    public Font(String file, int w, int h){
        this.w = w;
        this.h = h;

        System.out.println("Loading: " + file + "...");
        FONTSHEET = loadFont(file);

        wLetter = FONTSHEET.getWidth() / w;
        hLetter = FONTSHEET.getHeight() / h;
        loadFontArray();
    }

    public void setSize(int width, int height){
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i){
        w = i;
        wLetter = FONTSHEET.getWidth() / w;
    }

    public  void setHeight(int i){
        h = i;
        wLetter = FONTSHEET.getHeight() / h;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight(){
        return h;
    }

    private BufferedImage loadFont(String file){
        BufferedImage sprite = null;
        try{
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        }catch(Exception e){
            System.out.println("ERROR: Could not load file: " + file);
        }
        return sprite;
    }

    public void loadFontArray(){
        spriteArray = new BufferedImage[wLetter][hLetter]; //     Total number w: columns, h: rows in spritesheet

        for(int x = 0; x < wLetter; x++){
            for(int y = 0; y < hLetter; y++){
                spriteArray[x][y] = getLetter(x, y);
            }
        }
    }

    public BufferedImage getFontSheet(){
        return FONTSHEET;
    }

    public BufferedImage getLetter(int x, int y){
        return FONTSHEET.getSubimage(x * w, y * h, w, h);
    }

    public BufferedImage getFont(char letter) {
        int value = letter - 65;
//        System.out.println(value);
        int x = value % wLetter;
        int y = value / wLetter;
        return getLetter(x,y);
    }
}
