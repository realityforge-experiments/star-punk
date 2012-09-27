package starpunk;

import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameScreen
  implements Screen
{
  private World _world;
  private OrthographicCamera _camera;
  public GameScreen()
  {
    _camera = new OrthographicCamera( StarPunkGame.WIDTH, StarPunkGame.HEIGHT );

    _world = new World();
    _world.initialize();
  }

  @Override
  public void render( final float delta )
  {
    Gdx.gl10.glClear( GL10.GL_COLOR_BUFFER_BIT );

    _camera.update();

    _world.setDelta( delta );
    _world.process();
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
