package starpunk.logic.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import starpunk.Constants;

public class PhysicsSystem
  extends EntityProcessingSystem
{
  @Mapper( classifier = Constants.POSITION_CLASSIFIER )
  private ComponentMapper<Vector2> _positionMapper;
  @Mapper( classifier = Constants.VELOCITY_CLASSIFIER )
  private ComponentMapper<Vector2> _velocityMapper;
  @Mapper(classifier = Constants.ACCELERATION_CLASSIFIER)
  private ComponentMapper<Vector2> _accelerationMapper;

  private World _world;

  public PhysicsSystem()
  {
    super( Aspect.getEmpty() );
  }

  @Override
  protected void initialize()
  {
    final Vector2 gravity = new Vector2( 0, 0 );
    final boolean doSleep = true;
    _world = new World( gravity, doSleep );

    final BodyDef groundBodyDef = new BodyDef();
    groundBodyDef.position.set( 0, -10 );
    final Body groundBody = _world.createBody( groundBodyDef );
    final PolygonShape groundBox = new PolygonShape();
    groundBox.setAsBox( 50, 10 );
    groundBody.createFixture( groundBox, 0 );

    // Dynamic Body
    final BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.DynamicBody;
    bodyDef.position.set( 0, 4 );
    final Body body = _world.createBody( bodyDef );
    final PolygonShape dynamicBox = new PolygonShape();
    dynamicBox.setAsBox( 1, 1 );
    final FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = dynamicBox;
    fixtureDef.density = 1;
    fixtureDef.friction = 0.3f;
    body.createFixture( fixtureDef );
  }

  @Override
  protected void processEntities( final ImmutableBag<Entity> entities )
  {
    // Setup world
    final float timeStep = super.world.delta;
    final int velocityIterations = 6;
    final int positionIterations = 2;

    _world.step( timeStep, velocityIterations, positionIterations );
    super.processEntities( entities );
  }

  @Override
  protected void process( final Entity e )
  {
    //_positionMapper.getSafe( e ).setX(  );
  }

  @Override
  protected boolean checkProcessing()
  {
    return true;
  }
}
