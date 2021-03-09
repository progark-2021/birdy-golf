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

public class PhysicsComponent implements Component {
    // First we create a body definition
    Body body;
    BodyDef bodyDef;
    World world;
    FixtureDef fixtureDef;
    Fixture fixture;

    public PhysicsComponent(){
        this.world= new World(new Vector2(0, 0), true);
        this.bodyDef = new BodyDef();
        setType(bodyDef);
        this.body = world.createBody(this.bodyDef);
        this.fixtureDef = new FixtureDef();
        setFixture();
        this.fixture = getBody().createFixture(fixtureDef);
    }
    public Body getBody(){
        return this.body;
    }
    public Vector2 getPosition(){
        return this.body.getPosition();
    }
    public com.badlogic.gdx.physics.box2d.Shape getShape(){
        return this.fixtureDef.shape;
    }
    public BodyDef getBodyDef(){
        return this.bodyDef;
    }


    private void setType(BodyDef bodyDef){
        this.bodyDef.type = BodyDef.BodyType.DynamicBody;
        this.bodyDef.position.set(100, 300);
        System.out.println(bodyDef.position);
    }

    private void setFixture(){
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
