/*******************************************************************************
 * Copyright (c) 2007, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.edit.parts;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationPreCommitListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.business.internal.edit.helpers.LabelAlignmentHelper;
import org.eclipse.sirius.diagram.edit.api.part.DiagramNameEditPartOperation;
import org.eclipse.sirius.diagram.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.graphical.edit.policies.LaunchToolEditPolicy;
import org.eclipse.sirius.diagram.graphical.edit.policies.ListItemDeletionEditPolicy;
import org.eclipse.sirius.diagram.internal.edit.policies.DNodeListElementItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.internal.edit.policies.SiriusTextNonResizableEditPolicy;
import org.eclipse.sirius.diagram.internal.providers.SiriusElementTypes;
import org.eclipse.sirius.diagram.internal.providers.SiriusParserProvider;
import org.eclipse.sirius.diagram.tools.api.permission.EditPartAuthorityListener;
import org.eclipse.sirius.diagram.tools.api.requests.RequestConstants;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
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
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();

        removeEditPolicy(EditPolicyRoles.SEMANTIC_ROLE);
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DNodeListElementItemSemanticEditPolicy());

        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new SiriusTextNonResizableEditPolicy());
        installEditPolicy(RequestConstants.REQ_LAUNCH_TOOL, new LaunchToolEditPolicy());

        removeEditPolicy(EditPolicy.COMPONENT_ROLE);
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ListItemDeletionEditPolicy(getEditingDomain()));
    }

    /**
     * @was-generated
     */
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
    protected boolean isEditable() {
        return getParser() != null;
    }

    /**
     * @was-generated
     */
    public IParser getParser() {
        if (parser == null) {
            String parserHint = ((View) getModel()).getType();
            IAdaptable hintAdapter = new SiriusParserProvider.HintAdapter(SiriusElementTypes.DNodeListElement_3010, getParserElement(), parserHint);
            parser = ParserService.getInstance().getParser(hintAdapter);
        }
        return parser;
    }

    /**
     * @not-generated add support for label alignement
     */
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
    protected void refreshFont() {
        // ViewNodeEditPartOperations.refreshFont(resolveSemanticElement(),
        // getFigure());
        /*
         * 
         * FontStyle style = (FontStyle)
         * getFontStyleOwnerView().getStyle(NotationPackage
         * .eINSTANCE.getFontStyle()); if (style != null) { FontData fontData =
         * new FontData(style.getFontName(), style.getFontHeight(),
         * (style.isBold() ? SWT.BOLD : SWT.NORMAL) | (style.isItalic() ?
         * SWT.ITALIC : SWT.NORMAL)); setFont(fontData); }
         */
    }

    /**
     * @not-generated
     */
    protected void refreshFontColor() {
    }

    /**
     * @not-generated
     */
    protected void refreshForegroundColor() {
        DiagramNameEditPartOperation.refreshFont(this);
    }

    /**
     * @not-generated
     */
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

    public void deactivate() {
        DiagramElementEditPartOperation.deactivate(this);
        final IPermissionAuthority auth = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(getEditingDomain().getResourceSet());
        auth.removeAuthorityListener(this.getEditPartAuthorityListener());
        if (isActive()) {
            super.deactivate();
        }
    }

    /**
     * Returns all the semantic elements (instances of <code>EObject</code>).
     * The list that is returned by this method is a view. If the client try to
     * change the content of the list then a
     * {@link UnsupportedOperationException} will be thrown.
     * 
     * @return all the semantic elements (instances of <code>EObject</code>).
     */
    public List getSemanticElements() {
        View view = (View) getModel();
        EObject viewpointElement = view.getElement();
        if (viewpointElement instanceof DDiagramElement) {
            List semanticElements = Collections.unmodifiableList(((DDiagramElement) viewpointElement).getSemanticElements());
            return semanticElements;
        }
        return Collections.EMPTY_LIST;
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
    public NotificationPreCommitListener getEAdapterSemanticElements() {
        if (this.adapterSemanticElements == null) {
            this.adapterSemanticElements = DiagramElementEditPartOperation.createEAdpaterSemanticElements(this);
        }
        return this.adapterSemanticElements;
    }
}
