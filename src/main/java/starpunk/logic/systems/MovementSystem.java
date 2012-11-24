package starpunk.logic.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import starpunk.logic.components.Acceleration;
import starpunk.logic.components.Position;
import starpunk.logic.components.Velocity;

public class MovementSystem
  extends EntityProcessingSystem
{
  @Mapper
  ComponentMapper<Position> _positionMapper;
  @Mapper
  ComponentMapper<Velocity> _velocityMapper;
  @Mapper
  ComponentMapper<Acceleration> _accelerationMapper;

  public MovementSystem()
  {
    super( Aspect.getAspectForAll( Position.class, Velocity.class ) );
  }

  @Override
  protected void process( final Entity e )
  {
    final Position position = _positionMapper.get( e );
    final Velocity velocity = _velocityMapper.get( e );
    final Acceleration acceleration = _accelerationMapper.get( e );

    velocity.setVectorX( velocity.getVectorX() + acceleration.getVectorX() );
    velocity.setVectorY( velocity.getVectorY() + acceleration.getVectorY() );

    position.setX( position.getX() + velocity.getVectorX() * world.delta );
    position.setY( position.getY() + velocity.getVectorY() * world.delta );
  }
}
