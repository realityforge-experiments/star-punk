package com.artemis;

import java.util.HashMap;

public final class ComponentType
{
  private static final HashMap<Class<?>, ComponentType> _typeMap = new HashMap<Class<?>, ComponentType>();

  private static int c_maxIndex;

  private final int _index;
  private final Class<?> _type;

  private ComponentType( final Class<?> type )
  {
    _index = c_maxIndex++;
    _type = type;
  }

  public int getIndex()
  {
    return _index;
  }

  @Override
  public String toString()
  {
    return "ComponentType[" + _type.getSimpleName() + "] (" + _index + ")";
  }

  public static ComponentType getTypeFor( final Class<?> c )
  {
    ComponentType type = _typeMap.get( c );
    if( type == null )
    {
      type = new ComponentType( c );
      _typeMap.put( c, type );
    }
    return type;
  }

  public static int getIndexFor( final Class<?> c )
  {
    return getTypeFor( c ).getIndex();
  }
}
