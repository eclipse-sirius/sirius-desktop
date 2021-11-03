/*******************************************************************************
 * Copyright (c) 2007, 2022 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.refresh.listeners;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.business.internal.query.WorkspaceImageQuery;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * A ResourceSet listener to resize image with its default size.
 * 
 * @author jdupont
 */
public class GMFBoundsUpdater extends ResourceSetListenerImpl {

    private DDiagram dDiagram;

    /**
     * Default constructor.
     * 
     * @param domain
     *            {@link TransactionalEditingDomain}
     * @param dDiagram
     *            {@link DDiagram}.
     */
    public GMFBoundsUpdater(TransactionalEditingDomain domain, DDiagram dDiagram) {
        super(NotificationFilter.NOT_TOUCH.and(NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getDNode_OwnedStyle()))
                .or(NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getDDiagramElementContainer_OwnedStyle()))
                .or(NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getDNode_Height())).or(NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getDNode_Width())));
        this.dDiagram = dDiagram;
        domain.addResourceSetListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPrecommitOnly() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAggregatePrecommitListener() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
        Command cmd = null;
        Set<DDiagramElement> elementsToResize = new HashSet<DDiagramElement>();
        for (Notification notification : event.getNotifications()) {
            if (notification.getNotifier() instanceof DDiagramElement) {
                DDiagramElement dDiagramElement = (DDiagramElement) notification.getNotifier();
                DDiagram parentDDiagram = dDiagramElement.getParentDiagram();
                if (parentDDiagram != null && parentDDiagram.equals(dDiagram) && isStyleResize(notification, dDiagramElement)) {
                    elementsToResize.add(dDiagramElement);
                }
            }
        }
        if (!elementsToResize.isEmpty()) {
            cmd = new ResizeImageCommand(getTarget(), dDiagram, elementsToResize);
        }
        return cmd;
    }

    private boolean isStyleResize(Notification notification, DDiagramElement dDiagramElement) {
        boolean isStyleResize = false;
        if (notification.getNewValue() instanceof WorkspaceImage && notification.getOldValue() instanceof WorkspaceImage) {
            WorkspaceImage workspaceImageNewValue = (WorkspaceImage) notification.getNewValue();
            WorkspaceImage workspaceImageOldValue = (WorkspaceImage) notification.getOldValue();
            isStyleResize = isWorkspaceImageStyleResized(workspaceImageNewValue, workspaceImageOldValue, dDiagramElement);
        } else if (notification.getNewValue() instanceof Integer && notification.getOldValue() instanceof Integer) {
            isStyleResize = !notification.getNewValue().equals(notification.getOldValue());
        }
        return isStyleResize;
    }

    private boolean isWorkspaceImageStyleResized(WorkspaceImage workspaceImageNewValue, WorkspaceImage workspaceImageOldValue, DDiagramElement dDiagramElement) {
        boolean isWorkspaceImageStyleResized;
        boolean differentPath = true;
        if (workspaceImageNewValue != null && workspaceImageOldValue != null && workspaceImageNewValue.getWorkspacePath() != null) {
            if (workspaceImageNewValue.getWorkspacePath().equals(workspaceImageOldValue.getWorkspacePath())) {
                differentPath = false;
            }
        }
        StyleDescription description = dDiagramElement.getStyle().getDescription();
        isWorkspaceImageStyleResized = description instanceof WorkspaceImageDescription && ((WorkspaceImageDescription) description).getSizeComputationExpression().equals("-1") && differentPath; //$NON-NLS-1$
        return isWorkspaceImageStyleResized;
    }

    /**
     * Command to resize image with this original size.
     * 
     * @author jdupont
     * 
     */
    private static class ResizeImageCommand extends RecordingCommand {

        private DDiagram diagram;

        private Collection<DDiagramElement> elementsToResize;

        /**
         * Default constructor.
         * 
         * @param domain
         *            domain.
         * @param diagram
         *            current diagram.
         * @param elementsToRefresh
         *            elements which visibility needs to be refreshed
         */
        public ResizeImageCommand(TransactionalEditingDomain domain, DDiagram diagram, Collection<DDiagramElement> elementsToResize) {
            super(domain, Messages.ResizeImageCommand_label);
            this.diagram = diagram;
            this.elementsToResize = elementsToResize;
        }

        @Override
        protected void doExecute() {
            Session session = SessionManager.INSTANCE.getSession(((DSemanticDiagram) diagram).getTarget());
            for (DDiagramElement element : elementsToResize) {
                Node node = SiriusGMFHelper.getGmfNode(element, session);
                Size size = (Size) node.getLayoutConstraint();
                StyleDescription styleDescription = element.getStyle().getDescription();
                if (styleDescription instanceof WorkspaceImageDescription) {
                    double ratio = new WorkspaceImageQuery((WorkspaceImageDescription) styleDescription).getRatio();
                    size.setWidth(((DNode) element).getWidth() * LayoutUtils.SCALE);
                    size.setHeight((int) (((DNode) element).getWidth() / ratio * LayoutUtils.SCALE));
                } else if (styleDescription instanceof NodeStyleDescription) {
                    size.setHeight(((DNode) element).getHeight() * LayoutUtils.SCALE);
                    size.setWidth(((DNode) element).getWidth() * LayoutUtils.SCALE);
                }
            }
        }
    }

    /**
     * Dispose this listener.
     */
    public void dispose() {
        if (getTarget() != null) {
            getTarget().removeResourceSetListener(this);
        }
        dDiagram = null;
    }
}
