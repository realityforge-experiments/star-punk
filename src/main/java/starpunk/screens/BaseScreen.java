package starpunk.screens;

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