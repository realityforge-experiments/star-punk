package starpunk.screens;

import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import starpunk.EntityFactory;
import starpunk.StarPunkGame;
import starpunk.systems.MovementSystem;
import starpunk.systems.SpriteRenderSystem;

public final class GameScreen
  extends BaseScreen
{
  private World _world;
  private OrthographicCamera _camera;
  private SpriteRenderSystem _renderSystem;

  public GameScreen()
  {
    _camera = new OrthographicCamera( StarPunkGame.WIDTH, StarPunkGame.HEIGHT );

    _world = new World();
    _renderSystem = _world.setSystem( new SpriteRenderSystem( _camera ), true );
    _world.setSystem( new MovementSystem() );
    _world.initialize();

    for( int i = 0; 500 > i; i++ )
    {
      EntityFactory.createStar( _world, StarPunkGame.WIDTH, StarPunkGame.HEIGHT ).addToWorld();
    }
  }

  @Override
  public void update( final float delta )
  {
    _camera.update();

    _world.setDelta( delta );
    _world.process();
  }

  @Override
  public void draw( final float delta )
  {
    Gdx.gl10.glClear( GL10.GL_COLOR_BUFFER_BIT );
    _renderSystem.process();
  }
}
