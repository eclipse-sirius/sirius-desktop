/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.command.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.eclipse.sirius.tools.api.command.ui.RefreshFilter;
import org.eclipse.sirius.tools.api.command.ui.RefreshFilterManager;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Default implementation.
 * 
 * @author mchauvin
 */
public final class RefreshFilterManagerImpl implements RefreshFilterManager {

    /**
     * Here we keep a list of {@link RefreshFilter}.
     */
    private final Collection<RefreshFilter> filters = new ArrayList<RefreshFilter>();

    /**
     * Avoid instantiation.
     */
    private RefreshFilterManagerImpl() {

    }

    /**
     * Initialize a default manager implementation.
     * 
     * @return a default manager implementation
     */
    public static RefreshFilterManager init() {
        final RefreshFilterManager manager = new RefreshFilterManagerImpl();
        return manager;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.RefreshFilterManager#addRefreshFilter(org.eclipse.sirius.tools.api.command.ui.RefreshFilter)
     */
    public void addRefreshFilter(final RefreshFilter filter) {
        filters.add(filter);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.RefreshFilterManager#clearRefreshFilter()
     */
    public void clearRefreshFilter() {
        filters.clear();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.RefreshFilterManager#removeRefreshFilter(org.eclipse.sirius.tools.api.command.ui.RefreshFilter)
     */
    public void removeRefreshFilter(final RefreshFilter filter) {
        filters.remove(filter);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.RefreshFilter#shouldRefresh(org.eclipse.sirius.viewpoint.DRepresentation)
     */
    public boolean shouldRefresh(final DRepresentation representation) {
        final Iterator<RefreshFilter> it = filters.iterator();
        while (it.hasNext()) {
            final RefreshFilter filter = it.next();
            if (!filter.shouldRefresh(representation)) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.RefreshFilter#getOpenedRepresantationsToRefresh()
     */
    public Collection<DRepresentation> getOpenedRepresantationsToRefresh() {
        Collection<DRepresentation> representations = new LinkedHashSet<DRepresentation>();
        final Iterator<RefreshFilter> it = filters.iterator();
        while (it.hasNext()) {
            final RefreshFilter filter = it.next();
            representations.addAll(filter.getOpenedRepresantationsToRefresh());
        }
        return representations;
    }
}
