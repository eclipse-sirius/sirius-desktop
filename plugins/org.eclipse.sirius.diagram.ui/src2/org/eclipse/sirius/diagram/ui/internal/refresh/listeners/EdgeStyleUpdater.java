/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.refresh.listeners;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.UpdateGMFEdgeStyleCommand;

/**
 * This listener is in charge of updating the GMF informations related to Edge
 * styles (e.g. routing style) when the {@link EdgeStyle} changes.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class EdgeStyleUpdater extends ResourceSetListenerImpl {

    /**
     * The Viewpoint features that, when modified, should trigger an update of
     * GMF features.
     */
    private static final NotificationFilter LISTENED_FEATURES = NotificationFilter.NOT_TOUCH.and(NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getDEdge_OwnedStyle()).or(
            NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getEdgeStyle_RoutingStyle())));

    /**
     * The viewpoint cross reference used to get GMF elements from
     * DDiagramElements.
     */
    private ECrossReferenceAdapter crossReference;

    /**
     * Default constructor.
     * 
     * @param domain
     *            The {@link TransactionalEditingDomain} to listen.
     * @param crossReference
     *            The viewpoint cross reference used to get GMF elements from
     *            DDiagramElements.
     */
    public EdgeStyleUpdater(TransactionalEditingDomain domain, ECrossReferenceAdapter crossReference) {
        super(LISTENED_FEATURES);
        domain.addResourceSetListener(this);
        this.crossReference = crossReference;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#isPrecommitOnly()
     */
    @Override
    public boolean isPrecommitOnly() {
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#isAggregatePrecommitListener()
     */
    @Override
    public boolean isAggregatePrecommitListener() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command transactionAboutToCommit(final ResourceSetChangeEvent event) throws RollbackException {
        CompoundCommand cc = new CompoundCommand();
        for (Notification notification : event.getNotifications()) {
            EdgeStyle viewpointEdgeStyle = null;
            Edge gmfEdge = null;
            // If the notification indicates:
            // - a new edge style
            if (notification.getNotifier() instanceof DEdge && notification.getNewValue() instanceof EdgeStyle) {
                viewpointEdgeStyle = (EdgeStyle) notification.getNewValue();
                gmfEdge = SiriusGMFHelper.getGmfEdge((DEdge) notification.getNotifier(), crossReference);

            }
            // - a change of EdgeStyle
            else if (notification.getNotifier() instanceof EdgeStyle && DiagramPackage.eINSTANCE.getEdgeStyle_RoutingStyle().equals(notification.getFeature())) {
                viewpointEdgeStyle = (EdgeStyle) notification.getNotifier();
                gmfEdge = SiriusGMFHelper.getGmfEdge((DEdge) viewpointEdgeStyle.eContainer(), crossReference);
            }

            // Propagate change to GMF
            if (gmfEdge != null && viewpointEdgeStyle != null) {
                cc.append(new UpdateGMFEdgeStyleCommand(getTarget(), gmfEdge, viewpointEdgeStyle));
            }
        }
        return cc;
    }

    /**
     * Dispose this listener.
     */
    public void dispose() {
        if (getTarget() != null) {
            getTarget().removeResourceSetListener(this);
        }
        crossReference = null;
    }
}
