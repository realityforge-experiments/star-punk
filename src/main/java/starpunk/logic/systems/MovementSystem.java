package starpunk.logic.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector2;
import starpunk.Constants;
import starpunk.logic.components.Acceleration;
import starpunk.logic.components.Velocity;

public class MovementSystem
  extends EntityProcessingSystem
{
  @Mapper( classifier = Constants.POSITION_CLASSIFIER )
  private ComponentMapper<Vector2> _positionMapper;
  @Mapper
  private ComponentMapper<Velocity> _velocityMapper;
  @Mapper
  private ComponentMapper<Acceleration> _accelerationMapper;

  public MovementSystem()
  {
    super( Aspect.getAspectForAll( Velocity.class ).all( Constants.POSITION ) );
  }

  @Override
  protected void process( final Entity e )
  {
    final Vector2 position = _positionMapper.get( e );
    final Velocity velocity = _velocityMapper.get( e );
    final Acceleration acceleration = _accelerationMapper.get( e );

    velocity.setVectorX( velocity.getVectorX() + acceleration.getVectorX() );
    velocity.setVectorY( velocity.getVectorY() + acceleration.getVectorY() );

    position.x = position.x + velocity.getVectorX() * world.delta;
    position.y = position.y + velocity.getVectorY() * world.delta;
  }
}
