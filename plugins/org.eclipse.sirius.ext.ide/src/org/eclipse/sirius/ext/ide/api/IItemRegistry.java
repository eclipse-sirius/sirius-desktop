/*******************************************************************************
 * Copyright (c) 2015, 2018 Obeo.
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
package org.eclipse.sirius.ext.ide.api;

import java.util.List;

/**
 * The registry used to track the descriptors of the extensions.
 *
 * @author sbegaudeau
 *
 * @param <T>
 *            The type of the Objects described
 */
public interface IItemRegistry<T> {
	/**
	 * Returns all the {@link IItemDescriptor} of the registry.
	 *
	 * @return The list of all the {@link IItemDescriptor} of the registry
	 */
	List<IItemDescriptor<T>> getItemDescriptors();

	/**
	 * Returns the {@link IItemDescriptor} with the given identifier.
	 *
	 * @param id
	 *            The identifier
	 * @return The {@link IItemDescriptor} with the given identifier or null if none could be found
	 */
	IItemDescriptor<T> getItemDescriptor(String id);

	/**
	 * Adds the given {@link IItemDescriptor} to the registry.
	 *
	 * @param descriptor
	 *            The descriptior
	 * @return The previous {@link IItemDescriptor} with the same identifier, or null if no registered
	 *         {@link IItemDescriptor} had the same identifier
	 */
	IItemDescriptor<T> add(IItemDescriptor<T> descriptor);

	/**
	 * Removes the {@link IItemDescriptor} with the given identifier.
	 *
	 * @param id
	 *            The identifier
	 * @return The {@link IItemDescriptor} removed or null if no registered {@link IItemDescriptor} had an identifier
	 *         matching the given one
	 */
	IItemDescriptor<T> remove(String id);

	/**
	 * Clears the registry.
	 */
	void clear();
}
