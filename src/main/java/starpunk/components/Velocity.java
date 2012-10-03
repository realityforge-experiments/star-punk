package starpunk.components;

import com.artemis.Component;

public class Velocity extends Component
{
  private float _vectorX;
  private float _vectorY;

  public float getVectorX()
  {
    return _vectorX;
  }

  public void setVectorX( final float vectorX )
  {
    _vectorX = vectorX;
  }

  public float getVectorY()
  {
    return _vectorY;
  }

  public void setVectorY( final float vectorY )
  {
    _vectorY = vectorY;
  }
}
