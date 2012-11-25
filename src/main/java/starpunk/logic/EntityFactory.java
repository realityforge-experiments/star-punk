package starpunk.logic;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import starpunk.Constants;
import starpunk.logic.components.Sprite;

public final class EntityFactory
{
  private EntityFactory()
  {
  }

  /** Create a star entity within a particular bound. */
  public static Entity createStar( final World world, final int width, final int height )
  {
    final Entity e = world.createEntity();

    final Vector2 position = new Vector2();
    position.set( MathUtils.random( -width / 2, width / 2 ),
                  MathUtils.random( -height / 2, height / 2 ) );
    e.addComponent( Constants.POSITION, position );

    final Vector2 velocity = new Vector2();
    velocity.x = 0.05f * MathUtils.random( -width / 2, width / 2 );
    velocity.y = 0.05f * MathUtils.random( -height / 2, height / 2 );
    e.addComponent( Constants.VELOCITY, new Vector2() );
    e.addComponent( Constants.ACCELERATION, new Vector2() );

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

  public static Entity createShip( final World world, final int x, final int y )
  {
    final Entity e = world.createEntity();

    e.addComponent( Constants.POSITION, new Vector2( x, y ) );
    e.addComponent( Constants.VELOCITY, new Vector2() );
    e.addComponent( Constants.ACCELERATION, new Vector2() );

    final Sprite sprite = new Sprite();
    sprite.setName( "images/u_fighter" );
    sprite.setFrame( 2 );
    sprite.setScaleY( 2 );
    sprite.setScaleX( 2 );
    sprite.setLayer( Sprite.Layer.DEFAULT );

    e.addComponent( sprite );
    return e;
  }
}
