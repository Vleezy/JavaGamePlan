package com.romero.game.entity;

import com.romero.game.graphics.Animation;
import com.romero.game.graphics.Sprite;
import com.romero.game.util.AABB;
import com.romero.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

//Player, enemy anything that can move
public abstract class Entity {

    private final int UP = 3;
    private final int DOWN = 2;
    private final int RIGHT = 0;
    private final int LEFT = 1;
    protected int currentAnimation;

    protected Animation ani;
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;

    // Buttons
    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean attack;
    protected int attackSpeed;
    protected int attackDuration;

    protected float dx;
    protected float dy;

    //Movement Speeds
    protected float maxSpeed = 4f;
    protected float acc = 3f;
    protected float deacc = 0.3f; // Can use for a slide on ice effect

    protected AABB hitBounds;
    protected AABB bounds;



    public Entity(Sprite sprite, Vector2f origin, int size){
        this.sprite = sprite;
        pos = origin;
        this.size = size;

        bounds = new AABB(origin, size, size);
        hitBounds = new AABB(new Vector2f(origin.x + (size / 2), origin.y), size, size);

        ani = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
    }

    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }

    public void setSize(int i){
        size = i;
    }

    public void setMaxSpeed(float f){
        maxSpeed = f;
    }

//    Acceleration
    public void setAcc(float f){
        acc = f;
    }

    public void setDeacc(float f){
        deacc = f;
    }

    public AABB getBounds(){
        return bounds;
    }

    public int getSize(){
        return size;
    }
    public Animation getAnimation(){
        return ani;
    }

    public void setAnimation(int i, BufferedImage[] frames, int delay){
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }

    public void animate(){
        if(up){
            if(currentAnimation != UP || ani.getDelay() == -1){
                setAnimation(UP, sprite.getSpriteArray(UP), 5);
            }
        }
        else if(down){
            if(currentAnimation != DOWN || ani.getDelay() == -1){
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
            }
        }
        else if(right){
            if(currentAnimation != RIGHT || ani.getDelay() == -1){
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        }
        else if(left){
            if(currentAnimation != LEFT || ani.getDelay() == -1){
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        }
        else{
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }
    }

    private void setHitBoxDirection(){
        if(up){
            hitBounds.setYOffset(-size / 2);
            hitBounds.setXOffset(-size / 2);
        }
        else if(down){
            hitBounds.setYOffset(size / 2);
            hitBounds.setXOffset(-size / 2);
        }
        else if(left){
            hitBounds.setXOffset(-size);
            hitBounds.setYOffset(0);

        }
        else if(right){
            hitBounds.setXOffset(0);
            hitBounds.setYOffset(0);


        }
    }

    public void update(){
        animate();
        setHitBoxDirection();
        ani.update();
    }

    public abstract void render(Graphics2D g);
//    public void input(KeyHandler key, MouseHandler mouse){
//
//    }


}
