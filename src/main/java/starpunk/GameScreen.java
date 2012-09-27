package starpunk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class GameScreen
  implements Screen
{
  @Override
  public void render( final float delta )
  {
    Gdx.gl10.glClear( GL10.GL_COLOR_BUFFER_BIT );
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
