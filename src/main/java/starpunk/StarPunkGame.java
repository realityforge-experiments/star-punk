package starpunk;

import com.badlogic.gdx.Game;
import starpunk.screens.GameLoopScreen;

public final class StarPunkGame
  extends Game
{
  public static final int WIDTH = 512;
  public static final int HEIGHT = 512;

  StarPunkGame()
  {
  }

  @Override
  public void create()
  {
    setScreen( new GameLoopScreen( this ) );
  }
}
