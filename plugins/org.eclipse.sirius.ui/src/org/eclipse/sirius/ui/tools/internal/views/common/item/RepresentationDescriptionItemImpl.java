/*******************************************************************************
 * Copyright (c) 2008, 2010, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.item;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * RepresentationDescriptionItem.
 * 
 * @author mchauvin
 */
public class RepresentationDescriptionItemImpl
		implements
		org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem,
		Comparable<RepresentationDescriptionItemImpl> , IAdaptable  {

	private final Session session;

	private final RepresentationDescription representationDescription;

	private Resource resource;

	private boolean filterForResource;

	private final Object parent;

	/**
	 * Create a new {@link RepresentationDescriptionItemImpl}.
	 * 
	 * @param session
	 *            the current session.
	 * @param representationDescription
	 *            the representation description.
	 * @param parent
	 *            Parent tree item.
	 */
	public RepresentationDescriptionItemImpl(final Session session,
			final RepresentationDescription representationDescription,
			final Object parent) {
		this.session = session;
		this.representationDescription = representationDescription;
		this.parent = parent;
	}

	/**
	 * Create a new {@link RepresentationDescriptionItemImpl}.
	 * 
	 * @param session
	 *            the current session.
	 * @param representationDescription
	 *            the representation description.
	 * @param resource
	 *            the resource.
	 * @param parent
	 *            Parent tree item.
	 */
	public RepresentationDescriptionItemImpl(final Session session,
			final RepresentationDescription representationDescription,
			final Resource resource, final Object parent) {
		this(session, representationDescription, parent);
		this.resource = resource;
		this.filterForResource = true;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getWrappedObject() {
		return representationDescription;
	}

	/**
	 * {@inheritDoc}
	 */
	public int compareTo(final RepresentationDescriptionItemImpl o) {
		if (representationDescription.getName() != null) {
			return representationDescription.getName().compareTo(
					o.representationDescription.getName());
		}
		return 0;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		if (this == obj) {
			result = true;
		} else {
			if (obj instanceof RepresentationDescriptionItemImpl) {
				final RepresentationDescriptionItemImpl rdiObj = (RepresentationDescriptionItemImpl) obj;
				result = rdiObj.compareTo(this) == 0
						&& rdiObj.parent.equals(parent);
			}
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return representationDescription.getName().hashCode()
				+ parent.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class adapter) {
		if (adapter == EObject.class) {
			return representationDescription;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.sirius.ui.tools.api.views.common.item.CommonSessionItem#getSession()
	 */
	public Option<Session> getSession() {
		return Options.newSome(session);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.sirius.ui.tools.api.views.common.item.CommonSessionItem#getChildren()
	 */
	public Collection<?> getChildren() {
		final List<DRepresentation> representationsCandidates = Lists
				.newArrayList(DialectManager.INSTANCE.getRepresentations(
						representationDescription, session));

		List<RepresentationItemImpl> representations = Lists.newArrayList();

		if (filterForResource) {
			for (final DRepresentation representation : representationsCandidates) {
				if (representation.eResource() != null
						&& representation.eResource().equals(resource)) {
					representations.add(new RepresentationItemImpl(
							representation, this));
				}
			}
		} else {
			for (final DRepresentation representation : representationsCandidates) {
				representations.add(new RepresentationItemImpl(representation,
						this));
			}
		}

		Collections.sort(
				representations,
				Ordering.natural().onResultOf(
						new Function<RepresentationItemImpl, String>() {
							public String apply(RepresentationItemImpl from) {
								return from.getRepresentation().getName();
							}
						}));
		return representations;
	}

	public boolean isFilterForResource() {
		return filterForResource;
	}

	public void setFilterForResource(boolean filterForResource) {
		this.filterForResource = filterForResource;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.sirius.ui.tools.api.views.common.item.CommonSessionItem#getParent()
	 */
	public Object getParent() {
		return parent;
	}
}
