package starpunk.components;

import com.artemis.Component;

public class Position
  extends Component
{
  private float _x;
  private float _y;

  public float getX()
  {
    return _x;
  }

  public void setX( final float x )
  {
    _x = x;
  }

  public float getY()
  {
    return _y;
  }

  public void setY( final float y )
  {
    _y = y;
  }
}
