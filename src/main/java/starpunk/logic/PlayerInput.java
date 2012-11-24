package starpunk.logic;

public final class PlayerInput
{
  private static final PlayerInput c_input = new PlayerInput();

  private boolean _left;
  private boolean _right;
  private boolean _up;
  private boolean _down;

  public static PlayerInput getPlayerInput()
  {
    return c_input;
  }

  public boolean isLeft()
  {
    return _left;
  }

  public void setLeft( final boolean left )
  {
    _left = left;
  }

  public boolean isRight()
  {
    return _right;
  }

  public void setRight( final boolean right )
  {
    _right = right;
  }

  public boolean isUp()
  {
    return _up;
  }

  public void setUp( final boolean up )
  {
    _up = up;
  }

  public boolean isDown()
  {
    return _down;
  }

  public void setDown( final boolean down )
  {
    _down = down;
  }
}
