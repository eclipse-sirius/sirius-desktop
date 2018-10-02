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

/**
 * The description of the Object.
 *
 * @author sbegaudeau
 *
 * @param <T>
 *            The type of the Object described
 */
public interface IItemDescriptor<T> {
	/**
	 * Returns the identifier.
	 *
	 * @return The identifier
	 */
	String getID();

	/**
	 * Returns the label.
	 *
	 * @return The label
	 */
	String getLabel();

	/**
	 * Returns the description.
	 *
	 * @return The description
	 */
	String getDescription();

	/**
	 * Returns the item.
	 *
	 * @return The item
	 */
	T getItem();
}
