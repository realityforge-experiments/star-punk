package com.artemis;

import com.artemis.utils.Bag;
import java.util.BitSet;

public class ComponentManager extends Manager
{
  private final Bag<Bag<Object>> componentsByType;
  private final Bag<Entity> deleted;

  public ComponentManager()
  {
    componentsByType = new Bag<Bag<Object>>();
    deleted = new Bag<Entity>();
  }

  private void removeComponentsOfEntity( final Entity e )
  {
    final BitSet componentBits = e.getComponentBits();
    for( int i = componentBits.nextSetBit( 0 ); i >= 0; i = componentBits.nextSetBit( i + 1 ) )
    {
      componentsByType.get( i ).set( e.getId(), null );
    }
    componentBits.clear();
  }

  protected void addComponent( final Entity e, final ComponentType type, final Object component )
  {
    componentsByType.ensureCapacity( type.getIndex() );

    Bag<Object> components = componentsByType.get( type.getIndex() );
    if( components == null )
    {
      components = new Bag<Object>();
      componentsByType.set( type.getIndex(), components );
    }

    components.set( e.getId(), component );

    e.getComponentBits().set( type.getIndex() );
  }

  protected void removeComponent( final Entity e, final ComponentType type )
  {
    if( e.getComponentBits().get( type.getIndex() ) )
    {
      componentsByType.get( type.getIndex() ).set( e.getId(), null );
      e.getComponentBits().clear( type.getIndex() );
    }
  }

  protected Bag<Object> getComponentsByType( final ComponentType type )
  {
    Bag<Object> components = componentsByType.get( type.getIndex() );
    if( components == null )
    {
      components = new Bag<Object>();
      componentsByType.set( type.getIndex(), components );
    }
    return components;
  }

  protected Object getComponent( final Entity e, final ComponentType type )
  {
    final Bag<Object> components = componentsByType.get( type.getIndex() );
    if( components != null )
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
      fillBag.add( componentsByType.get( i ).get( e.getId() ) );
    }

    return fillBag;
  }

  @Override
  public void deleted( final Entity e )
  {
    deleted.add( e );
  }

  protected void clean()
  {
    if( deleted.size() > 0 )
    {
      for( int i = 0; deleted.size() > i; i++ )
      {
        removeComponentsOfEntity( deleted.get( i ) );
      }
      deleted.clear();
    }
  }
}
