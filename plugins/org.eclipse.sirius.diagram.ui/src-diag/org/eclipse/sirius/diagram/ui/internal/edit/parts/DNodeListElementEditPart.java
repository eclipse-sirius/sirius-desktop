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

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationPreCommitListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.business.internal.edit.helpers.LabelAlignmentHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.DiagramNameEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.LaunchToolEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.ListItemDeletionEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusPropertyHandlerEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.DNodeListElementItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.SiriusTextNonResizableEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.providers.SiriusElementTypes;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.diagram.ui.tools.api.permission.EditPartAuthorityListener;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.viewpoint.LabelAlignment;

/**
 * @was-generated
 */
public class DNodeListElementEditPart extends AbstractGeneratedDiagramNameEditPart implements ITextAwareEditPart {

    /** The authority listener. */
    protected EditPartAuthorityListener authListener = new EditPartAuthorityListener(this);

    private NotificationPreCommitListener adapterSemanticElements;

    /**
     * @was-generated
     */
    public static final int VISUAL_ID = 3010;

    /**
     * @was-generated
     */
    public DNodeListElementEditPart(View view) {
        super(view);
    }

    /**
     * @was-generated
     */
    @Override
    public DragTracker getDragTracker(Request request) {
        if (request instanceof SelectionRequest && ((SelectionRequest) request).getLastButtonPressed() == 3) {
            return null;
        }
        return new DragEditPartsTrackerEx(this);
    }

    /**
     * We want a special behavior with direct editing.
     * 
     * @not-generated
     * 
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();

        removeEditPolicy(EditPolicyRoles.SEMANTIC_ROLE);
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DNodeListElementItemSemanticEditPolicy());

        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new SiriusTextNonResizableEditPolicy());
        installEditPolicy(RequestConstants.REQ_LAUNCH_TOOL, new LaunchToolEditPolicy());

        removeEditPolicy(EditPolicy.COMPONENT_ROLE);
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ListItemDeletionEditPolicy(getEditingDomain()));

        // Enables Font and Style action
        removeEditPolicy(EditPolicyRoles.PROPERTY_HANDLER_ROLE);
        installEditPolicy(EditPolicyRoles.PROPERTY_HANDLER_ROLE, new SiriusPropertyHandlerEditPolicy());
    }

    /**
     * @was-generated
     */
    @Override
    public void setLabel(IFigure figure) {
        unregisterVisuals();
        setFigure(figure);
        defaultText = getLabelTextHelper(figure);
        registerVisuals();
        refreshVisuals();
    }

    /**
     * @was-generated
     */
    @Override
    protected boolean isEditable() {
        return getParser() != null;
    }

    @Override
    protected IElementType getParserElementType() {
        return SiriusElementTypes.DNodeListElement_3010;
    }

    /**
     * @not-generated add support for label alignement
     */
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        refreshLabelAlignment();
    }

    private void refreshLabelAlignment() {
        final DDiagramElement diagElement = resolveDiagramElement();
        if (diagElement != null) {
            final IFigure parentFigure = getFigure().getParent();
            if (parentFigure != null && parentFigure.getLayoutManager() instanceof ConstrainedToolbarLayout) {
                final ConstrainedToolbarLayout ctl = (ConstrainedToolbarLayout) parentFigure.getLayoutManager();
                final LabelAlignment alignment = LabelAlignmentHelper.getLabelAlignementFor(diagElement);
                ctl.setMinorAlignment(LabelAlignmentHelper.getAsCTLMinorAlignment(alignment));
            }
        }
    }

    /**
     * @not-generated
     */
    @Override
    protected void refreshFont() {
        DiagramNameEditPartOperation.refreshFont(this);
    }

    /**
     * @not-generated
     */
    @Override
    protected void refreshFontColor() {
    }

    /**
     * @not-generated
     */
    @Override
    protected void refreshForegroundColor() {
        DiagramNameEditPartOperation.refreshFont(this);
    }

    /**
     * @not-generated
     */
    @Override
    protected void handleNotificationEvent(Notification notification) {
        final EditPart styleEditPart = getStyleEditPart();
        // Refreshes edit part.
        if (styleEditPart != null) {
            final EObject element = ((IGraphicalEditPart) styleEditPart).resolveSemanticElement();
            if (element != null && element.eResource() != null) {
                styleEditPart.refresh();
            }
        }
        final EObject element = resolveSemanticElement();
        if (element != null && element.eResource() != null && getParent() != null) {
            refresh();
        }

        Object feature = notification.getFeature();
        if (DiagramPackage.eINSTANCE.getDDiagramElementContainer_OwnedStyle() == feature) {
            refreshVisuals();
        }
        super.handleNotificationEvent(notification);

        if (notification.getEventType() == Notification.SET || notification.getEventType() == Notification.UNSET || notification.getEventType() == Notification.ADD)
            refresh();
    }

    /**
     * @was-generated
     */
    @Override
    protected IFigure createFigure() {
        IFigure label = createFigurePrim();
        defaultText = getLabelTextHelper(label);
        return label;
    }

    /**
     * @not-generated
     */
    protected IFigure createFigurePrim() {
        SiriusWrapLabel safeWrapLabel = new SiriusWrapLabel();
        safeWrapLabel.setTextWrap(true);
        return safeWrapLabel;
    }

    @Override
    public void activate() {
        if (!isActive()) {
            final IPermissionAuthority auth = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(getEditingDomain().getResourceSet());
            auth.addAuthorityListener(this.getEditPartAuthorityListener());
            super.activate();
            DiagramElementEditPartOperation.activate(this);
        }
        this.getEditPartAuthorityListener().refreshEditMode();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#enableEditMode()
     */
    @Override
    public void enableEditMode() {
        /*
         * We want to be sure nobody is enabling the edit mode if the element is
         * locked.
         */
        if (!this.getEditPartAuthorityListener().isLocked()) {
            super.enableEditMode();
        }
    }

    @Override
    public void deactivate() {
        DiagramElementEditPartOperation.deactivate(this);
        final IPermissionAuthority auth = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(getEditingDomain().getResourceSet());
        auth.removeAuthorityListener(this.getEditPartAuthorityListener());
        if (isActive()) {
            super.deactivate();
        }
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.AbstractDiagramNameEditPart#getEditPartAuthorityListener()
     */
    @Override
    public EditPartAuthorityListener getEditPartAuthorityListener() {
        return authListener;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#getEAdapterSemanticElements()
     */
    @Override
    public NotificationPreCommitListener getEAdapterSemanticElements() {
        if (this.adapterSemanticElements == null) {
            this.adapterSemanticElements = DiagramElementEditPartOperation.createEAdpaterSemanticElements(this);
        }
        return this.adapterSemanticElements;
    }

    @Override
    protected View getFontStyleOwnerView() {
        return (View) getModel();
    }
}
