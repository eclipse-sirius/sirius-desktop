/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.api.command.view;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.listener.Notification;
import org.eclipse.sirius.common.tools.api.listener.NotificationUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.tools.api.command.ui.RefreshFilter;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Command that is able to refresh a/many viewpoint element(s).
 * 
 * @author cbrun
 */
public class RefreshSiriusElement extends RecordingCommand {

    /** The refreshable. */
    private DRefreshable uniqueRefreshable;

    /** all refreshables. */
    private Collection<?> refreshablesList;

    /** The filters. */
    private Collection<RefreshFilter> filters;

    /**
     * Create a new {@link RefreshSiriusElement}.
     * 
     * @param domain
     *            the editing domain.
     * @param objectToRefresh
     *            the object to refresh.
     */
    public RefreshSiriusElement(final TransactionalEditingDomain domain, final DRefreshable objectToRefresh) {
        super(domain, "Refresh representation");
        this.uniqueRefreshable = objectToRefresh;
    }

    /**
     * Create a new {@link RefreshSiriusElement}.
     * 
     * @param domain
     *            the editing domain.
     * @param objectsToRefresh
     *            the objects to refresh.
     * @param filters
     *            the filters.
     */
    public RefreshSiriusElement(final TransactionalEditingDomain domain, final Collection<?> objectsToRefresh, final Collection<RefreshFilter> filters) {
        super(domain, "Refresh representation");
        this.refreshablesList = objectsToRefresh;
        this.filters = filters;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        if (uniqueRefreshable != null) {
            this.uniqueRefreshable.refresh();
            enableVisibilityUpdate(this.uniqueRefreshable);
            forceVisibilityRefresh(this.uniqueRefreshable);
        }
        if (refreshablesList != null) {
            final Iterator<?> it = refreshablesList.iterator();
            while (it.hasNext()) {
                final Object obj = it.next();
                if (obj instanceof DRepresentation) {
                    if (!isFiltered((DRepresentation) obj)) {
                        ((DRepresentation) obj).refresh();
                        enableVisibilityUpdate((DRefreshable) obj);
                        forceVisibilityRefresh((DRefreshable) obj);
                    }
                } else if (obj instanceof DRefreshable) {
                    ((DRefreshable) obj).refresh();
                    enableVisibilityUpdate((DRefreshable) obj);
                    forceVisibilityRefresh((DRefreshable) obj);
                }

            }
        }
    }

    private void forceVisibilityRefresh(DRefreshable obj) {
        if (obj instanceof DDiagram) {
            DisplayServiceManager.INSTANCE.getDisplayService().refreshAllElementsVisibility((DDiagram) obj);
        } else if (obj instanceof DDiagramElement) {
            DDiagramElement dde = (DDiagramElement) obj;
            DDiagram parentDiagram = dde.getParentDiagram();
            Session session = SessionManager.INSTANCE.getSession(dde.getTarget());
            NotificationUtil.sendNotification(parentDiagram, Notification.Kind.START, Notification.VISIBILITY);
            DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, parentDiagram);
            DisplayServiceManager.INSTANCE.getDisplayService().computeVisibility(mappingManager, parentDiagram, dde);
            NotificationUtil.sendNotification(parentDiagram, Notification.Kind.STOP, Notification.VISIBILITY);
        }
    }

    /**
     * Tells whether a {@link DRepresentation} is filtered by the filters or
     * not.
     * 
     * @param vp
     *            {@link DRepresentation} to test.
     * @return true if we should ignore it, false otherwise.
     */
    private boolean isFiltered(final DRepresentation vp) {
        if (filters != null) {
            final Iterator<RefreshFilter> it = filters.iterator();
            while (it.hasNext()) {
                final RefreshFilter filter = it.next();
                if (!filter.shouldRefresh(vp)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void enableVisibilityUpdate(DRefreshable obj) {
        DDiagram diagram = null;
        if (obj instanceof DDiagramElement) {
            diagram = ((DDiagramElement) obj).getParentDiagram();

        } else if (obj instanceof DDiagram) {
            diagram = (DDiagram) obj;
        }

        if (diagram != null) {
            NotificationUtil.sendNotification(diagram, org.eclipse.sirius.common.tools.api.listener.Notification.Kind.START,
                    org.eclipse.sirius.common.tools.api.listener.Notification.VISIBILITY_UPDATE);
        }
    }
}
