package com.artemis;

import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;
import java.util.HashMap;

/**
 * The most raw entity system. It should not typically be used, but you can create your own
 * entity system handling by extending this. It is recommended that you use the other provided
 * entity system implementations.
 *
 * @author Arni Arent
 */
public abstract class EntitySystem
  implements EntityObserver
{
  private final int systemIndex;

  protected World world;

  private final Bag<Entity> actives;

  private final Aspect _aspect;

  private boolean passive;

  /**
   * Creates an entity system that uses the specified aspect as a matcher against entities.
   *
   * @param aspect to match against entities
   */
  public EntitySystem( final Aspect aspect )
  {
    actives = new Bag<Entity>();
    _aspect = aspect;
    systemIndex = SystemIndexManager.getIndexFor( this.getClass() );
  }

  /** Called before processing of entities begins. */
  protected void begin()
  {
  }

  public final void process()
  {
    if( checkProcessing() )
    {
      begin();
      processEntities( actives );
      end();
    }
  }

  /** Called after the processing of entities ends. */
  protected void end()
  {
  }

  /**
   * Any implementing entity system must implement this method and the logic
   * to process the given entities of the system.
   *
   * @param entities the entities this system contains.
   */
  protected abstract void processEntities( ImmutableBag<Entity> entities );

  /** @return true if the system should be processed, false if not. */
  protected abstract boolean checkProcessing();

  /** Override to implement code that gets executed when systems are initialized. */
  protected void initialize()
  {
  }

  /**
   * Called if the system has received a entity it is interested in, e.g. created or a component was added to it.
   *
   * @param e the entity that was added to this system.
   */
  protected void inserted( final Entity e )
  {
  }

  /**
   * Called if a entity was removed from this system, e.g. deleted or had one of it's components removed.
   *
   * @param e the entity that was removed from this system.
   */
  protected void removed( final Entity e )
  {
  }

  /**
   * Will check if the entity is of interest to this system.
   *
   * @param e entity to check
   */
  protected final void check( final Entity e )
  {
    final boolean contains = e.getSystemBits().get( systemIndex );
    final boolean interested = _aspect.isInterested( e.getComponentBits() );

    if( interested && !contains )
    {
      insertToSystem( e );
    }
    else if( !interested && contains )
    {
      removeFromSystem( e );
    }
  }

  private void removeFromSystem( final Entity e )
  {
    actives.remove( e );
    e.getSystemBits().clear( systemIndex );
    removed( e );
  }

  private void insertToSystem( final Entity e )
  {
    actives.add( e );
    e.getSystemBits().set( systemIndex );
    inserted( e );
  }

  @Override
  public final void added( final Entity e )
  {
    check( e );
  }

  @Override
  public final void changed( final Entity e )
  {
    check( e );
  }

  @Override
  public final void deleted( final Entity e )
  {
    if( e.getSystemBits().get( systemIndex ) )
    {
      removeFromSystem( e );
    }
  }

  @Override
  public final void disabled( final Entity e )
  {
    if( e.getSystemBits().get( systemIndex ) )
    {
      removeFromSystem( e );
    }
  }

  @Override
  public final void enabled( final Entity e )
  {
    check( e );
  }

  protected final void setWorld( final World world )
  {
    this.world = world;
  }

  protected boolean isPassive()
  {
    return passive;
  }

  protected boolean isActive()
  {
    return !isPassive();
  }

  protected void setPassive( final boolean passive )
  {
    this.passive = passive;
  }

  public ImmutableBag<Entity> getActives()
  {
    return actives;
  }

  /**
   * Used to generate a unique bit for each system.
   * Only used internally in EntitySystem.
   */
  private static class SystemIndexManager
  {
    private static int INDEX;
    private static final HashMap<Class<? extends EntitySystem>, Integer> indices = new HashMap<Class<? extends EntitySystem>, Integer>();

    private static int getIndexFor( final Class<? extends EntitySystem> es )
    {
      Integer index = indices.get( es );
      if( index == null )
      {
        index = INDEX++;
        indices.put( es, index );
      }
      return index;
    }
  }
}
