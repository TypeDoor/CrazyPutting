package com.crazy_putting.game.GameObjects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.crazy_putting.game.Others.Velocity;

public abstract class PhysicsGameObject extends GameObject {
    public abstract Texture getTexture() ;
    public abstract Velocity getVelocity();
    public abstract float getMass();
    public abstract void setPosition(Vector3 position);
    public abstract void setPositionX(float x);
    public abstract void setPositionY(float y);
    public abstract void setVelocity(float speed, float angle);
    public abstract void setSpeed(float speed);
    public abstract boolean inTheWater();
    public abstract Vector3 getPreviousPosition();
    public abstract float getSpeed();
    public abstract boolean isFixed();
    public abstract void fix(boolean tf);
    public abstract boolean isSlow();
    public abstract void setVelocityComponents(float newSpeedX, float newSpeedY);

}
