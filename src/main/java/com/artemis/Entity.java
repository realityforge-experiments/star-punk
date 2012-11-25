package com.artemis;

import com.artemis.utils.Bag;
import java.util.BitSet;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * The entity class. Cannot be instantiated outside the framework, you must
 * create new entities using World.
 *
 * @author Arni Arent
 */
public final class Entity
{
  private final int _id;
  private final World _world;
  private final BitSet _componentBits = new BitSet();
  private final BitSet _systemBits = new BitSet();
  private UUID _uuid;

  Entity( final World world, final int id )
  {
    _world = world;
    _id = id;
    reset();
  }

  /**
   * The internal id for this entity within the framework. No other entity
   * will have the same ID, but ID's are however reused so another entity may
   * acquire this ID if the previous entity was deleted.
   *
   * @return id of the entity.
   */
  public int getId()
  {
    return _id;
  }

  /** Returns a BitSet instance containing bits of the components the entity possesses. */
  BitSet getComponentBits()
  {
    return _componentBits;
  }

  /** Returns a BitSet instance containing bits of the components the entity possesses. */
  BitSet getSystemBits()
  {
    return _systemBits;
  }

  /**
   * Make entity ready for re-use.
   * Will generate a new uuid for the entity.
   */
  void reset()
  {
    _systemBits.clear();
    _componentBits.clear();
    _uuid = UUID.randomUUID();
  }

  @Override
  public String toString()
  {
    return "Entity[" + _id + "]";
  }

  /**
   * Add a component to this entity.
   *
   * @param component to add to this entity
   */
  public <T> void addComponent( @Nonnull final T component )
  {
    addComponent( ComponentType.getTypeFor( (Class<T>) component.getClass() ), component );
  }

  /**
   * Faster adding of components into the entity. Not necessary to use this, but
   * in some cases you might need the extra performance.
   *
   * @param type of the component
   * @param component the component to add
   */
  public <T> void addComponent( @Nonnull final ComponentType<T> type, @Nonnull final T component )
  {
    getWorld().getComponentManager().addComponent( this, type, component );
  }

  /**
   * Removes the component from this entity.
   *
   * @param component to remove from this entity.
   */
  public void removeComponent( @Nonnull final Object component )
  {
    removeComponent( ComponentType.getTypeFor( component.getClass() ) );
  }

  /**
   * Faster removal of components from a entity.
   *
   * @param type to remove from this entity.
   */
  public <T> void removeComponent( @Nonnull final ComponentType<T> type )
  {
    getWorld().getComponentManager().removeComponent( this, type );
  }

  /**
   * Checks if the entity has been added to the world and has not been deleted from it.
   * If the entity has been disabled this will still return true.
   *
   * @return if it's active.
   */
  public boolean isActive()
  {
    return getWorld().getEntityManager().isActive( _id );
  }

  /**
   * Will check if the entity is enabled in the world.
   * By default all entities that are added to world are enabled,
   * this will only return false if an entity has been explicitly disabled.
   *
   * @return if it's enabled
   */
  public boolean isEnabled()
  {
    return getWorld().getEntityManager().isEnabled( _id );
  }

  /**
   * This is the preferred method to use when retrieving a component from a
   * entity. It will provide good performance.
   * But the recommended way to retrieve components from an entity is using
   * the ComponentMapper.
   *
   * @param type in order to retrieve the component fast you must provide a
   * ComponentType instance for the expected component.
   */
  @Nullable
  public <T> T getComponent( @Nonnull final ComponentType<T> type )
  {
    return getWorld().getComponentManager().getComponent( this, type );
  }

  /**
   * Slower retrieval of components from this entity. Minimize usage of this,
   * but is fine to use e.g. when creating new entities and setting data in
   * components.
   *
   * @param <T> the expected return component type.
   * @param type the expected return component type.
   * @return component that matches, or null if none is found.
   */
  @Nullable
  public <T> T getComponent( @Nonnull final Class<T> type )
  {
    return type.cast( getComponent( ComponentType.getTypeFor( type ) ) );
  }

  /**
   * Returns a bag of all components this entity has.
   * You need to reset the bag yourself if you intend to fill it more than once.
   *
   * @param fillBag the bag to put the components into.
   * @return the fillBag with the components in.
   */
  @Nonnull
  public Bag<Object> getComponents( @Nonnull final Bag<Object> fillBag )
  {
    return getWorld().getComponentManager().getComponentsFor( this, fillBag );
  }

  /**
   * Refresh all changes to components for this entity. After adding or
   * removing components, you must call this method. It will update all
   * relevant systems. It is typical to call this after adding components to a
   * newly created entity.
   */
  public void addToWorld()
  {
    getWorld().addEntity( this );
  }

  /** This entity has changed, a component added or deleted. */
  public void changedInWorld()
  {
    getWorld().changedEntity( this );
  }

  /** Delete this entity from the world. */
  public void deleteFromWorld()
  {
    getWorld().deleteEntity( this );
  }

  /**
   * (Re)enable the entity in the world, after it having being disabled.
   * Won't do anything unless it was already disabled.
   */
  public void enable()
  {
    getWorld().enable( this );
  }

  /**
   * Disable the entity from being processed. Won't delete it, it will
   * continue to exist but won't get processed.
   */
  public void disable()
  {
    getWorld().disable( this );
  }

  /**
   * Get the UUID for this entity.
   * This UUID is unique per entity (re-used entities get a new UUID).
   *
   * @return uuid instance for this entity.
   */
  @Nonnull
  public UUID getUuid()
  {
    return _uuid;
  }

  /**
   * Returns the world this entity belongs to.
   *
   * @return world of entity.
   */
  @Nonnull
  public World getWorld()
  {
    return _world;
  }
}
