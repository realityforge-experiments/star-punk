package starpunk.logic.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector2;
import starpunk.Constants;

public class MovementSystem
  extends EntityProcessingSystem
{
  @Mapper( classifier = Constants.POSITION_CLASSIFIER )
  private ComponentMapper<Vector2> _positionMapper;
  @Mapper( classifier = Constants.VELOCITY_CLASSIFIER )
  private ComponentMapper<Vector2> _velocityMapper;
  @Mapper( classifier = Constants.ACCELERATION_CLASSIFIER )
  private ComponentMapper<Vector2> _accelerationMapper;

  public MovementSystem()
  {
    super( Aspect.getAspectForAll( Constants.POSITION, Constants.VELOCITY ) );
  }

  @Override
  protected void process( final Entity e )
  {
    final Vector2 position = _positionMapper.get( e );
    final Vector2 velocity = _velocityMapper.get( e );
    final Vector2 acceleration = _accelerationMapper.get( e );

    velocity.x = velocity.x + acceleration.x;
    velocity.y = velocity.y + acceleration.y;

    position.x = position.x + velocity.x * world.delta;
    position.y = position.y + velocity.y * world.delta;
  }
}
