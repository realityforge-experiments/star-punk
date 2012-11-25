package starpunk;

import com.artemis.ComponentType;
import com.badlogic.gdx.math.Vector2;

public class Constants
{
  public static final String POSITION_CLASSIFIER = "POSITION";
  public static final ComponentType<Vector2> POSITION = ComponentType.getTypeFor( Vector2.class, POSITION_CLASSIFIER );

  public static final String VELOCITY_CLASSIFIER = "VELOCITY";
  public static final ComponentType<Vector2> VELOCITY = ComponentType.getTypeFor( Vector2.class, VELOCITY_CLASSIFIER );

  public static final String ACCELERATION_CLASSIFIER = "ACCELERATION";
  public static final ComponentType<Vector2> ACCELERATION =
    ComponentType.getTypeFor( Vector2.class, ACCELERATION_CLASSIFIER );

  private Constants()
  {
  }
}
