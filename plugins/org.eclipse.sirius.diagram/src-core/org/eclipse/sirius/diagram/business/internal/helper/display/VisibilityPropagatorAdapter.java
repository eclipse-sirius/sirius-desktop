/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.helper.display;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.listener.NotificationReceiver;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;

import com.google.common.collect.Iterables;

/**
 * This class is an EMF Adapter which listen change in the model to update a
 * {@link DDiagramElement#isVisible()}.
 * 
 * @author Maxime Porhel (mporhel)
 */
public class VisibilityPropagatorAdapter extends EContentAdapter implements NotificationReceiver {

    /** refresh is not activate by default */
    private boolean active;

    /** save previous activation */
    private boolean previousActive;

    private Session session;

    private DDiagram diagram;

    /**
     * Create a new adapter.
     * 
     * @param session
     *            the current session.
     * @param diagram
     *            the current diagram.
     */
    public VisibilityPropagatorAdapter(Session session, DDiagram diagram) {
        this.session = session;
        this.diagram = diagram;
        this.diagram.eAdapters().add(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.listener.NotificationReceiver#receive(org.eclipse.sirius.common.tools.api.listener.Notification.Kind,
     *      org.eclipse.sirius.common.tools.api.listener.Notification)
     */
    public void receive(final int kind, final int notification) {

        switch (notification) {
        case org.eclipse.sirius.common.tools.api.listener.Notification.VISIBILITY:
            switch (kind) {

            case org.eclipse.sirius.common.tools.api.listener.Notification.Kind.START:
                previousActive = active;
                active = true;
                break;

            case org.eclipse.sirius.common.tools.api.listener.Notification.Kind.STOP:
                active = previousActive;
                previousActive = false;
                break;

            default:
                break;
            }
            break;

        case org.eclipse.sirius.common.tools.api.listener.Notification.REFRESH_VISIBILITY_ON_DIAGRAM:
            switch (kind) {

            case org.eclipse.sirius.common.tools.api.listener.Notification.Kind.START:
                previousActive = active;
                active = false;
                break;

            case org.eclipse.sirius.common.tools.api.listener.Notification.Kind.STOP:
                active = previousActive;
                previousActive = false;
                break;

            default:
                break;
            }
            break;

        default:
            break;
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.EContentAdapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    public void notifyChanged(final Notification n) {

        super.notifyChanged(n);

        final Object notifier = n.getNotifier();

        if (active) {

            switch (n.getEventType()) {
            case Notification.ADD:
            case Notification.ADD_MANY:
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
            case Notification.SET:
            case Notification.UNSET:

                if (notifier instanceof DSemanticDiagram && DiagramPackage.eINSTANCE.getDDiagram_ActivatedFilters().equals(n.getFeature())) {
                    DisplayServiceManager.INSTANCE.getDisplayService().refreshAllElementsVisibility((DDiagram) notifier);
                }

                if (notifier instanceof DSemanticDiagram && DiagramPackage.eINSTANCE.getDDiagram_ActivatedLayers().equals(n.getFeature())) {
                    DisplayServiceManager.INSTANCE.getDisplayService().refreshAllElementsVisibility((DDiagram) notifier);
                }

                if (notifier instanceof DDiagramElement && n.getFeatureID(DDiagramElement.class) == DiagramPackage.DDIAGRAM_ELEMENT__VISIBLE) {
                    propagateElementVisibility((DDiagramElement) notifier, n);
                }

                break;
            default:
                /* do nothing if resolve or other notification kind */
                break;
            }
        }
    }

    @Override
    protected void addAdapter(Notifier notifier) {
        if (notifier instanceof DSemanticDiagram || notifier instanceof DDiagramElement) {
            super.addAdapter(notifier);
        }
    }

    private void propagateElementVisibility(final DDiagramElement element, final Notification n) {
        final DDiagram parentDiagram = element.getParentDiagram();

        if (element instanceof AbstractDNode) {
            for (final DNode borderNode : ((AbstractDNode) element).getOwnedBorderedNodes()) {
                borderNode.setVisible(getVisibility(parentDiagram, borderNode, n.getNewBooleanValue()));
            }
        }

        if (element instanceof DNodeContainer) {
            for (final DDiagramElement child : ((DNodeContainer) element).getOwnedDiagramElements()) {
                child.setVisible(getVisibility(parentDiagram, child, n.getNewBooleanValue()));
            }
        } else if (element instanceof DNodeList) {
            for (final DDiagramElement child : ((DNodeList) element).getElements()) {
                child.setVisible(getVisibility(parentDiagram, child, n.getNewBooleanValue()));
            }
        }

        if (element instanceof EdgeTarget) {
            for (DEdge linkedEdge : Iterables.concat(((EdgeTarget) element).getIncomingEdges(), ((EdgeTarget) element).getOutgoingEdges())) {
                linkedEdge.setVisible(getVisibility(parentDiagram, linkedEdge, n.getNewBooleanValue()));
            }
        }

    }

    private boolean getVisibility(final DDiagram parentDiagram, final DDiagramElement element, final boolean parentIsVisible) {
        if (!parentIsVisible) {
            return false;
        }
        DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, parentDiagram);
        return DisplayServiceManager.INSTANCE.getDisplayService().computeVisibility(mappingManager, parentDiagram, element);
    }

    /**
     * Dispose the current content adapter: remove it from the diagram.
     */
    public void dispose() {
        if (diagram != null && diagram.eResource() != null) {
            diagram.eAdapters().remove(this);
        }
        diagram = null;
        session = null;
    }
}
