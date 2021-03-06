package com.crazy_putting.game.GameObjects;


import com.badlogic.gdx.math.Vector3;
import com.crazy_putting.game.Components.GraphicsComponent;

public class GameObject {
    private GraphicsComponent _graphicComponent;
    protected Vector3 _position = new Vector3();//= Vector3.Zero;
    public GameObject(Vector3 pPosition)
    {
        _position = pPosition;
    }
    public GameObject()
    {}

    public void addGraphicComponent(GraphicsComponent pGC)
    {
        _graphicComponent = pGC;
        _graphicComponent.setOwner(this);
    }

    public GraphicsComponent getGraphicComponent() {
        return _graphicComponent;
    }

    public Vector3 getPosition(){
        return _position;
    }

}
