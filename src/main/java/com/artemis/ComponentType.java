package com.artemis;

import java.util.HashMap;

public class ComponentType
{
  private static int INDEX;

  private final int index;
  private final Class<?> type;

  private ComponentType( final Class<?> type )
  {
    index = INDEX++;
    this.type = type;
  }

  public int getIndex()
  {
    return index;
  }

  @Override
  public String toString()
  {
    return "ComponentType[" + type.getSimpleName() + "] (" + index + ")";
  }

  private static final HashMap<Class<?>, ComponentType> componentTypes = new HashMap<Class<?>, ComponentType>();

  public static ComponentType getTypeFor( final Class<?> c )
  {
    ComponentType type = componentTypes.get( c );

    if( type == null )
    {
      type = new ComponentType( c );
      componentTypes.put( c, type );
    }

    return type;
  }

  public static int getIndexFor( final Class<?> c )
  {
    return getTypeFor( c ).getIndex();
  }
}
