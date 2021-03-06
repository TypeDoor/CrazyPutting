package com.crazy_putting.game.Physics;

import com.badlogic.gdx.Gdx;
import com.crazy_putting.game.GameLogic.CourseManager;
import com.crazy_putting.game.GameLogic.GraphicsManager;
import com.crazy_putting.game.GameObjects.PhysicsGameObject;

public class Physics {

    private static final double g = 9.81;
    //just create a friction coefficient here for now
    private static float mu;


    private static double EPSILON = 1;


    private static double partialDerivativeX(PhysicsGameObject obj) {
        float x1 = (float) (obj.getPosition().x + EPSILON);
        float x2 = (float) (x1 - 2 * EPSILON);
        float y = obj.getPosition().y;
        float result = (float) ((CourseManager.calculateHeight(x1, y) - CourseManager.calculateHeight(x2, y)) / 2 * EPSILON);
        //float difference = (float) (result - (0.01 + obj.getPosition().x * 0.06));
        //System.out.println("approximation error: " + difference);
        return result;
    }

    private static double partialDerivativeY(PhysicsGameObject obj) {
        float x = (float) (obj.getPosition().x + EPSILON);
        float y1 = obj.getPosition().y;
        float y2 = (float) (y1 - 2 * EPSILON);
        float result = (float) ((CourseManager.calculateHeight(x, y1) - CourseManager.calculateHeight(x, y2)) / 2 * EPSILON);
        //System.out.println(result);
        return result;
    }

    public static void updateCoefficients() {
        mu = CourseManager.getActiveCourse().getFriction();


    }




    public static void update(PhysicsGameObject obj, double dt) {
        if (obj.isFixed()) {
            return;
        }


        float x = obj.getPosition().x;
        float y = obj.getPosition().y;


        if (collided(obj)) {
            obj.setPosition(CourseManager.getStartPosition());
            obj.fix(true);

            Gdx.app.log("Message","Ball collided");
            return;
        }
//        System.out.println("Get velocity "+obj.getVelocity().Vx);
        obj.getPreviousPosition().x = x;
        obj.getPreviousPosition().y = y;
        //calculation of a new X position
        // x(t + h) = x(t) +hVx(t);
        float newX = (float) (x + (dt * obj.getVelocity().Vx));

        //calculation of a new Y position
        // y(t+h) = y(t) + hVy(t);
        float newY = (float) (y + (dt * obj.getVelocity().Vy));

        //calculation of a new total velocity of the ball
        // v(t+h) = v(t) + h*F(x,y,vx,vy)/m
        float newSpeedX = (float) (obj.getVelocity().Vx + dt * totalForceX(obj) / obj.getMass());
        float newSpeedY = (float) (obj.getVelocity().Vy + dt * totalForceY(obj) / obj.getMass());
        obj.setVelocityComponents(newSpeedX, newSpeedY);
        obj.getVelocity().Vx = newSpeedX;
        obj.getVelocity().Vy = newSpeedY;

        obj.setPositionX(newX);
        obj.setPositionY(newY);

///        System.out.println("Update physics x: "+newX+" y: "+newY+" speed: "+(Math.sqrt(Math.pow(obj.getVelocity().Vx,2)+Math.pow(obj.getVelocity().Vy,2)))+" Vx: "+obj.getVelocity().Vx+" Vy: "+obj.getVelocity().Vy);
//        Gdx.app.log("Physics","Speed: "+obj.getSpeed());
    }


    public static boolean collided(PhysicsGameObject obj) {
        //current position of the ball
        float x2 = obj.getPosition().x;
        float y2 = obj.getPosition().y;

        if (x2 > GraphicsManager.WORLD_WIDTH / 2 || x2 < GraphicsManager.WORLD_WIDTH / 2 * (-1) || y2 > GraphicsManager.WORLD_HEIGHT / 2 || y2 < GraphicsManager.WORLD_HEIGHT / 2 * (-1)) {
            System.out.println("Out of bounds");
            return true;

        }

        //previous position of the ball
        float x1 = obj.getPreviousPosition().x;
        float y1 = obj.getPreviousPosition().y;


        float dx = x2 - x1;
        float dy = y2 - y1;
        for (int i = 1; i < 4; i++) {
            if (CourseManager.calculateHeight(x1 + dx / i, equation2Points(dx, dy, x1 + dx / i, x1, y1)) < 0) {
                return true;
            }
        }
        return false;

    }

    //equation of the line is: y = dy/dx  - (dy/dx)*x1 + y1
    private static float equation2Points(float dx, float dy, float xValue, float previousX, float previousY) {
        float k = dy / dx;
        return k * xValue - k * previousX + previousY;
    }

    /*
    total power that affects the ball is
    F = G + H;
    */

    //Calculation of the Gravitational Force
    //G = -mgh(,x) - mgh(,y)
    public static float gravityForceX(PhysicsGameObject obj) {

        return (float) (-obj.getMass() * g * partialDerivativeX(obj));
    }

    public static float gravityForceY(PhysicsGameObject obj) {
        return (float) (-obj.getMass() * g * partialDerivativeY(obj));
    }

    /*
    Calculation of the Force of friction
    H = -(mu)* m* v / ||V||
    V = vx/cos(x)
  */
    public static float frictionForceX(PhysicsGameObject obj) {

        float numerator = (float) (-mu * obj.getMass() * g * obj.getVelocity().Vx);
        float lengthOfVelocityVector = (float) (Math.pow(obj.getVelocity().Vx, 2) + Math.pow(obj.getVelocity().Vy, 2));
        float denominator = (float) Math.sqrt(lengthOfVelocityVector);

        return numerator / denominator;
    }

    public static float frictionForceY(PhysicsGameObject obj) {
        float numerator = (float) (-mu * obj.getMass() * g * obj.getVelocity().Vy);
        float lengthOfVelocityVector = (float) (Math.pow(obj.getVelocity().Vx, 2) + Math.pow(obj.getVelocity().Vy, 2));
        float denominator = (float) Math.sqrt(lengthOfVelocityVector);

        return numerator / denominator;
    }


    public static float totalForceX(PhysicsGameObject obj) {
        return gravityForceX(obj) + frictionForceX(obj);
    }

    public static float totalForceY(PhysicsGameObject obj) {
        return gravityForceY(obj) + frictionForceY(obj);
    }


}


