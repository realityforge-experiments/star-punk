package starpunk;

import com.badlogic.gdx.Game;
import starpunk.screens.GameScreen;

public final class StarPunkGame
  extends Game
{
  public static final int WIDTH = 1280;
  public static final int HEIGHT = 900;

  StarPunkGame()
  {
  }

  @Override
  public void create()
  {
    setScreen( new GameScreen() );
  }
}
