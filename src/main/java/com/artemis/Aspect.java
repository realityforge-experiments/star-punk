package com.artemis;

import java.util.BitSet;

/**
 * An Aspects is used by systems as a matcher against entities, to check if a system is
 * interested in an entity. Aspects define what sort of component types an entity must
 * possess, or not possess.
 * <p/>
 * This creates an aspect where an entity must possess A and B and C:
 * Aspect.getAspectForAll(A.class, B.class, C.class)
 * <p/>
 * This creates an aspect where an entity must possess A and B and C, but must not possess U or V.
 * Aspect.getAspectForAll(A.class, B.class, C.class).exclude(U.class, V.class)
 * <p/>
 * This creates an aspect where an entity must possess A and B and C, but must not possess U or V, but must possess one of X or Y or Z.
 * Aspect.getAspectForAll(A.class, B.class, C.class).exclude(U.class, V.class).one(X.class, Y.class, Z.class)
 * <p/>
 * You can create and compose aspects in many ways:
 * Aspect.getEmpty().one(X.class, Y.class, Z.class).all(A.class, B.class, C.class).exclude(U.class, V.class)
 * is the same as:
 * Aspect.getAspectForAll(A.class, B.class, C.class).exclude(U.class, V.class).one(X.class, Y.class, Z.class)
 *
 * @author Arni Arent
 */
@SuppressWarnings( "unchecked" )
public final class Aspect
{
  private final BitSet _allSet = new BitSet();
  private final BitSet _exclusionSet = new BitSet();
  private final BitSet _oneSet = new BitSet();

  public boolean isInterested( final BitSet componentBits )
  {
    // possibly interested, let's try to prove it wrong.
    boolean interested = true;
    // Check if the entity possesses ALL of the components defined in the aspect.
    if( !_allSet.isEmpty() )
    {
      for( int i = _allSet.nextSetBit( 0 ); i >= 0; i = _allSet.nextSetBit( i + 1 ) )
      {
        if( !componentBits.get( i ) )
        {
          interested = false;
          break;
        }
      }
    }

    // Check if the entity possesses ANY of the exclusion components, if it does then the system is not interested.
    if( !_exclusionSet.isEmpty() && interested )
    {
      interested = !_exclusionSet.intersects( componentBits );
    }

    // Check if the entity possesses ANY of the components in the oneSet. If so, the system is interested.
    if( !_oneSet.isEmpty() )
    {
      interested = _oneSet.intersects( componentBits );
    }
    return interested;
  }

  /**
   * Returns an aspect where an entity must possess all of the specified component types.
   *
   * @param types a required component type
   * @return an aspect that can be matched against entities
   */
  public final Aspect all( final Class... types )
  {
    for( final Class t : types )
    {
      _allSet.set( ComponentType.getIndexFor( t ) );
    }

    return this;
  }

  /**
   * Excludes all of the specified component types from the aspect. A system will not be
   * interested in an entity that possesses one of the specified exclusion component types.
   *
   * @param types component type to exclude
   * @return an aspect that can be matched against entities
   */
  public final Aspect exclude( final Class... types )
  {
    for( final Class t : types )
    {
      _exclusionSet.set( ComponentType.getIndexFor( t ) );
    }
    return this;
  }

  /**
   * Returns an aspect where an entity must possess one of the specified component types.
   *
   * @param types one of the types the entity must possess
   * @return an aspect that can be matched against entities
   */
  public final Aspect one( final Class... types )
  {
    for( final Class t : types )
    {
      _oneSet.set( ComponentType.getIndexFor( t ) );
    }
    return this;
  }

  /**
   * Creates an aspect where an entity must possess all of the specified component types.
   *
   * @param types the required component types.
   * @return an aspect that can be matched against entities
   */
  public static Aspect getAspectForAll( final Class... types )
  {
    return new Aspect().all( types );
  }

  /**
   * Creates an aspect where an entity must possess one of the specified component types.
   *
   * @param types one of the types the entity must possess
   * @return an aspect that can be matched against entities
   */
  public static Aspect getAspectForOne( final Class... types )
  {
    return new Aspect().one( types );
  }

  /**
   * Creates and returns an empty aspect. This can be used if you want a system that processes no entities, but
   * still gets invoked. Typical usages is when you need to create special purpose systems for debug rendering,
   * like rendering FPS, how many entities are active in the world, etc.
   * <p/>
   * You can also use the all, one and exclude methods on this aspect, so if you wanted to create a system that
   * processes only entities possessing just one of the components A or B or C, then you can do:
   * Aspect.getEmpty().one(A,B,C);
   *
   * @return an empty Aspect that will reject all entities.
   */
  public static Aspect getEmpty()
  {
    return new Aspect();
  }
}
