package starpunk;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.math.MathUtils;
import starpunk.components.Position;
import starpunk.components.Sprite;
import starpunk.components.Velocity;

public final class EntityFactory
{
  private EntityFactory()
  {
  }

  /**
   * Create a star entity within a particular bound.
   */
  public static Entity createStar( final World world, final int width, final int height )
  {
    final Entity e = world.createEntity();

    final Position position = new Position();
    position.setX( MathUtils.random( -width / 2, width / 2 ) );
    position.setY( MathUtils.random( -height / 2, height / 2 ) );
    e.addComponent( position );

    final Velocity velocity = new Velocity();
    velocity.setVectorX( 0.05f * MathUtils.random( -width / 2, width / 2 ) );
    velocity.setVectorY( 0.05f * MathUtils.random( -height / 2, height / 2 ) );
    e.addComponent( velocity );

    final Sprite sprite = new Sprite();
    sprite.setName( "star" );
    final float scale = MathUtils.random( 0.5f, 1f );
    sprite.setScaleY( scale );
    sprite.setScaleX( scale );
    sprite.setRotation( MathUtils.random( 0f, 360f ) );
    sprite.setR( MathUtils.random( 0.3f, 0.9f ) );
    sprite.setG( MathUtils.random( 0.3f, 0.9f ) );
    sprite.setB( MathUtils.random( 0.3f, 0.9f ) );
    sprite.setA( MathUtils.random( 0.1f, 0.5f ) );
    sprite.setLayer( Sprite.Layer.BACKGROUND );

    e.addComponent( sprite );

    return e;
  }
}
