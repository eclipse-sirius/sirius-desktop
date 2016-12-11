/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.diagram.ui.requests.ChangePropertyValueRequest;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.DEdgeNameSelectionFeedbackEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.ResizableShapeLabelEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusPropertyHandlerEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.locator.EdgeLabelLocator;
import org.eclipse.sirius.diagram.ui.internal.providers.SiriusElementTypes;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.diagram.ui.tools.api.policy.CompoundEditPolicy;
import org.eclipse.sirius.ext.base.Option;

/**
 * @was-generated
 */
@SuppressWarnings("restriction")
public class AbstractDEdgeNameEditPart extends AbstractGeneratedDiagramNameEditPart implements ITextAwareEditPart {

    /**
     * @was-generated
     */
    public AbstractDEdgeNameEditPart(View view) {
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
        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new ResizableShapeLabelEditPolicy());

        EditPolicy currentSelectionEditPolicy = getEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE);
        EditPolicy selectionFeedBackEditPolicy = new DEdgeNameSelectionFeedbackEditPolicy();
        if (currentSelectionEditPolicy != null) {
            CompoundEditPolicy compoundEditPolicy = new CompoundEditPolicy();
            compoundEditPolicy.addEditPolicy(currentSelectionEditPolicy);
            compoundEditPolicy.addEditPolicy(selectionFeedBackEditPolicy);
            removeEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE);
            installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, compoundEditPolicy);
        } else {
            installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, selectionFeedBackEditPolicy);
        }
        // Enables Font and Style action on Label of Edge
        removeEditPolicy(EditPolicyRoles.PROPERTY_HANDLER_ROLE);
        installEditPolicy(EditPolicyRoles.PROPERTY_HANDLER_ROLE, new SiriusPropertyHandlerEditPolicy());

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

    @Override
    protected IElementType getParserElementType() {
        return SiriusElementTypes.DEdge_4001;
    }

    /**
     * @not-generated remove direct edit manager if there is no edit behavior
     */
    @Override
    protected void setManager(DirectEditManager manager) {
        DEdge edge = (DEdge) resolveSemanticElement();
        Option<EdgeMapping> edgeMapping = new IEdgeMappingQuery(edge.getActualMapping()).getEdgeMapping();
        if (edgeMapping.some() && edgeMapping.get().getLabelDirectEdit() != null) {
            this.manager = manager;
        }
    }

    /**
     * @not-generated
     */
    @Override
    protected void handleNotificationEvent(Notification event) {
        if (resolveSemanticElement() instanceof DEdge) {
            Object feature = event.getFeature();
            if (DiagramPackage.eINSTANCE.getDDiagramElementContainer_OwnedStyle() == feature) {
                refreshVisuals();
            }
            if (NotationPackage.eINSTANCE.getNode_LayoutConstraint().equals(feature)) {
                refreshBounds();
            }
            super.handleNotificationEvent(event);
        }
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
    public void refreshBounds() {
        // The case of NonResizable is useless here for SiriusWrapLabel.
        handleResizableRefreshBounds();
    }

    /**
     * Handles resizable label refresh bounds. "Override" to use a specific
     * {@link EdgeLabelLocator}.
     */
    private void handleResizableRefreshBounds() {
        int dx = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_X())).intValue();
        int dy = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_Y())).intValue();
        int width = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Width())).intValue();
        int height = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height())).intValue();
        Rectangle rectangle = new Rectangle(dx, dy, width, height);
        if (getParent() instanceof AbstractConnectionEditPart) {
            ((AbstractGraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), new EdgeLabelLocator(getFigure().getParent(), rectangle, getKeyPoint()));
        }
    }

    @Override
    public EditPart getTargetEditPart(Request request) {
        if (request instanceof ChangePropertyValueRequest && ((ChangePropertyValueRequest) request).getPropertyID().equals(Properties.ID_FONTCOLOR)) {
            return getParent();
        } else {
            return super.getTargetEditPart(request);
        }
    }
}
