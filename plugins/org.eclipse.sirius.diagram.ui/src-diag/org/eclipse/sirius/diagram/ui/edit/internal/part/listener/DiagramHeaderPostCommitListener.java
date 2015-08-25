/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.edit.internal.part.listener;

import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.header.DiagramHeaderComposite;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * A ResourceSet listener to refresh the diagram header after changes on :
 * <UL>
 * <LI>Name of Node,</LI>
 * <LI>Location of Node,</LI>
 * <LI>Width of Node,</LI>
 * <LI>Label Color of Node,</LI>
 * <LI>Background Color of Node.</LI>
 * </UL>
 * 
 * @author lredor
 * 
 */
public class DiagramHeaderPostCommitListener extends ResourceSetListenerImpl {
    private DiagramHeaderComposite diagramHeader;

    /**
     * Default constructor.
     * 
     * @param domain
     *            {@link TransactionalEditingDomain}
     * @param diagramHeader
     *            the header to refresh.
     */
    public DiagramHeaderPostCommitListener(TransactionalEditingDomain domain, DiagramHeaderComposite diagramHeader) {
        super(NotificationFilter.NOT_TOUCH.and(NotificationFilter.createFeatureFilter(NotationPackage.eINSTANCE.getNode_LayoutConstraint())
                .or(NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getBundledImage_Color()))
                .or(NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getDot_BackgroundColor())).or(NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getEllipse_Color()))
                .or(NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getLozenge_Color())).or(NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getNote_Color()))
                .or(NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getSquare_Color()))
                .or(NotificationFilter.createFeatureFilter(ViewpointPackage.eINSTANCE.getBasicLabelStyle_LabelColor()))
                .or(NotificationFilter.createFeatureFilter(ViewpointPackage.eINSTANCE.getDRepresentationElement_Name()))
                .or(NotificationFilter.createFeatureFilter(NotationPackage.eINSTANCE.getLocation_X()).or(NotificationFilter.createFeatureFilter(NotationPackage.eINSTANCE.getSize_Width())))));
        this.diagramHeader = diagramHeader;
        domain.addResourceSetListener(this);
    }

    @Override
    public boolean isPrecommitOnly() {
        return false;
    }

    @Override
    public boolean isPostcommitOnly() {
        return true;
    }

    /**
     * Forces a refresh of the diagram header composite.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        refreshDiagramHeader();
    }

    /**
     * Refresh the diagram header composite.
     */
    private void refreshDiagramHeader() {
        EclipseUIUtil.displayAsyncExec(new Runnable() {
            public void run() {
                if (diagramHeader != null && !diagramHeader.isDisposed()) {
                    diagramHeader.rebuildHeaderSection();
                }
            }
        });
    }

    /**
     * Dispose this listener.
     */
    public void dispose() {
        if (getTarget() != null) {
            getTarget().removeResourceSetListener(this);
        }
        diagramHeader = null;
    }
}
