package com.artemis;

import java.util.BitSet;

/**
 * An Aspects is used by systems as a matcher against entities, to check if a system is
 * interested in an entity. Aspects define what sort of component types an entity must
 * possess, or not possess.
 * 
 * This creates an aspect where an entity must possess A and B and C:
 * Aspect.getAspectForAll(A.class, B.class, C.class)
 * 
 * This creates an aspect where an entity must possess A and B and C, but must not possess U or V.
 * Aspect.getAspectForAll(A.class, B.class, C.class).exclude(U.class, V.class)
 * 
 * This creates an aspect where an entity must possess A and B and C, but must not possess U or V, but must possess one of X or Y or Z.
 * Aspect.getAspectForAll(A.class, B.class, C.class).exclude(U.class, V.class).one(X.class, Y.class, Z.class)
 *
 * You can create and compose aspects in many ways:
 * Aspect.getEmpty().one(X.class, Y.class, Z.class).all(A.class, B.class, C.class).exclude(U.class, V.class)
 * is the same as:
 * Aspect.getAspectForAll(A.class, B.class, C.class).exclude(U.class, V.class).one(X.class, Y.class, Z.class)
 *
 * @author Arni Arent
 *
 */
@SuppressWarnings( "unchecked" )
public class Aspect {
	
	private BitSet allSet;
	private BitSet exclusionSet;
	private BitSet oneSet;
	
	private Aspect() {
		this.allSet = new BitSet();
		this.exclusionSet = new BitSet();
		this.oneSet = new BitSet();
	}
	
	protected BitSet getAllSet() {
		return allSet;
	}
	
	protected BitSet getExclusionSet() {
		return exclusionSet;
	}
	
	protected BitSet getOneSet() {
		return oneSet;
	}
	
	/**
	 * Returns an aspect where an entity must possess all of the specified component types.
   *
	 * @param types a required component type
	 * @return an aspect that can be matched against entities
	 */
	public final Aspect all(final Class... types) {
		for (Class t : types) {
			allSet.set(ComponentType.getIndexFor(t));
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
	public final Aspect exclude(Class... types) {
		for (Class t : types) {
			exclusionSet.set(ComponentType.getIndexFor(t));
		}
		return this;
	}
	
	/**
	 * Returns an aspect where an entity must possess one of the specified component types.
	 * @param types one of the types the entity must possess
	 * @return an aspect that can be matched against entities
	 */
	public final Aspect one(Class... types) {
		for (Class t : types) {
			oneSet.set(ComponentType.getIndexFor(t));
		}
		return this;
	}
	
	/**
	 * Creates an aspect where an entity must possess all of the specified component types.
	 * 
	 * @param types the required component types.
	 * @return an aspect that can be matched against entities
	 */
	public static Aspect getAspectForAll(Class... types) {
		Aspect aspect = new Aspect();
		aspect.all(types);
		return aspect;
	}
	
	/**
	 * Creates an aspect where an entity must possess one of the specified component types.
	 * 
	 * @param types one of the types the entity must possess
	 * @return an aspect that can be matched against entities
	 */
	public static Aspect getAspectForOne(Class... types) {
		Aspect aspect = new Aspect();
		aspect.one(types);
		return aspect;
	}
	
	/**
	 * Creates and returns an empty aspect. This can be used if you want a system that processes no entities, but
	 * still gets invoked. Typical usages is when you need to create special purpose systems for debug rendering,
	 * like rendering FPS, how many entities are active in the world, etc.
	 * 
	 * You can also use the all, one and exclude methods on this aspect, so if you wanted to create a system that
	 * processes only entities possessing just one of the components A or B or C, then you can do:
	 * Aspect.getEmpty().one(A,B,C);
	 * 
	 * @return an empty Aspect that will reject all entities.
	 */
	public static Aspect getEmpty() {
		return new Aspect();
	}

}
