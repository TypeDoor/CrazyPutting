package com.crazy_putting.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.crazy_putting.game.Others.InputData;
import sun.applet.Main;

public class GolfGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public InputData input;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(1.1f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        input = new InputData();
        this.setScreen(new MainScreen(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }


    @Override
    public void render() {
        super.render();
    }
}