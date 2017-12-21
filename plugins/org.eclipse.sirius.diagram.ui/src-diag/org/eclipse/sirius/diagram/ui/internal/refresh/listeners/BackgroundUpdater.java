/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.refresh.listeners;

import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.ComputedColor;
import org.eclipse.sirius.viewpoint.description.InterpolatedColor;

/**
 * A ResourceSet listener to refresh the diagram background if it is defined by Interpolated or computed color.
 * 
 * @author jmallet
 */
public class BackgroundUpdater extends ResourceSetListenerImpl {

    private DDiagram dDiagram;

    private static final NotificationFilter FEATURES_TO_REFACTOR_BACKGROUND = NotificationFilter.NOT_TOUCH;

    /**
     * Default constructor.
     * 
     * @param domain
     *            {@link TransactionalEditingDomain}
     * @param dDiagram
     *            {@link DDiagram}.
     */
    public BackgroundUpdater(TransactionalEditingDomain domain, DDiagram dDiagram) {
        super(FEATURES_TO_REFACTOR_BACKGROUND);
        domain.addResourceSetListener(this);
        this.dDiagram = dDiagram;
    }

    /**
     * Refresh the background colors of all the opened diagrams from the given session.
     */
    public static void refreshDiagramBackgrounds(Session session) {
        EclipseUIUtil.displayAsyncExec(() -> {
            IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
            if (uiSession != null) {
                for (DialectEditor editor : uiSession.getEditors()) {
                    if (editor instanceof DDiagramEditorImpl) {
                        DiagramEditPart diagramEditPart = ((DDiagramEditorImpl) editor).getDiagramEditPart();
                        ((DDiagramEditPart) diagramEditPart).configureBackground(diagramEditPart.getFigure());
                    }
                }
            }
        });
    }

    @Override
    public boolean isPostcommitOnly() {
        return true;
    }

    /**
     * Forces a refresh of the background color if needed.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        if (dDiagram instanceof DSemanticDiagram) {
            ColorDescription backgroundColor = ((DSemanticDiagram) dDiagram).getDescription().getBackgroundColor();
            if (backgroundColor instanceof InterpolatedColor || backgroundColor instanceof ComputedColor) {
                Session.of(dDiagram).ifPresent(s -> refreshDiagramBackgrounds(s));
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
    }
}
