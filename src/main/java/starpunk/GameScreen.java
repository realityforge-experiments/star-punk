package starpunk;

import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import starpunk.systems.MovementSystem;
import starpunk.systems.SpriteRenderSystem;

public class GameScreen
  implements Screen
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
  public void render( final float delta )
  {
    Gdx.gl10.glClear( GL10.GL_COLOR_BUFFER_BIT );

    _camera.update();

    _world.setDelta( delta );
    _world.process();

    _renderSystem.process();
  }

  @Override
  public void resize( int width, int height )
  {
  }

  @Override
  public void show()
  {
  }

  @Override
  public void hide()
  {
  }

  @Override
  public void pause()
  {
  }

  @Override
  public void resume()
  {
  }

  @Override
  public void dispose()
  {
  }
}
