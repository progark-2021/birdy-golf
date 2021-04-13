package no.birdygolf.gruppe19.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;


//Our first component
public class PhysicsComponent implements Component {
    Body body;
    BodyDef bodyDef;
    World world;
    FixtureDef fixtureDef;
    Fixture fixture;

    public PhysicsComponent(int xPosition, int yPosition){
        this.world= new World(new Vector2(0, 0), true);
        this.bodyDef = new BodyDef();
        setType(bodyDef);
        setPosition(xPosition, yPosition);
        this.body = world.createBody(this.bodyDef);
        this.fixtureDef = new FixtureDef();
        //setFixture();
        this.fixture = getBody().createFixture(fixtureDef);
    }
    public Body getBody(){
        return this.body;
    }
    public Vector2 getPosition(){
        return this.body.getPosition();
    }

    public BodyDef getBodyDef(){
        return this.bodyDef;
    }


    private void setType(BodyDef bodyDef){
        //Dynamic bodies are objects which move around and are affected by forces and other objects
        this.bodyDef.type = BodyDef.BodyType.DynamicBody;
    }
    private void setPosition(int x, int y){
        this.bodyDef.position.set(x,y);
        System.out.println(bodyDef.position);
    }


    // Separated into own components

    private void setFixture(){
        // we can change this to be less hardcoded by adding some of these as parameters in the constructor
        CircleShape circle = new CircleShape();
        circle.setRadius(6f);
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;
    }


    private void initializeBox2D() {
        Box2D.init();
    }

    public void render(float delta) {
        initializeBox2D();
    }
}
