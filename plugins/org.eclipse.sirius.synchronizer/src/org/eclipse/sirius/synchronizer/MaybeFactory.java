/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/

package org.eclipse.sirius.synchronizer;

import com.google.common.base.Predicate;


/**
 * Factory class to create new instances of Options.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 */
public final class MaybeFactory {

	/*
	 * utility class.
	 */
	private MaybeFactory() {

	}

	/**
	 * Returns a predicate version of {@link Maybe#some()}.
	 * 
	 * @param <T>
	 *            the type of the option.
	 * @return a predicate version of {@link Maybe#some()}.
	 */
	public static <T> Predicate<Maybe<T>> isSome() {
		return new Predicate<Maybe<T>>() {
			public boolean apply(Maybe<T> input) {
				return input.some();
			}
		};
	}

	/**
	 * create a new option representing a None value.
	 * 
	 * @param <T>
	 *            the type expected by the option.
	 * @return a newly created none option.
	 */
	public static <T> Maybe<T> newNone() {
		return new Maybe<T>(null);
	}

	/**
	 * create a new option representing Some value.
	 * 
	 * @param <T>
	 *            the type expected by the option.
	 * @param value
	 *            the value wrapped by the option.
	 * @return a newly created Some option.
	 */
	public static <T> Maybe<T> newSome(T value) {
		return new Maybe<T>(value);
	}
}
