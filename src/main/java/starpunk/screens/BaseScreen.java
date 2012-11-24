package starpunk.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import starpunk.StarPunkGame;

public abstract class BaseScreen
  implements Screen
{
  private final StarPunkGame _game;

  protected BaseScreen( final StarPunkGame game )
  {
    _game = game;
  }

  protected final StarPunkGame getGame()
  {
    return _game;
  }

  public abstract boolean update( float delta );

  public abstract void draw( float delta );

  @Override
  public final void render( final float delta )
  {
    if( update( delta ) )
    {
      draw( delta );
    }
  }

  @Override
  public void resize( final int width, final int height )
  {
    log( "Resizing screen to: " + width + " x " + height );
  }

  @Override
  public void show()
  {
    log( "Showing screen" );
  }

  @Override
  public void hide()
  {
    log( "Hiding screen" );
  }

  @Override
  public void pause()
  {
    log( "Pausing screen" );
  }

  @Override
  public void resume()
  {
    log( "Resuming screen" );
  }

  @Override
  public void dispose()
  {
    log( "Disposing screen" );
  }

  private void log( final String message )
  {
    Gdx.app.log( getClass().getSimpleName(), message );
  }
}