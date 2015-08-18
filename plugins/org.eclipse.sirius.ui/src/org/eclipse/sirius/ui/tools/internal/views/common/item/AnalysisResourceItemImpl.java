/*******************************************************************************
 * Copyright (c) 2009, 2010, 2011 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ui.tools.api.views.common.item.AnalysisResourceItem;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;

import com.google.common.collect.Lists;

/**
 * Resource item wrapper class.
 * 
 * @author mchauvin
 */
public class AnalysisResourceItemImpl implements AnalysisResourceItem {

    private static final String SESSION_IMAGE = "icons/obj16/SiriusFile.gif"; //$NON-NLS-1$

    private final Session session;

    private final Resource resource;

    private final Object parent;

    // Special attribute to use this wrapper as a creator, it will no be added
    // to a hierarchy but just used to compute children.
    private boolean linkChildrenToParentMode;

    /**
     * Construct a new resource item wrapper.
     * 
     * @param session
     *            the current session
     * @param resource
     *            the resource to wrap
     * @param parent
     *            Parent tree item
     */
    public AnalysisResourceItemImpl(final Session session, final Resource resource, final Object parent) {
        this.session = session;
        this.resource = resource;
        this.parent = parent;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper#getWrappedObject()
     */
    public Object getWrappedObject() {
        return resource;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.ui.tools.api.view.common.item.ItemDecorator#getImage()
     */
    public Image getImage() {
        return SiriusEditPlugin.getPlugin().getBundledImage(SESSION_IMAGE);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.ui.tools.api.view.common.item.ItemDecorator#getText()
     */
    public String getText() {
        String result = StringUtil.EMPTY_STRING;
        if (resource.getResourceSet() != null && resource.getURI() != null && resource.getURI().lastSegment() != null) {
            result = resource.getURI().lastSegment() + " - [" + resource.getURI() + "]";
        } else {
            if (resource.getResourceSet() != null && resource.getURI() != null) {
                result = resource.getURI().toString();
            }
        }
        return result;
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
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        result = prime * result + ((resource == null) ? 0 : resource.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (this == obj) {
            result = true;
        } else if (obj instanceof AnalysisResourceItemImpl) {
            AnalysisResourceItemImpl other = (AnalysisResourceItemImpl) obj;
            result = true;

            if (parent == null && other.parent != null) {
                result = false;
            } else if (!parent.equals(other.parent)) {
                result = false;
            }

            if (resource == null && other.resource != null) {
                result = false;
            } else if (!resource.equals(other.resource)) {
                result = false;
            }

        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.api.views.common.item.CommonSessionItem#getChildren()
     */
    public Collection<?> getChildren() {
        final List<ViewpointItemImpl> all = Lists.newArrayList();
        if (resource != null) {
            for (final Viewpoint viewpoint : session.getSelectedViewpoints(false)) {
                all.add(new ViewpointItemImpl(session, viewpoint, resource, linkChildrenToParentMode ? parent : this));
            }
            Collections.sort(all);
        }
        return all;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.api.views.common.item.CommonSessionItem#getParent()
     */
    public Object getParent() {
        return parent;
    }

    /**
     * Allow to use this object as a children creator. It should not be added to
     * a hierarchy. It will link all created
     * {@link org.eclipse.sirius.ui.tools.api.views.common.item.CommonSessionItem}
     * to its parent.
     * 
     * @param linkChildrenToParent
     *            activate/deactivate the creator mode.
     */
    public void setSpecialMode(boolean linkChildrenToParent) {
        this.linkChildrenToParentMode = linkChildrenToParent;
    }
}
