package com.crazy_putting.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.crazy_putting.game.GameLogic.CourseManager;
import com.crazy_putting.game.GameObjects.Course;

import static com.crazy_putting.game.GameLogic.GraphicsManager.WINDOW_HEIGHT;
import static com.crazy_putting.game.GameLogic.GraphicsManager.WINDOW_WIDTH;

public class CourseCreatorScreen implements Screen {
    private static GolfGame game;
    private Stage stage;
    private TextButton confirmButton;
//    private TextField textField;

    private TextField heightText;
    private TextField frictionText;
    private TextField startTextX;
    private TextField startTextY;
    private TextField goalTextX;
    private TextField goalTextY;
    private TextField radiusText;
    private TextField maxVelocityText;
    public CourseCreatorScreen(GolfGame game){
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("skin/plain-james-ui.json"));

        Label title = new Label("Course creator", skin);
        title.setColor(Color.BLUE);
        title.setPosition(WINDOW_WIDTH/2-title.getWidth()/2,WINDOW_HEIGHT*0.75f);

        confirmButton = new TextButton("Confirm", skin);
        Vector2 buttonSize = new Vector2(200,50);
        confirmButton.setPosition(WINDOW_WIDTH/2-buttonSize.x/2, buttonSize.y*2);
        confirmButton.setSize(buttonSize.x,buttonSize.y);
        confirmButton.addListener(new ClickListener(){

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                confirmButtonClicked();
            }
        });
        confirmButton.setColor(Color.WHITE);
        Label heightLabel = new Label("Height function",skin);
        heightText = new TextField("", skin);
        Label frictionLabel = new Label("Friction coefficient",skin);
         frictionText = new TextField("", skin);
        Label startLabelX = new Label("Start position X",skin);
        startTextX = new TextField("", skin);
        Label startLabelY = new Label("Start position Y",skin);
        startTextY = new TextField("", skin);
        Label goalLabelX = new Label("Goal position X",skin);
        goalTextX = new TextField("", skin);
        Label goalLabelY = new Label("Goal position Y",skin);
        goalTextY = new TextField("", skin);
        Label radiusLabel = new Label("Radius of the target",skin);
         radiusText = new TextField("", skin);
        Label maxVelocityLabel = new Label("Max velocity",skin);
        maxVelocityText = new TextField("", skin);
        /*
            Important: To change color of font of a label/button/etc. you need to change it in assets in .json
            file of a skin, where it is defined for a particular component.
            You can also add your own colors in RGB format.
         */

        Table table = new Table();
        table.setWidth(WINDOW_WIDTH);
        table.align(Align.center);
        table.setPosition(0,WINDOW_HEIGHT/2);
        table.add(heightLabel);
        table.add(heightText);
        table.row();
        table.add(frictionLabel);
        table.add(frictionText);
        table.row();
        table.add(startLabelX);
        table.add(startTextX);

        table.add(startLabelY);
        table.add(startTextY);
        table.row();
        table.add(goalLabelX);
        table.add(goalTextX);
        table.add(goalLabelY);
        table.add(goalTextY);
        table.row();
        table.add(radiusLabel);
        table.add(radiusText);
        table.row();
        table.add(maxVelocityLabel);
        table.add(maxVelocityText);
//        textField = new TextField("", skin);
//        Vector2 textFieldSize = new Vector2(200,50);
//        textField.setPosition(confirmButton.getX(),confirmButton.getY()-buttonSize.y);
//        textField.setSize(textFieldSize.x, textFieldSize.y);
//        stage.addActor(textField);
        stage.addActor(title);
        stage.addActor(table);
        stage.addActor(confirmButton);

    }

    public void confirmButtonClicked(){
        // TODO game logic needs to be implemented
        createCourse();
        System.out.println("Put here game logic...");
        CourseManager.setActiveCourseWithIndex(CourseManager.getCourseAmount()-1);
        game.setScreen(new MenuScreen(game));
    }
    private  void createCourse()
    {
        try {
            Course newCourse = new Course();
            newCourse.setName("Course without name D:");
            newCourse.setHeight(heightText.getText());
            newCourse.setFriction(Float.parseFloat(frictionText.getText()));
            newCourse.setBallStartPos(new Vector2(Float.parseFloat(startTextX.getText()), Float.parseFloat(startTextY.getText())));
            newCourse.setGoalPosition(new Vector2(Float.parseFloat(goalTextX.getText()), Float.parseFloat(goalTextY.getText())));
            newCourse.setGoalRadius(Float.parseFloat(radiusText.getText()));
            newCourse.setMaxSpeed(Float.parseFloat(maxVelocityText.getText()));
            CourseManager.addCourseToList(newCourse);
        }catch(Exception e)
        {
            System.out.println("Error saving course... Going Back to Menu");
            System.out.println(e.toString());
            System.out.println("Error saving course... Going Back to Menu");
            System.out.println("Error saving course... Going Back to Menu");
            game.setScreen(new MenuScreen(game));
        }
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
