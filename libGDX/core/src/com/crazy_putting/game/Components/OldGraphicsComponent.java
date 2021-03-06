package com.crazy_putting.game.Components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.crazy_putting.game.GameObjects.GameObject;

import static com.crazy_putting.game.GameLogic.GraphicsManager.WORLD_HEIGHT;
import static com.crazy_putting.game.GameLogic.GraphicsManager.WORLD_WIDTH;

/**
 * A graphic component is an object that is rendered in the GUI e.g. a ball or a hole.
 */
public class OldGraphicsComponent extends Component {

    private GameObject _owner;
    private Texture _texture;
    private int _width;
    private int _height;

    public OldGraphicsComponent(Texture pTexture) {
       // GraphicsManager.addGraphicsComponent(this);
        _texture = pTexture;
        _width = 15;
        _height = 15;
    }

    public OldGraphicsComponent(Texture pTexture, int pWidth, int pHeight) {
        //GraphicsManager.addGraphicsComponent(this);
        _texture = pTexture;

        _width = pWidth;
        _height = pHeight;
    }
    /*public void setOwner( OldGameObject pGameObj)
    {
        _owner = pGameObj;
    }*/
    public void render(SpriteBatch pBach)
    {
        if(_texture == null) return;


        pBach.draw(_texture, _owner.getPosition().x+WORLD_WIDTH/2-_width/2, _owner.getPosition().y+WORLD_HEIGHT/2-_height/2,_width, _height);

    }

}
