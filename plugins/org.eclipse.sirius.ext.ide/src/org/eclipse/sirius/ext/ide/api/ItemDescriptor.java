/*******************************************************************************
 * Copyright (c) 2015, 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.ide.api;

/**
 * The implementation of the {@link IItemDescriptor}.
 *
 * @author sbegaudeau
 *
 * @param <T>
 *            The type of the object described
 */
public class ItemDescriptor<T> implements IItemDescriptor<T> {

	/**
	 * The identifier.
	 */
	private String id;

	/**
	 * The label.
	 */
	private String label;

	/**
	 * The description.
	 */
	private String description;

	/**
	 * The instance.
	 */
	private T instance;

	/**
	 * The constructor.
	 *
	 * @param id
	 *            The identifier
	 * @param label
	 *            The label
	 * @param description
	 *            The description
	 * @param instance
	 *            The instance
	 */
	public ItemDescriptor(String id, String label, String description, T instance) {
		this.id = id;
		this.label = label;
		this.description = description;
		this.instance = instance;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.api.extensions.IItemDescriptor#getID()
	 */
	@Override
	public String getID() {
		return this.id;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.api.extensions.IItemDescriptor#getLabel()
	 */
	@Override
	public String getLabel() {
		return this.label;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.api.extensions.IItemDescriptor#getDescription()
	 */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.api.extensions.IItemDescriptor#getItem()
	 */
	@Override
	public T getItem() {
		return this.instance;
	}

}
