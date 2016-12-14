package com.simoncat.framework.core.tuple;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * A couple consisting of two elements.
 * </p>
 * <p>
 * Compare with Pair class, this class is will ignore the order to these two elements when compare with each other.
 * </p>
 * <p>
 * <strong>Please NOTE: make sure this type support {@link Object#equals}, so that this class can do comparasion correctly.</strong>
 * </p>
 * 
 * @author Simon LUO (printscroll@163.com)
 *
 * @param <T>
 *            the element type.
 */
@Slf4j
public final class Couple<T> implements Serializable {

	/** Serialization version */
	private static final long serialVersionUID = 2427123348613404720L;

	private final T one;
	private final T another;

	/**
	 * Create a new pair instance of two nulls.
	 */
	private Couple(final T one, final T another) {
		this.one = one;
		this.another = another;
	}

	/**
	 * <p>
	 * Obtains a switchable pair of from two objects inferring the generic types.
	 * </p>
	 * 
	 * <p>
	 * This factory allows the pair to be created using inference to obtain the generic types.
	 * </p>
	 * 
	 * @param <T>
	 *            the element type
	 * @param one
	 *            one element, can not be null
	 * @param another
	 *            another element, can not be null
	 * @return a pair formed from the two parameters
	 */
	public static <T> Optional<Couple<T>> of(final T one, final T another) {
		if (Objects.isNull(one) || Objects.isNull(another)) {
			log.error("Expect Non-null arguments, but get null object from {} and {}", one, another);
			return Optional.empty();
		}
		return Optional.of(new Couple<T>(one, another));
	}

	public static <T> Optional<Couple<T>> of(final T[] twoElements) {
		if (Objects.isNull(twoElements)) {
			log.error("Expect Non-null arguments, but get null array: {}", twoElements);
			return Optional.empty();
		}
		if (twoElements.length != 2) {
			log.error("Expect 2 arguments, but get {} of array {}", twoElements.length, twoElements);
			return Optional.empty();
		}
		return Optional.of(new Couple<T>(twoElements[0], twoElements[1]));
	}

	@SuppressWarnings("unchecked")
	public static <T> Optional<Couple<T>> of(final Set<T> twoElements) {
		if (Objects.isNull(twoElements)) {
			log.error("Expect Non-null arguments, but get null Set: {}", twoElements);
			return Optional.empty();
		}
		return Couple.of((T[]) twoElements.toArray());

	}

	@SuppressWarnings("unchecked")
	protected T[] getTwoElementArray() {
		return (T[]) new Object[] { this.one, this.another };
	}

	public List<T> getTwoElementList() {
		return Arrays.asList(this.one, this.another);
	}

	public T getOne() {
		return this.one;
	}

	public T getAnother() {
		return this.another;
	}

	public boolean contains(T object) {
		return (this.one.equals(object)) || (this.another.equals(object));
	}

	/**
	 * <p>
	 * Compares this pair to another based on the two elements.
	 * </p>
	 * 
	 * @param obj
	 *            the object to compare to, null returns false
	 * @return true if the elements of the pair are equal
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof Couple) {
			final Couple<T> other = (Couple<T>) obj;
			return Arrays.equals(this.getTwoElementArray(), other.getTwoElementArray());
		}
		return false;
	}
}
