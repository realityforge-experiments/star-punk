package com.artemis;

import com.artemis.utils.Bag;

/**
 * High performance component retrieval from entities. Use this wherever you
 * need to retrieve components from entities often and fast.
 *
 * @param <A> the class type of the component
 * @author Arni Arent
 */
public class ComponentMapper<A extends Object>
{
  private final Class<A> _type;
  private final Bag<Object> _components;

  private ComponentMapper( final Class<A> type, final World world )
  {
    _components = world.getComponentManager().getComponentsByType( ComponentType.getTypeFor( type ) );
    _type = type;
  }

  /**
   * Fast but unsafe retrieval of a component for this entity.
   * No bounding checks, so this could throw an ArrayIndexOutOfBoundsException,
   * however in most scenarios you already know the entity possesses this component.
   *
   * @param e the entity that should possess the component
   * @return the instance of the component
   */
  public A get( final Entity e )
  {
    return _type.cast( _components.get( e.getId() ) );
  }

  /**
   * Fast and safe retrieval of a component for this entity.
   * If the entity does not have this component then null is returned.
   *
   * @param e the entity that should possess the component
   * @return the instance of the component
   */
  public A getSafe( final Entity e )
  {
    if( _components.isIndexWithinBounds( e.getId() ) )
    {
      return _type.cast( _components.get( e.getId() ) );
    }
    return null;
  }

  /**
   * Checks if the entity has this type of component.
   *
   * @param e the entity to check
   * @return true if the entity has this component type, false if it doesn't.
   */
  public boolean has( final Entity e )
  {
    return getSafe( e ) != null;
  }

  /**
   * Returns a component mapper for this type of components.
   *
   * @param type the type of components this mapper uses.
   * @param world the world that this component mapper should use.
   * @return a new mapper.
   */
  public static <T extends Object> ComponentMapper<T> getFor( final Class<T> type, final World world )
  {
    return new ComponentMapper<T>( type, world );
  }
}
