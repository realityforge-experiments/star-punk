package starpunk;

import com.badlogic.gdx.Game;
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
    _assetManager.initialize();
    setScreen( new GameLoopScreen( this ) );
  }

  @Override
  public void dispose()
  {
    super.dispose();
    _assetManager.dispose();
  }

  public AssetManager getAssetManager()
  {
    return _assetManager;
  }
}
