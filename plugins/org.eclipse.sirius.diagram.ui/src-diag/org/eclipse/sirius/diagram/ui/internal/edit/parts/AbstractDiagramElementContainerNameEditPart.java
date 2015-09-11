/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.handles.NonResizableHandleKit;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;

/**
 * Abstract edit part for {@link DDiagramElementContainer} name edit parts. It
 * regroups all the common code previously genrated for the subclasses.
 */
public abstract class AbstractDiagramElementContainerNameEditPart extends AbstractGeneratedDiagramNameEditPart implements ITextAwareEditPart {

    /**
     * @was-generated
     */
    public AbstractDiagramElementContainerNameEditPart(View view) {
        super(view);
    }

    /**
     * We want a special behavior with direct editing.
     * 
     * @not-generated
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new NonResizableEditPolicy() {

            @Override
            protected List createSelectionHandles() {
                List handles = new ArrayList();
                NonResizableHandleKit.addMoveHandle((GraphicalEditPart) getHost(), handles);
                return handles;
            }

            @Override
            public Command getCommand(Request request) {
                return null;
            }

            @Override
            public boolean understandsRequest(Request request) {
                return false;
            }
        });
    }

    /**
     * @not-generated
     */
    public void setLabel(SiriusWrapLabel figure) {
        unregisterVisuals();
        setFigure(figure);
        defaultText = getLabelTextHelper(figure);
        registerVisuals();
        refreshVisuals();
    }

    /**
     * @not-generated : select the parent
     */
    @Override
    public boolean isSelectable() {
        return false;
    }

    /**
     * @was-generated
     */
    @Override
    protected void handleNotificationEvent(Notification event) {
        Object feature = event.getFeature();
        if (DiagramPackage.eINSTANCE.getDDiagramElementContainer_OwnedStyle() == feature) {
            refreshVisuals();
        }
        super.handleNotificationEvent(event);
    }

    /**
     * @was-generated
     */
    @Override
    protected IFigure createFigure() {
        // Parent should assign one using setLabel() method
        return null;
    }

    /**
     * @not-generated
     */
    @Override
    public void setLabel(IFigure figure) {
        if (figure instanceof SiriusWrapLabel) {
            this.setLabel((SiriusWrapLabel) figure);
        }
    }

    @Override
    public void activate() {
        if (!isActive()) {
            super.activate();
        }
    }

    @Override
    public void deactivate() {
        if (isActive()) {
            super.deactivate();
        }
    }

}
