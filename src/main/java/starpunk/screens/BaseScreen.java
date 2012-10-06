package starpunk.screens;

import com.badlogic.gdx.Screen;

public abstract class BaseScreen
  implements Screen
{
  public abstract void update( float delta );

  public abstract void draw( float delta );

  @Override
  public void render( final float delta )
  {
    update( delta );
    draw( delta );
  }

  @Override
  public void resize( final int width, final int height )
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