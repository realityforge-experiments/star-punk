package starpunk.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import starpunk.components.Position;
import starpunk.components.Velocity;

public class MovementSystem
  extends EntityProcessingSystem
{
  @Mapper
  ComponentMapper<Position> _positionMapper;
  @Mapper
  ComponentMapper<Velocity> _velocityMapper;

  public MovementSystem()
  {
    super( Aspect.getAspectFor( Position.class, Velocity.class ) );
  }

  @Override
  protected void process( final Entity e )
  {
    final Position position = _positionMapper.get( e );
    final Velocity velocity = _velocityMapper.get( e );

    position.setX( position.getX() + velocity.getVectorX() * world.delta );
    position.setY( position.getY() + velocity.getVectorY() * world.delta );
  }

}
