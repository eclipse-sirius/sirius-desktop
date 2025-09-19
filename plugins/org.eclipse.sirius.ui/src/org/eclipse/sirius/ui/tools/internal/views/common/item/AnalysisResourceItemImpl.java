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
import java.util.Objects;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ui.tools.api.views.common.item.AnalysisResourceItem;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;

/**
 * Resource item wrapper class.
 *
 * @author mchauvin
 */
public class AnalysisResourceItemImpl implements AnalysisResourceItem {

    private static final String SESSION_IMAGE = "icons/obj16/SiriusFile.gif"; //$NON-NLS-1$

    /**
     * The resource for representations per resource.
     */
    protected final Resource resource;

    private final Session session;

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

    @Override
    public Object getWrappedObject() {
        return resource;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.common.ui.tools.api.view.common.item.ItemDecorator#getImage()
     */
    public Image getImage() {
        return SiriusEditPlugin.getPlugin().getBundledImage(AnalysisResourceItemImpl.SESSION_IMAGE);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.common.ui.tools.api.view.common.item.ItemDecorator#getText()
     */
    public String getText() {
        String result = StringUtil.EMPTY_STRING;
        if (resource.getResourceSet() != null && resource.getURI() != null && resource.getURI().lastSegment() != null) {
            result = resource.getURI().lastSegment() + " - [" + resource.getURI() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            if (resource.getResourceSet() != null && resource.getURI() != null) {
                result = resource.getURI().toString();
            }
        }
        return result;
    }

    @Override
    public Option<Session> getSession() {
        return Options.newSome(session);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        result = prime * result + ((resource == null) ? 0 : resource.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (this == obj) {
            result = true;
        } else if (obj instanceof AnalysisResourceItemImpl) {
            AnalysisResourceItemImpl other = (AnalysisResourceItemImpl) obj;
            result = Objects.equals(parent, other.parent) && Objects.equals(resource, other.resource);
        }
        return result;
    }

    @Override
    public Collection<?> getChildren() {
        final List<ViewpointItemImpl> all = new ArrayList<>();
        if (resource != null) {
            for (final Viewpoint viewpoint : session.getSelectedViewpoints(false)) {
                all.add(new ViewpointItemImpl(session, viewpoint, resource, linkChildrenToParentMode ? parent : this));
            }
            Collections.sort(all);
        }
        return all;
    }

    @Override
    public Object getParent() {
        return parent;
    }

    /**
     * Allow to use this object as a children creator. It should not be added to a hierarchy. It will link all created
     * {@link org.eclipse.sirius.ui.tools.api.views.common.item.CommonSessionItem} to its parent.
     *
     * @param linkChildrenToParent
     *            activate/deactivate the creator mode.
     */
    public void setSpecialMode(boolean linkChildrenToParent) {
        this.linkChildrenToParentMode = linkChildrenToParent;
    }
}
