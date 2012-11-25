package starpunk;

import com.artemis.ComponentType;
import com.badlogic.gdx.math.Vector2;

public class Constants
{
  public static final String POSITION_CLASSIFIER = "POSITION_CLASSIFIER";
  public static final ComponentType<Vector2> POSITION = ComponentType.getTypeFor( Vector2.class, POSITION_CLASSIFIER );

  private Constants()
  {
  }
}
