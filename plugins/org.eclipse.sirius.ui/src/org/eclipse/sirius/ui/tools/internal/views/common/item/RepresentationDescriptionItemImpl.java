/*******************************************************************************
 * Copyright (c) 2008, 2017 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * RepresentationDescriptionItem.
 * 
 * @author mchauvin
 */
public class RepresentationDescriptionItemImpl implements org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem, Comparable<RepresentationDescriptionItemImpl>, IAdaptable {

    private final Session session;

    private final RepresentationDescription representationDescription;

    private Resource resource;

    private boolean filterForResource;

    private final Object parent;

    /**
     * The viewpoint containing the representation description.
     */
    private Viewpoint viewpoint;

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
    public RepresentationDescriptionItemImpl(final Session session, final RepresentationDescription representationDescription, final Object parent) {
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
     * @param parent
     *            Parent tree item.
     * @param descriptionViewpoint
     *            the viewpoint containing the given representation description.
     */
    public RepresentationDescriptionItemImpl(final Session session, final RepresentationDescription representationDescription, final Object parent, Viewpoint descriptionViewpoint) {
        this.session = session;
        this.representationDescription = representationDescription;
        this.parent = parent;
        this.viewpoint = descriptionViewpoint;
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
    public RepresentationDescriptionItemImpl(final Session session, final RepresentationDescription representationDescription, final Resource resource, final Object parent) {
        this(session, representationDescription, parent);
        this.resource = resource;
        this.filterForResource = true;
    }

    /**
     * Returns the viewpoint containing the given representation description.
     * 
     * @return the viewpoint containing the given representation description.
     */
    public Viewpoint getViewpoint() {
        return viewpoint;
    }

    @Override
    public Object getWrappedObject() {
        return representationDescription;
    }

    @Override
    public int compareTo(final RepresentationDescriptionItemImpl o) {
        if (representationDescription.getName() != null) {
            return representationDescription.getName().compareTo(o.representationDescription.getName());
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
                result = EqualityHelper.areEquals(this.representationDescription, rdiObj.representationDescription);
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
        return representationDescription.hashCode() + parent.hashCode();
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Object getAdapter(Class adapter) {
        if (adapter == EObject.class) {
            return representationDescription;
        }
        return null;
    }

    @Override
    public Option<Session> getSession() {
        return Options.newSome(session);
    }

    @Override
    public Collection<?> getChildren() {
        final List<DRepresentationDescriptor> repDescriptorsCandidates = Lists.newArrayList(DialectManager.INSTANCE.getRepresentationDescriptors(representationDescription, session));

        List<RepresentationItemImpl> representations = Lists.newArrayList();

        if (filterForResource) {
            for (final DRepresentationDescriptor repDescriptor : repDescriptorsCandidates) {
                Resource representationResource = repDescriptor.eResource();
                if (representationResource != null && representationResource.equals(resource)) {
                    representations.add(new RepresentationItemImpl(repDescriptor, this));
                }
            }
        } else {
            for (final DRepresentationDescriptor repDescriptor : repDescriptorsCandidates) {
                representations.add(new RepresentationItemImpl(repDescriptor, this));
            }
        }

        Collections.sort(representations, Ordering.natural().onResultOf(new Function<RepresentationItemImpl, String>() {
            @Override
            public String apply(RepresentationItemImpl from) {
                return from.getDRepresentationDescriptor().getName();
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

    @Override
    public Object getParent() {
        return parent;
    }
}
