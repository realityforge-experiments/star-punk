package com.artemis;

import java.util.HashMap;

public class ComponentType
{
  private static int INDEX;

  private final int index;
  private final Class<? extends Object> type;

  private ComponentType( final Class<? extends Object> type )
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

  private static final HashMap<Class<? extends Object>, ComponentType> componentTypes = new HashMap<Class<? extends Object>, ComponentType>();

  public static ComponentType getTypeFor( final Class<? extends Object> c )
  {
    ComponentType type = componentTypes.get( c );

    if( type == null )
    {
      type = new ComponentType( c );
      componentTypes.put( c, type );
    }

    return type;
  }

  public static int getIndexFor( final Class<? extends Object> c )
  {
    return getTypeFor( c ).getIndex();
  }
}
