package starpunk.logic.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.managers.TagManager;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.math.Vector2;
import starpunk.Constants;
import starpunk.logic.PlayerInput;

public final class PlayerMovementSystem
  extends VoidEntitySystem
{
  private static final float MAX_ACCELERATION = 10;

  @Mapper( classifier = Constants.ACCELERATION_CLASSIFIER )
  private ComponentMapper<Vector2> _accelerationMapper;

  private Entity _player;

  @Override
  protected void processSystem()
  {
    ensurePlayerEntity();

    if( null != _player )
    {
      updatePlayer( _player );
    }
  }

  private void ensurePlayerEntity()
  {
    if( null == _player || !_player.isActive() )
    {
      _player = world.getManager( TagManager.class ).getEntity( "PLAYER" );
    }
  }

  private void updatePlayer( final Entity e )
  {
    final Vector2 acceleration = _accelerationMapper.get( e );

    final PlayerInput input = PlayerInput.getPlayerInput();
    acceleration.x = input.isLeft() ? -MAX_ACCELERATION : input.isRight() ? MAX_ACCELERATION : 0;
    acceleration.y = input.isUp() ? MAX_ACCELERATION : input.isDown() ? -MAX_ACCELERATION : 0;
  }
}
