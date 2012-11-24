package starpunk.logic.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.managers.TagManager;
import com.artemis.utils.ImmutableBag;
import starpunk.logic.PlayerInput;
import starpunk.logic.components.Acceleration;

public final class PlayerMovementSystem
  extends EntitySystem
{
  private static final float MAX_ACCELERATION = 10;

  @Mapper
  ComponentMapper<Acceleration> _accelerationMapper;
  private Entity _player;

  public PlayerMovementSystem()
  {
    super( Aspect.getAspectForAll() );
  }

  @Override
  public void initialize()
  {
    ensurePlayerEntity();
  }

  @Override
  protected void processEntities( final ImmutableBag<Entity> entities )
  {
    ensurePlayerEntity();

    if( null != _player )
    {
      updatePlayer( _player );
    }
  }

  @Override
  protected boolean checkProcessing()
  {
    return true;
  }

  private void ensurePlayerEntity()
  {
    if( null == _player || !_player.isActive() )
    {
      _player = world.getManager( TagManager.class ).getEntity( "PLAYER" );
    }
  }

  protected void updatePlayer( Entity e )
  {
    final Acceleration acceleration = _accelerationMapper.get( e );

    final PlayerInput input = PlayerInput.getPlayerInput();
    acceleration.setVectorX( input.isLeft() ? -MAX_ACCELERATION : input.isRight() ? MAX_ACCELERATION : 0 );
    acceleration.setVectorY( input.isUp() ? MAX_ACCELERATION : input.isDown() ? -MAX_ACCELERATION : 0 );
  }
}
