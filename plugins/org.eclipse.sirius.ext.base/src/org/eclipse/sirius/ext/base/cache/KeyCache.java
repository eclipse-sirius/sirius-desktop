/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.base.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple registry which assigns a unique integer identifier to each set of
 * elements which are {@link #equals(Object) equal} to each other (i.e. to each
 * equivalence class defined by {@link Object#equals(Object)}).
 * <p>
 * This class guarantees that if {@link #getKey(Object)} is called twice for
 * objects o1 and o2 (with no calls to {@link #clear()} in between), it will
 * return the same integer value if and only if <code>o1.equals(o2)</code> is
 * true.
 * <p>
 * Note that to perform its task, this class keeps references to all the objects
 * "registered" with it by calling {@link #getKey(Object)}. It is the user's
 * responsibility to call {@link #clear()} when necessary to avoid using too
 * much memory.
 * 
 * @author pcdavid
 */
public class KeyCache {
    /**
     * The default shared instance.
     */
	public static final KeyCache DEFAULT = new KeyCache();
	
	/**
	 * All the objects seen since creation or the last call to clear(). Keys are
	 * the first object seen in an equivalence class defined by
	 * {@link #equals(Object)}, values are the unique identifier for this class.
	 */
	private final Map<Object, Integer> registered = new HashMap<>();

	/**
	 * Returns the unique integer key associated to this object and all the
	 * objects it is {@link #equals(Object) equal} too.
	 * 
	 * @param o
	 *            any object.
	 * @return the unique integer key associated to this object and all the
	 *         objects it is {@link #equals(Object) equal} too.
	 */
	public int getKey(Object o) {
		if (registered.containsKey(o)) {
			return registered.get(o);
		} else {
			// Use the insertion order as the unique key.
			int value = registered.size();
			registered.put(o, value);
			return value;
		}
	}

	/**
	 * Forget about all the previously registered elements. After a call to
	 * {@link #clear()}, a {@link KeyCache} returns to its initial state.
	 */
	public void clear() {
		registered.clear();
	}
}
