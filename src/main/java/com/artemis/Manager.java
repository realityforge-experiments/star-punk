package com.artemis;

/**
 * Manager.
 *
 * @author Arni Arent
 */
public abstract class Manager implements EntityObserver
{
  protected World world;

  protected abstract void initialize();

  protected void setWorld( final World world )
  {
    this.world = world;
  }

  protected World getWorld()
  {
    return world;
  }

  @Override
  public void added( final Entity e )
  {
  }

  @Override
  public void changed( final Entity e )
  {
  }

  @Override
  public void deleted( final Entity e )
  {
  }

  @Override
  public void disabled( final Entity e )
  {
  }

  @Override
  public void enabled( final Entity e )
  {
  }
}
