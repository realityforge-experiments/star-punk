package com.artemis;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

public final class ComponentType<T>
{
  private static final String DEFAULT_CLASSIFIER = "DEFAULT";
  private static final HashMap<Class<?>, Map<String, ComponentType>> _typeMap =
    new HashMap<Class<?>, Map<String, ComponentType>>();

  private static int c_maxIndex;

  private final int _index;
  private final Class<T> _type;
  private final String _classifier;

  private ComponentType( final Class<T> type, final String classifier )
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

  public static <T> ComponentType<T> getTypeFor( final Class<T> type )
  {
    return getTypeFor( type, DEFAULT_CLASSIFIER );
  }

  @Nonnull
  public static <T> ComponentType<T> getTypeFor( @Nonnull final Class<T> type, @Nonnull final String classifier )
  {
    Map<String, ComponentType> componentTypeMap = _typeMap.get( type );
    if( null == componentTypeMap )
    {
      componentTypeMap = new HashMap<String, ComponentType>();
      _typeMap.put( type, componentTypeMap );
    }

    ComponentType<T> componentType = (ComponentType<T>) componentTypeMap.get( classifier );
    if( null == componentType )
    {
      componentType = new ComponentType<T>( type, classifier );
      componentTypeMap.put( classifier, componentType );
    }
    return componentType;
  }

  public static int getIndexFor( final Class<?> c )
  {
    return getTypeFor( c ).getIndex();
  }
}
