package starpunk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import starpunk.screens.GameLoopScreen;

public final class StarPunkGame
  extends Game
{
  public static final int WIDTH = 512;
  public static final int HEIGHT = 512;

  private final AssetManager _assetManager = new AssetManager();
  private static StarPunkGame c_game;

  public static StarPunkGame getGame()
  {
    return c_game;
  }

  StarPunkGame()
  {
    c_game = this;
  }

  @Override
  public void create()
  {
    Gdx.app.log( StarPunkGame.class.getSimpleName(), "Creating game on " + Gdx.app.getType() );
    _assetManager.initialize();
    setScreen( new GameLoopScreen( this ) );
  }

  @Override
  public void dispose()
  {
    Gdx.app.log( StarPunkGame.class.getSimpleName(), "Disposing game." );
    super.dispose();
    _assetManager.dispose();
  }

  public AssetManager getAssetManager()
  {
    return _assetManager;
  }

  @Override
  public void resize( final int width, final int height )
  {
    Gdx.app.log( StarPunkGame.class.getSimpleName(), "Resizing game to: " + width + " x " + height );
    super.resize( width, height );
  }

  @Override
  public void pause()
  {
    Gdx.app.log( StarPunkGame.class.getSimpleName(), "Pausing game" );
    super.pause();
  }

  @Override
  public void resume()
  {
    Gdx.app.log( StarPunkGame.class.getSimpleName(), "Resuming game" );
    super.resume();
  }

  @Override
  public void setScreen( final Screen screen )
  {
    Gdx.app.log( StarPunkGame.class.getSimpleName(), "Setting screen: " + screen.getClass().getSimpleName() );
    super.setScreen( screen );
  }
}
