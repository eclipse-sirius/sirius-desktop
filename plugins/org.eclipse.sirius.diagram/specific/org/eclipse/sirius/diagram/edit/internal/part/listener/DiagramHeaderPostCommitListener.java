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
package org.eclipse.sirius.diagram.edit.internal.part.listener;

import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.ui.PlatformUI;

import org.eclipse.sirius.SiriusPackage;
import org.eclipse.sirius.diagram.tools.internal.editor.header.DiagramHeaderComposite;

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
                .or(NotificationFilter.createFeatureFilter(SiriusPackage.eINSTANCE.getBundledImage_Color()))
                .or(NotificationFilter.createFeatureFilter(SiriusPackage.eINSTANCE.getDot_BackgroundColor()))
                .or(NotificationFilter.createFeatureFilter(SiriusPackage.eINSTANCE.getEllipse_Color())).or(NotificationFilter.createFeatureFilter(SiriusPackage.eINSTANCE.getLozenge_Color()))
                .or(NotificationFilter.createFeatureFilter(SiriusPackage.eINSTANCE.getNote_Color())).or(NotificationFilter.createFeatureFilter(SiriusPackage.eINSTANCE.getSquare_Color()))
                .or(NotificationFilter.createFeatureFilter(SiriusPackage.eINSTANCE.getBasicLabelStyle_LabelColor()))
                .or(NotificationFilter.createFeatureFilter(SiriusPackage.eINSTANCE.getDRepresentationElement_Name()))
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
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
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
