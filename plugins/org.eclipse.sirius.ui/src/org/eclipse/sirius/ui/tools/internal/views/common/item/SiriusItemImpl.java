/*******************************************************************************
 * Copyright (c) 2009, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.graphics.Image;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.common.ui.tools.api.view.common.item.ItemDecorator;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.api.views.common.item.SiriusItem;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * Represents viewpoint item in session view.
 * 
 * @author mchauvin
 */
public class SiriusItemImpl implements SiriusItem,
		Comparable<SiriusItemImpl>, ItemDecorator, IAdaptable {

	private final Session session;

	private final Viewpoint viewpoint;

	private Resource resource;

	private boolean filterForResource;

	private final Object parent;

	/**
	 * Constructor.
	 * 
	 * @param session
	 *            Session
	 * @param viewpoint
	 *            Sirius
	 * @param parent
	 *            Parent tree item
	 */
	public SiriusItemImpl(final Session session, final Viewpoint viewpoint,
			final Object parent) {
		this.session = session;
		this.viewpoint = viewpoint;
		this.parent = parent;
	}

	/**
	 * Constructor.
	 * 
	 * @param resource
	 *            Resource
	 * @param session
	 *            Session
	 * @param viewpoint
	 *            Sirius
	 * @param parent
	 *            Parent tree item
	 * @see #SiriusItem(Session, Viewpoint, Object)
	 */
	public SiriusItemImpl(final Session session, final Viewpoint viewpoint,
			final Resource resource, final Object parent) {
		this(session, viewpoint, parent);
		this.resource = resource;
		filterForResource = true;
	}

	public Object getWrappedObject() {
		return viewpoint;
	}

	/**
	 * {@inheritDoc}
	 */
	public int compareTo(final SiriusItemImpl o) {
		if (viewpoint.getName() != null) {
			return viewpoint.getName().compareTo(o.viewpoint.getName());
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
			if (obj instanceof SiriusItemImpl) {
				final SiriusItemImpl objItem = (SiriusItemImpl) obj;
				result = compareTo(objItem) == 0
						&& objItem.parent.equals(parent);
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
		return viewpoint.getName().hashCode() + parent.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class adapter) {
		if (adapter == EObject.class) {
			return viewpoint;
		}
		return null;
	}

	/**
	 * isFilterForResource.
	 * 
	 * @return the filterForResource
	 */
	public boolean isFilterForResource() {
		return filterForResource;
	}

	/**
	 * Get the resource.
	 * 
	 * @return the resource
	 */
	protected Resource getResource() {
		return resource;
	}

	protected boolean isSafeSirius() {
		return viewpoint.eResource() != null && !viewpoint.eIsProxy();
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
		final List<RepresentationDescriptionItemImpl> all = new ArrayList<RepresentationDescriptionItemImpl>();
		if (isSafeSirius()) {
			for (final RepresentationDescription representationDescription : new ViewpointQuery(
					viewpoint).getAllRepresentationDescriptions()) {
				if (isFilterForResource()) {
					all.add(new RepresentationDescriptionItemImpl(session,
							representationDescription, getResource(), this));
				} else {
					all.add(new RepresentationDescriptionItemImpl(session,
							representationDescription, this));
				}
			}
			Collections.sort(all);
		}
		return all;
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

	public String getText() {
		return isSafeSirius() ? new IdentifiedElementQuery(viewpoint)
				.getLabel() : "\"Not found\"";
	}

	public Image getImage() {
		return SiriusEditPlugin.getPlugin().getImage(
				SiriusEditPlugin.getPlugin().getItemImageDescriptor(
						viewpoint));
	}
}
