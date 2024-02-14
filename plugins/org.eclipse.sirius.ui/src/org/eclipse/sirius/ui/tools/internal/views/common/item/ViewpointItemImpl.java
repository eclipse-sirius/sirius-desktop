/*******************************************************************************
 * Copyright (c) 2009, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.internal.views.common.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.common.ui.tools.api.view.common.item.ItemDecorator;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointItem;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;

/**
 * Represents viewpoint item in session view.
 *
 * @author mchauvin
 */
public class ViewpointItemImpl implements ViewpointItem, Comparable<ViewpointItemImpl>, ItemDecorator, IAdaptable {

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
    public ViewpointItemImpl(final Session session, final Viewpoint viewpoint, final Object parent) {
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
     * @see #ViewpointItem(Session, Viewpoint, Object)
     */
    public ViewpointItemImpl(final Session session, final Viewpoint viewpoint, final Resource resource, final Object parent) {
        this(session, viewpoint, parent);
        this.resource = resource;
        filterForResource = true;
    }

    @Override
    public Object getWrappedObject() {
        return viewpoint;
    }

    @Override
    public int compareTo(final ViewpointItemImpl o) {
        if (viewpoint != null && viewpoint.getName() != null && o.viewpoint != null && o.viewpoint.getName() != null) {
            return viewpoint.getName().compareTo(o.viewpoint.getName());
        }
        return 0;
    }

    @Override
    public boolean equals(final Object obj) {
        boolean result = false;
        if (this == obj) {
            result = true;
        } else {
            if (obj instanceof ViewpointItemImpl) {
                final ViewpointItemImpl objItem = (ViewpointItemImpl) obj;
                result = EqualityHelper.areEquals(this.viewpoint, objItem.viewpoint);
                result = result && this.session.equals(objItem.session);
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return (viewpoint != null ? viewpoint.hashCode() : 0) + session.hashCode();
    }

    @Override
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

    protected boolean isSafeViewpoint() {
        return viewpoint != null && viewpoint.eResource() != null && !viewpoint.eIsProxy();
    }

    @Override
    public Option<Session> getSession() {
        return Options.newSome(session);
    }

    @Override
    public Collection<?> getChildren() {
        final List<RepresentationDescriptionItemImpl> all = new ArrayList<RepresentationDescriptionItemImpl>();
        if (isSafeViewpoint()) {
            for (final RepresentationDescription representationDescription : new ViewpointQuery(viewpoint).getAllRepresentationDescriptions()) {
                if (isFilterForResource()) {
                    all.add(new RepresentationDescriptionItemImpl(session, representationDescription, getResource(), this));
                } else {
                    all.add(new RepresentationDescriptionItemImpl(session, representationDescription, this, viewpoint));
                }
            }
            Collections.sort(all);
        }
        return all;
    }

    public void setFilterForResource(boolean filterForResource) {
        this.filterForResource = filterForResource;
    }

    @Override
    public Object getParent() {
        return parent;
    }

    @Override
    public String getText() {
        return isSafeViewpoint() ? MessageTranslator.INSTANCE.getMessage(viewpoint, new IdentifiedElementQuery(viewpoint).getLabel()) : Messages.ViewpointItemImpl_notFoundLabel;
    }

    @Override
    public Image getImage() {
        return SiriusEditPlugin.getPlugin().getImage(SiriusEditPlugin.getPlugin().getItemImageDescriptor(viewpoint));
    }

    public Viewpoint getViewpoint() {
        return viewpoint;
    }
}
