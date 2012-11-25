package com.artemis;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

public final class ComponentType
{
  private static final String DEFAULT_CLASSIFIER = "DEFAULT";
  private static final HashMap<Class<?>, Map<String, ComponentType>> _typeMap =
    new HashMap<Class<?>, Map<String, ComponentType>>();

  private static int c_maxIndex;

  private final int _index;
  private final Class<?> _type;
  private final String _classifier;

  private ComponentType( final Class<?> type, final String classifier )
  {
    _index = c_maxIndex++;
    _type = type;
    _classifier = classifier;
  }

  public int getIndex()
  {
    return _index;
  }

  @Override
  public String toString()
  {
    return "ComponentType[" + _type.getSimpleName() + "/" + _classifier + "] (" + _index + ")";
  }

  public static ComponentType getTypeFor( final Class<?> type )
  {
    return getTypeFor( type, DEFAULT_CLASSIFIER );
  }

  @Nonnull
  public static ComponentType getTypeFor( @Nonnull final Class<?> type, @Nonnull final String classifier )
  {
    Map<String, ComponentType> componentTypeMap = _typeMap.get( type );
    if( null == componentTypeMap )
    {
      componentTypeMap = new HashMap<String, ComponentType>();
      _typeMap.put( type, componentTypeMap );
    }

    ComponentType componentType = componentTypeMap.get( classifier );
    if( null == componentType )
    {
      componentType = new ComponentType( type, classifier );
      componentTypeMap.put( classifier, componentType );
    }
    return componentType;
  }

  public static int getIndexFor( final Class<?> c )
  {
    return getTypeFor( c ).getIndex();
  }
}
