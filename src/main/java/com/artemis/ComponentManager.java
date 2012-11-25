package com.artemis;

import com.artemis.utils.Bag;
import java.util.BitSet;

@SuppressWarnings( "unchecked" )
public class ComponentManager
  extends Manager
{
  private final Bag<Bag> _componentsByType;
  private final Bag<Entity> _deleted;

  public ComponentManager()
  {
    _componentsByType = new Bag<Bag>();
    _deleted = new Bag<Entity>();
  }

  private void removeComponentsOfEntity( final Entity e )
  {
    final BitSet componentBits = e.getComponentBits();
    for( int i = componentBits.nextSetBit( 0 ); i >= 0; i = componentBits.nextSetBit( i + 1 ) )
    {
      _componentsByType.get( i ).set( e.getId(), null );
    }
    componentBits.clear();
  }

  protected <T> void addComponent( final Entity e, final ComponentType<T> type, final T component )
  {
    _componentsByType.ensureCapacity( type.getIndex() );

    Bag<Object> components = _componentsByType.get( type.getIndex() );
    if( components == null )
    {
      components = new Bag<Object>();
      _componentsByType.set( type.getIndex(), components );
    }

    components.set( e.getId(), component );

    e.getComponentBits().set( type.getIndex() );
  }

  protected <T> void removeComponent( final Entity e, final ComponentType<T> type )
  {
    if( e.getComponentBits().get( type.getIndex() ) )
    {
      _componentsByType.get( type.getIndex() ).set( e.getId(), null );
      e.getComponentBits().clear( type.getIndex() );
    }
  }

  protected <T> Bag<T> getComponentsByType( final ComponentType<T> type )
  {
    Bag<T> components = (Bag<T>) _componentsByType.get( type.getIndex() );
    if( null == components )
    {
      components = new Bag<T>();
      _componentsByType.set( type.getIndex(), components );
    }
    return components;
  }

  protected <T> T getComponent( final Entity e, final ComponentType<T> type )
  {
    final Bag<T> components = _componentsByType.get( type.getIndex() );
    if( null != components )
    {
      return components.get( e.getId() );
    }
    return null;
  }

  public Bag<Object> getComponentsFor( final Entity e, final Bag<Object> fillBag )
  {
    final BitSet componentBits = e.getComponentBits();

    for( int i = componentBits.nextSetBit( 0 ); i >= 0; i = componentBits.nextSetBit( i + 1 ) )
    {
      fillBag.add( _componentsByType.get( i ).get( e.getId() ) );
    }

    return fillBag;
  }

  @Override
  public void deleted( final Entity e )
  {
    _deleted.add( e );
  }

  protected void clean()
  {
    if( _deleted.size() > 0 )
    {
      for( int i = 0; _deleted.size() > i; i++ )
      {
        removeComponentsOfEntity( _deleted.get( i ) );
      }
      _deleted.clear();
    }
  }
}
