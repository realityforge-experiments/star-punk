package starpunk.components;

import com.artemis.Component;

public final class Sprite
  extends Component
{
  private String _name;
  private float _scaleX = 1;
  private float _scaleY = 1;
  private float _rotation;
  private float _r = 1;
  private float _g = 1;
  private float _b = 1;
  private float _a = 1;
  private Layer _layer = Layer.DEFAULT;

  public String getName()
  {
    return _name;
  }

  public void setName( final String name )
  {
    _name = name;
  }

  public float getScaleX()
  {
    return _scaleX;
  }

  public void setScaleX( final float scaleX )
  {
    _scaleX = scaleX;
  }

  public float getScaleY()
  {
    return _scaleY;
  }

  public void setScaleY( final float scaleY )
  {
    _scaleY = scaleY;
  }

  public float getRotation()
  {
    return _rotation;
  }

  public void setRotation( final float rotation )
  {
    _rotation = rotation;
  }

  public float getR()
  {
    return _r;
  }

  public void setR( final float r )
  {
    _r = r;
  }

  public float getG()
  {
    return _g;
  }

  public void setG( final float g )
  {
    _g = g;
  }

  public float getB()
  {
    return _b;
  }

  public void setB( final float b )
  {
    _b = b;
  }

  public float getA()
  {
    return _a;
  }

  public void setA( final float a )
  {
    _a = a;
  }

  public Layer getLayer()
  {
    return _layer;
  }

  public void setLayer( final Layer layer )
  {
    _layer = layer;
  }

  public enum Layer
  {
    DEFAULT,
    BACKGROUND
  }
}
