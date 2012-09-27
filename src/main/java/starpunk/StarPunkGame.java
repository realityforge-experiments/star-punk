package starpunk;

import com.badlogic.gdx.Game;

final class StarPunkGame
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
    setScreen( new GameScreen( this ) );
  }
}
