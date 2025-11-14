/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.edit.api.part;

import java.util.List;
import java.util.Optional;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationPreCommitListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.internal.util.LabelViewConstants;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeConnectionRequest;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.ITreeConnection;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.description.CenteringStyle;
import org.eclipse.sirius.diagram.description.tool.RequestDescription;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.DEdgeQuery;
import org.eclipse.sirius.diagram.ui.business.internal.view.ShowingViewUtil;
import org.eclipse.sirius.diagram.ui.edit.internal.part.CommonEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramEdgeEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.EditStatusUpdater;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.DEdgeSelectionFeedbackEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.LaunchToolEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusEdgeLabelSnapBackEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusPropertyHandlerEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.SiriusConnectionEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabelWithAttachment;
import org.eclipse.sirius.diagram.ui.tools.api.permission.EditPartAuthorityListener;
import org.eclipse.sirius.diagram.ui.tools.api.policy.CompoundEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.policies.SiriusConnectionEndPointEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.internal.routers.SiriusBendpointConnectionRouter;
import org.eclipse.sirius.diagram.ui.tools.internal.ruler.SiriusSnapToHelperUtil;
import org.eclipse.sirius.ext.gmf.runtime.draw2d.ui.figures.SiriusPolylineConnectionEx;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.IFigureWithoutLabels;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * Implementation of the default behaviors of edges.
 * 
 * @author ymortier
 */
@SuppressWarnings("restriction")
public abstract class AbstractDiagramEdgeEditPart extends ConnectionNodeEditPart implements IDiagramEdgeEditPart {

    /**
     * The minimum length(width or height) the edge box must have to be considered as selectable.
     */
    private static final int EDGE_MINIMUM_LENGTH = 20;

    /**
     * The minimum thickness the edge box at top, bottom, left or right position of the union of the nodes boxes must
     * have to be considered as selectable zone.
     */
    private static final int EDGE_MINIMUM_THICKNESS = 20;

    /**
     * Define the minimum selection box size for the source and target of the edge (if source or target is bordered
     * node), to fix the horizontal and vertical increment around which the selection of this edge select the source or
     * the target and not the edge.
     */
    private static final int SOURCE_TARGET_MINIMUM_SIZE_SELECTION = 20;

    /** The path router. */
    private static final BendpointConnectionRouter ROUTER = new SiriusBendpointConnectionRouter();

    /** The authority listener. */
    protected EditPartAuthorityListener authListener = new EditPartAuthorityListener(this);

    /** Listens the diagram element. */
    private NotificationListener adapterDiagramElement;

    /** Listens the routing style. */
    private NotificationPreCommitListener adapterRoutingStyle;

    /** listen to semantic elements container */
    private NotificationListener editModeListener = new EditStatusUpdater(this);

    /**
     * The routingStyle for which a listener is potentially added in the DiagramEventBroker during the activation of
     * this edit part. It is used to remove the listener during deactivation.
     */
    private RoutingStyle routingStyle;

    /**
     * The listener added in the DiagramEventBroker for the above routingStyle during the activation of this edit part.
     * It is used to remove the listener during deactivation.
     */
    private NotificationPreCommitListener routingStyleNotificationPreCommitListener;

    /**
     * Creates a new <code>AbstractDiagramEdgeEditPart</code>.
     * 
     * @param view
     *            the GMF view.
     */
    public AbstractDiagramEdgeEditPart(final View view) {
        super(view);
    }

    @Override
    protected void registerModel() {
        super.registerModel();
        DiagramElementEditPartOperation.registerModel(this);
    }

    @Override
    protected void unregisterModel() {
        super.unregisterModel();
        DiagramElementEditPartOperation.unregisterModel(this);
    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        // Remove the connection end points role to replace by viewpoint end
        // points edit policy. it's for have the scroll diagram on reconnect
        // edge.
        removeEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE);
        installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new SiriusConnectionEndPointEditPolicy());
        // Enables Font and Style action
        removeEditPolicy(EditPolicyRoles.PROPERTY_HANDLER_ROLE);
        installEditPolicy(EditPolicyRoles.PROPERTY_HANDLER_ROLE, new SiriusPropertyHandlerEditPolicy());
        installEditPolicy(EditPolicy.CONNECTION_ROLE, new SiriusConnectionEditPolicy());
        installEditPolicy(RequestConstants.REQ_LAUNCH_TOOL, new LaunchToolEditPolicy());

        final EditPolicy currentSelectionEditPolicy = getEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE);
        final EditPolicy selectionFeedBackEditPolicy = new DEdgeSelectionFeedbackEditPolicy();
        if (currentSelectionEditPolicy != null) {
            final CompoundEditPolicy selectionCompoundEditPolicy = new CompoundEditPolicy();
            selectionCompoundEditPolicy.addEditPolicy(currentSelectionEditPolicy);
            selectionCompoundEditPolicy.addEditPolicy(selectionFeedBackEditPolicy);
            removeEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE);
            installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, selectionCompoundEditPolicy);
        } else {
            installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, selectionFeedBackEditPolicy);
        }
        // Policy that handles snap back action and have a lighter color for
        // guides
        installEditPolicy(EditPolicyRoles.SNAP_FEEDBACK_ROLE, new SiriusEdgeLabelSnapBackEditPolicy());
    }

    @Override
    public Command getCommand(final Request request) {
        final Command cmd = super.getCommand(request);
        return CommonEditPartOperation.handleAutoPinOnInteractiveMove(this, request, cmd);
    }

    @Override
    protected void addNotationalListeners() {
        super.addNotationalListeners();
        Object model = getModel();
        if (model instanceof View) {
            ViewQuery viewQuery = new ViewQuery((View) getModel());
            Optional<DDiagram> diagram = viewQuery.getDDiagram();
            if (diagram.isPresent()) {
                addListenerFilter("ShowingMode", this, diagram.get(), DiagramPackage.eINSTANCE.getDDiagram_IsInShowingMode()); //$NON-NLS-1$
            }
        }
    }

    @Override
    protected void removeNotationalListeners() {
        super.removeNotationalListeners();
        removeListenerFilter("ShowingMode"); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#setVisibility(boolean)
     */
    @Override
    public void setVisibility(boolean vis) {
        if (!vis && getSelected() != SELECTED_NONE)
            getViewer().deselect(this);
        ViewQuery viewQuery = new ViewQuery((View) getModel());
        if (viewQuery.isInShowingMode()) {
            figure.setVisible(true);
        } else {
            figure.setVisible(vis);
        }
        getFigure().revalidate();
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     * Tests that the semantic model is an Edge. Refresh the edit part if the event is :
     * <ul>
     * <li><code>Notification.SET</code></li>
     * <li><code>Notification.UNSET</code></li>
     * <li><code>Notification.ADD</code></li>
     * </ul>
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart#handleNotificationEvent(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    protected void handleNotificationEvent(final Notification notification) {
        DiagramElementEditPartOperation.handleNotificationEvent(this, notification);

        final EObject element = resolveSemanticElement();
        if (element instanceof DEdge) {
            updateCenteringProperty((DEdge) element, notification);
            super.handleNotificationEvent(notification);
        }
    }

    private void updateCenteringProperty(DEdge element, Notification notification) {
        if (DiagramPackage.eINSTANCE.getEdgeStyle_Centered() == notification.getFeature()) {
            EdgeStyle edgeStyle = element.getOwnedStyle();
            if (edgeStyle != null) {
                IFigure figure = getFigure();
                if (figure instanceof ViewEdgeFigure) {
                    ((ViewEdgeFigure) figure).setCentering(edgeStyle.getCentered());
                }
            }
        }
    }

    @Override
    public PolylineConnectionEx getPolylineConnectionFigure() {
        final Connection connection = this.getConnectionFigure();
        if (connection instanceof PolylineConnectionEx) {
            return (PolylineConnectionEx) connection;
        }
        throw new IllegalStateException();
    }

    @Override
    public void refreshVisuals() {
        super.refreshVisuals();
        DiagramEdgeEditPartOperation.refreshVisuals(this);
    }

    @Override
    protected void refreshVisibility() {
        boolean gmfModelVisibility = ((View) getModel()).isVisible();
        if (gmfModelVisibility) {
            // By default, only the GMF model visibility status is considered. But if the source or the target is
            // contained in a collapsed compartment the edge must not be visible (like in
            // org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart.ConnectionRefreshMgr.refreshConnections(ShapeCompartmentEditPart)).
            IGraphicalEditPart source = (IGraphicalEditPart) this.getSource();
            IGraphicalEditPart target = (IGraphicalEditPart) this.getTarget();
            if (source == null || target == null) {
                setVisibility(false);
            } else if (!source.getFigure().isShowing() || !target.getFigure().isShowing()) {
                setVisibility(false);
            } else {
                setVisibility(true);
            }
        } else {
            setVisibility(false);
        }
    }

    @Override
    protected void refreshFont() {
        super.refreshFont();
        DiagramEdgeEditPartOperation.refreshFont(this);
    }

    /**
     * {@inheritDoc} Replace the original refreshBendpoints to compute the bendpoints of the edge with path.
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart#refreshBendpoints()
     */
    @Override
    protected void refreshBendpoints() {
        // Specific refresh for edge with path
        DEdge edge = getEdgeWithPath();
        if (edge == null) {
            if (getEdge().getBendpoints() instanceof RelativeBendpoints) {
                super.refreshBendpoints();
            }
        } else {
            DiagramEdgeEditPartOperation.refreshBendpointsWithPath(this, edge);
        }
    }

    /**
     * @return an edge if the edge of this edit part has path, null otherwise
     */
    private DEdge getEdgeWithPath() {
        final EObject semanticElement = resolveSemanticElement();
        if (semanticElement instanceof DEdge) {
            final DEdge edge = (DEdge) semanticElement;
            if (edge.getPath() != null && !edge.getPath().isEmpty()) {
                return edge;
            }
        }
        return null;
    }

    @Override
    public void refreshForegroundColor() {
        // We don't change the foregroundColor to keep the selected color.
        if (!DiagramEdgeEditPartOperation.isSelected(this) && !DiagramEdgeEditPartOperation.isLabelSelected(this)) {
            super.refreshForegroundColor();
            DiagramEdgeEditPartOperation.refreshForegroundColor(this);
        }
    }

    @Override
    public void refreshSourceDecoration() {
        DiagramEdgeEditPartOperation.refreshSourceDecoration(this);
    }

    @Override
    public void refreshTargetDecoration() {
        DiagramEdgeEditPartOperation.refreshTargetDecoration(this);
    }

    @Override
    public void refreshLineStyle() {
        DiagramEdgeEditPartOperation.refreshLineStyle(this);
    }

    @Override
    public NotificationListener getEAdapterDiagramElement() {
        if (this.adapterDiagramElement == null) {
            this.adapterDiagramElement = DiagramElementEditPartOperation.createEApdaterDiagramElement(this);
        }
        return this.adapterDiagramElement;
    }

    @Override
    public NotificationListener getEditModeListener() {
        return this.editModeListener;
    }

    @Override
    public NotificationPreCommitListener getEAdapterRoutingStyle() {
        if (this.adapterRoutingStyle == null) {
            this.adapterRoutingStyle = DiagramEdgeEditPartOperation.createEAdapterRoutingStyle(this);
        }
        return this.adapterRoutingStyle;
    }

    @Override
    public EditPartAuthorityListener getEditPartAuthorityListener() {
        return this.authListener;
    }

    @Override
    public Class<?> getMetamodelType() {
        return DEdge.class;
    }

    @Override
    public IStyleEditPart getStyleEditPart() {
        return DiagramElementEditPartOperation.getStyleEditPart(this);
    }

    @Override
    public List<EObject> resolveAllSemanticElements() {
        return DiagramElementEditPartOperation.resolveAllSemanticElements(this);
    }

    @Override
    public DDiagramElement resolveDiagramElement() {
        return DiagramElementEditPartOperation.resolveDiagramElement(this);
    }

    @Override
    public EObject resolveTargetSemanticElement() {
        return DiagramElementEditPartOperation.resolveTargetSemanticElement(this);
    }

    @Override
    public void routingStyleChanged(final Notification message) {
        DiagramEdgeEditPartOperation.routingStyleChanged(this, message);
    }

    @Override
    public void activate() {
        if (!isActive()) {
            /*
             * Fix bad behavior with hidden Edges while deleting ports.
             */
            final EObject element = resolveSemanticElement();
            if (element instanceof DEdge) {
                super.activate();
                DiagramElementEditPartOperation.activate(this);
                DiagramEdgeEditPartOperation.activate(this);
            }
        }
        installRouter();
    }

    @Override
    public void deactivate() {
        if (isActive()) {
            removeNotationalListeners();
            DiagramEdgeEditPartOperation.deactivate(this);
            DiagramElementEditPartOperation.deactivate(this);
            super.deactivate();
        }
    }

    @Override
    public void enableEditMode() {
        /*
         * We want to be sure nobody is enabling the edit mode if the element is locked.
         */
        if (!authListener.isLocked()) {
            super.enableEditMode();
        }
    }

    @Override
    protected void installRouter() {
        final EObject element = this.resolveSemanticElement();
        if (element instanceof DEdge) {
            final DEdge edge = (DEdge) element;
            if (edge.getPath() != null && !edge.getPath().isEmpty()) {
                this.getPolylineConnectionFigure().setConnectionRouter(ROUTER);
            } else {
                super.installRouter();
            }
        }
    }

    @Override
    protected void refreshRoutingStyles() {
        final EObject element = resolveSemanticElement();
        if (element instanceof DEdge) {
            super.refreshRoutingStyles();
        }
    }

    @Override
    protected void refreshRouterChange() {
        final EObject element = resolveSemanticElement();
        if (element instanceof DEdge) {
            super.refreshRouterChange();
        }
    }

    @Override
    public Image getLabelIcon() {
        return DiagramElementEditPartOperation.getLabelIcon(this);
    }

    @Override
    protected Connection createConnectionFigure() {
        return new ViewEdgeFigure();
    }

    /**
     * The figure.
     */
    public class ViewEdgeFigure extends SiriusPolylineConnectionEx implements ITreeConnection, IFigureWithoutLabels {
        /**
         * Label figure that allows to set the model {@link View}. These labels are created before label edit parts are
         * created so we need to be able to attached the right view after.
         * 
         * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
         *
         */
        public final class SiriusWrapLabelWithAttachmentWithModel extends SiriusWrapLabelWithAttachment {
            private View model;

            private SiriusWrapLabelWithAttachmentWithModel(int location, Polyline attachment) {
                super(location, attachment);
            }

            public void setModel(View model) {
                this.model = model;
            }

            @Override
            public void paint(Graphics graphics) {
                ShowingViewUtil.initGraphicsForVisibleAndInvisibleElements(this, graphics, model);
                try {
                    CommonEditPartOperation.setGraphicsTraceabilityId(graphics, () -> resolveTargetSemanticElement());
                    super.paint(graphics);
                    CommonEditPartOperation.setGraphicsTraceabilityId(graphics, null);
                    graphics.restoreState();
                } finally {
                    graphics.popState();
                }
            }
        }

        /**
         * @was-generated
         */
        private SiriusWrapLabelWithAttachment fFigureViewEdgeNameFigure;

        private SiriusWrapLabelWithAttachment fFigureViewEdgeBeginNameFigure;

        private SiriusWrapLabelWithAttachment fFigureViewEdgeEndNameFigure;

        private Polyline attachmentToEdgeNameFigure;

        private Polyline attachmentToEdgeBeginNameFigure;

        private Polyline attachmentToEdgeEndNameFigure;

        private CenteringStyle centeringStyle;

        /**
         * Constructor.
         */
        public ViewEdgeFigure() {
            createContents();
        }

        /**
         * @not-generated : we don't want to create the label
         */
        private void createContents() {
            final EObject element = resolveSemanticElement();

            // We must create the fFigureViewEdgeNameFigure to avoid NPE after
            // (On some cases, like Undo of ContainerDrop operation, the element
            // is DSemanticDiagram and not DEdge)
            createCenterLabelFigure(element);
            createBeginLabelFigure(element);
            createEndLabelFigure(element);
            initCentering(element);
        }

        private void initCentering(EObject element) {
            if (element instanceof DEdge) {
                EdgeStyle edgeStyle = ((DEdge) element).getOwnedStyle();
                if (edgeStyle != null) {
                    setCentering(edgeStyle.getCentered());
                }
            }

        }

        private void setCentering(CenteringStyle centering) {
            this.centeringStyle = centering;
        }

        /**
         * Get the centeringStyle value.
         * 
         * @return the {@link CenteringStyle}.
         */
        public CenteringStyle getCenteringStyle() {
            return this.centeringStyle;
        }

        /**
         * Returns whether the connection is centered on its source or not.
         * 
         * @return true if the connection is centered on its source or both. False otherwise.
         */
        public boolean isSourceCentered() {
            return CenteringStyle.BOTH == getCenteringStyle() || CenteringStyle.SOURCE == getCenteringStyle();
        }

        /**
         * Returns whether the connection is centered on its target or not.
         * 
         * @return true if the connection is centered on its target or both. False otherwise.
         */
        public boolean isTargetCentered() {
            return CenteringStyle.BOTH == getCenteringStyle() || CenteringStyle.TARGET == getCenteringStyle();
        }

        /**
         * @param element
         */
        private void createCenterLabelFigure(final EObject element) {
            attachmentToEdgeNameFigure = addNewAttachmentFigure();
            fFigureViewEdgeNameFigure = new SiriusWrapLabelWithAttachmentWithModel(LabelViewConstants.MIDDLE_LOCATION, attachmentToEdgeNameFigure);

            if (element instanceof DEdge) {
                DEdge edge = (DEdge) element;
                fFigureViewEdgeNameFigure.setText(edge.getName());
                fFigureViewEdgeNameFigure.setVisible(!StringUtil.isEmpty(edge.getName()));
            } else {
                fFigureViewEdgeNameFigure.setVisible(false);
            }
            fFigureViewEdgeNameFigure.setLabelAlignment(PositionConstants.CENTER);
            fFigureViewEdgeNameFigure.setTextWrap(true);
            fFigureViewEdgeNameFigure.setTextWrapAlignment(PositionConstants.CENTER);
            this.add(fFigureViewEdgeNameFigure);

        }

        /**
         * @param element
         */
        private void createBeginLabelFigure(final EObject element) {
            attachmentToEdgeBeginNameFigure = addNewAttachmentFigure();

            fFigureViewEdgeBeginNameFigure = new SiriusWrapLabelWithAttachmentWithModel(LabelViewConstants.SOURCE_LOCATION, attachmentToEdgeBeginNameFigure);
            if (element instanceof DEdge) {
                DEdge edge = (DEdge) element;
                fFigureViewEdgeBeginNameFigure.setText(edge.getBeginLabel());
                fFigureViewEdgeBeginNameFigure.setVisible(!StringUtil.isEmpty(edge.getBeginLabel()));
            } else {
                fFigureViewEdgeBeginNameFigure.setVisible(false);
            }
            fFigureViewEdgeBeginNameFigure.setLabelAlignment(PositionConstants.LEFT);
            fFigureViewEdgeBeginNameFigure.setTextWrap(true);
            fFigureViewEdgeBeginNameFigure.setTextWrapAlignment(PositionConstants.CENTER);
            this.add(fFigureViewEdgeBeginNameFigure);

        }

        /**
         * Create a new attachment figure, add it to the current figure and return it.
         * 
         * @return the new attachment figure.
         */
        private Polyline addNewAttachmentFigure() {
            Polyline newAttachment = new Polyline();
            newAttachment.setLineStyle(Graphics.LINE_DASHDOT);
            newAttachment.setForegroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_LIST_SELECTION));
            newAttachment.setVisible(false);
            this.add(newAttachment);
            return newAttachment;
        }

        /**
         * @param element
         */
        private void createEndLabelFigure(final EObject element) {
            attachmentToEdgeEndNameFigure = addNewAttachmentFigure();

            fFigureViewEdgeEndNameFigure = new SiriusWrapLabelWithAttachmentWithModel(LabelViewConstants.TARGET_LOCATION, attachmentToEdgeEndNameFigure);
            if (element instanceof DEdge) {
                DEdge edge = (DEdge) element;
                fFigureViewEdgeEndNameFigure.setText(edge.getEndLabel());
                fFigureViewEdgeEndNameFigure.setVisible(!StringUtil.isEmpty(edge.getEndLabel()));
            } else {
                fFigureViewEdgeEndNameFigure.setVisible(false);
            }
            fFigureViewEdgeEndNameFigure.setLabelAlignment(PositionConstants.CENTER);
            fFigureViewEdgeEndNameFigure.setTextWrap(true);
            fFigureViewEdgeEndNameFigure.setTextWrapAlignment(PositionConstants.CENTER);
            this.add(fFigureViewEdgeEndNameFigure);

        }

        @SuppressWarnings("deprecation")
        @Override
        public void layout() {
            if (!isActive()) {
                return;
            }
            final EObject element = resolveSemanticElement();
            if (element != null && DEdge.class.isInstance(element)) {
                final DEdge edge = (DEdge) element;
                if (edge.getParentDiagram() == null) {
                    return;
                }
                boolean needRefreshVisuals = false;
                if (edge.getPath() != null && !edge.getPath().isEmpty()) {
                    if (AbstractDiagramEdgeEditPart.invalidPath(AbstractDiagramEdgeEditPart.this, edge)) {
                        if (getSelected() != EditPart.SELECTED_PRIMARY) {
                            needRefreshVisuals = true;
                        }
                    }
                }
                if (needRefreshVisuals || edge.isIsMockEdge()) {
                    refreshVisuals();
                }

                if (this.getBounds() != null && getSource() != null && getTarget() != null) {
                    super.layout();
                }

                DDiagramElementQuery dDiagramElementQuery = new DDiagramElementQuery(edge);
                if (!edge.getParentDiagram().isIsInShowingMode()) {
                    boolean middleLabelVisibility = !dDiagramElementQuery.isLabelHidden(DEdgeNameEditPart.VISUAL_ID);
                    boolean endLabelVisibility = !dDiagramElementQuery.isLabelHidden(DEdgeEndNameEditPart.VISUAL_ID);
                    boolean beginLabelVisibility = !dDiagramElementQuery.isLabelHidden(DEdgeBeginNameEditPart.VISUAL_ID);
                    // Set label invisible if the label is empty
                    if (edge.getName() == null || StringUtil.isEmpty(edge.getName())) {
                        middleLabelVisibility = false;
                    }
                    if (edge.getEndLabel() == null || StringUtil.isEmpty(edge.getEndLabel())) {
                        endLabelVisibility = false;
                    }
                    if (edge.getBeginLabel() == null || StringUtil.isEmpty(edge.getBeginLabel())) {
                        beginLabelVisibility = false;
                    }
                    // Update label visibility
                    fFigureViewEdgeNameFigure.setVisible(middleLabelVisibility);
                    fFigureViewEdgeEndNameFigure.setVisible(endLabelVisibility);
                    fFigureViewEdgeBeginNameFigure.setVisible(beginLabelVisibility);
                } else {
                    if (edge.getName() != null && !StringUtil.isEmpty(edge.getName()) && !(dDiagramElementQuery.isLabelHidden(DEdgeNameEditPart.VISUAL_ID)) && !fFigureViewEdgeNameFigure.isVisible()) {
                        fFigureViewEdgeNameFigure.setVisible(true);
                    } else if (edge.getEndLabel() != null && !StringUtil.isEmpty(edge.getEndLabel()) && !(dDiagramElementQuery.isLabelHidden(DEdgeEndNameEditPart.VISUAL_ID))
                            && !fFigureViewEdgeEndNameFigure.isVisible()) {
                        fFigureViewEdgeEndNameFigure.setVisible(true);
                    } else if (edge.getBeginLabel() != null && !StringUtil.isEmpty(edge.getBeginLabel()) && !(dDiagramElementQuery.isLabelHidden(DEdgeBeginNameEditPart.VISUAL_ID))
                            && !fFigureViewEdgeBeginNameFigure.isVisible()) {
                        fFigureViewEdgeBeginNameFigure.setVisible(true);
                    }
                }
            }
        }

        @SuppressWarnings("deprecation")
        @Override
        public void paintFigure(final Graphics graphics) {
            if (!isActive()) {
                return;
            }
            final EObject element = resolveSemanticElement();
            if (element != null && DEdge.class.isInstance(element)) {
                final DEdge viewEdge = (DEdge) element;
                DDiagram diagram = viewEdge.getParentDiagram();
                if ((!viewEdge.isIsMockEdge() && viewEdge.isVisible()) || (diagram != null && diagram.isIsInShowingMode())) {
                    ShowingViewUtil.initGraphicsForVisibleAndInvisibleElements(this, graphics, (View) getModel());
                    try {
                        super.paintFigure(graphics);
                        graphics.restoreState();
                    } finally {
                        graphics.popState();
                    }
                }
            }
        }

        @Override
        protected void paintChildren(Graphics graphics) {
            if (!isActive()) {
                return;
            }
            final EObject element = resolveSemanticElement();
            if (element != null && DEdge.class.isInstance(element)) {
                final DEdge viewEdge = (DEdge) element;
                DEdgeQuery dEdgeQuery = new DEdgeQuery(viewEdge);
                Optional<DDiagram> diagram = dEdgeQuery.getDDiagram();
                if (viewEdge.isVisible() || (diagram.isPresent() && diagram.get().isIsInShowingMode())) {
                    super.paintChildren(graphics);
                }
            }
        }

        /**
         * Get the name figure.
         * 
         * @return the name figure
         */
        public SiriusWrapLabel getFigureViewEdgeNameFigure() {
            return fFigureViewEdgeNameFigure;
        }

        /**
         * Get the name figure.
         * 
         * @return the name figure
         */
        public SiriusWrapLabel getFigureViewBeginEdgeNameFigure() {
            return fFigureViewEdgeBeginNameFigure;
        }

        /**
         * Get the name figure.
         * 
         * @return the name figure
         */
        public SiriusWrapLabel getFigureViewEndEdgeNameFigure() {
            return fFigureViewEdgeEndNameFigure;
        }

        /**
         * Overridden to have public access to its method.
         * 
         * {@inheritDoc}
         */
        @Override
        public RotatableDecoration getSourceDecoration() {
            return super.getSourceDecoration();
        }

        /**
         * Overridden to have public access to its method.
         * 
         * {@inheritDoc}
         */
        @Override
        public RotatableDecoration getTargetDecoration() {
            return super.getTargetDecoration();
        }

        @Override
        public String getHint() {
            if (AbstractDiagramEdgeEditPart.this.getTarget() != null) {
                return AbstractDiagramEdgeEditPart.this.getTarget().toString();
            } else {
                return "base"; //$NON-NLS-1$
            }
        }

        @Override
        public Orientation getOrientation() {
            return Orientation.VERTICAL;
        }

        /**
         * Returns the edit part who owns this figure.
         * 
         * @return the owner edit part.
         */
        public IGraphicalEditPart getEditPart() {
            return AbstractDiagramEdgeEditPart.this;
        }

        @Override
        public Rectangle getBoundsWithoutLabels() {
            return getPoints().getBounds();
        }
    }

    private static boolean invalidPath(final AbstractDiagramEdgeEditPart editPart, final DEdge edge) {
        return true;
    }

    @Override
    public void performRequest(final Request request) {
        if (request instanceof DirectEditRequest || RequestConstants.REQ_DIRECT_EDIT.equals(request.getType())) {
            this.performDirectEditRequest(request);
        } else {
            super.performRequest(request);
        }
    }

    /**
     * {@inheritDoc} <BR>
     * Return the current edit part for CreateRequest with RequestDescription, SelectionWizardDescription or
     * PaneBasedSelectionWizardDescription as new object.<BR>
     * CreateUnspecifiedTypeConnectionRequest is used to create a NoteAtachment directly from the Note.
     * 
     * The returned edit part can be this edge one or the target or source's one if this edge part is over the nodes
     * ones.
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart#getTargetEditPart(org.eclipse.gef.Request)
     * 
     *      T
     */
    @Override
    public EditPart getTargetEditPart(final Request request) {
        EditPart result = null;
        AbstractToolDescription tool = null;
        // Attaching notes and robustness
        if (request instanceof CreateRequest && !(request instanceof CreateUnspecifiedTypeConnectionRequest) && ((CreateRequest) request).getNewObject() instanceof AbstractToolDescription) {
            tool = (AbstractToolDescription) ((CreateRequest) request).getNewObject();
        }

        if (tool instanceof RequestDescription || tool instanceof SelectionWizardDescription || tool instanceof PaneBasedSelectionWizardDescription) {
            return this;
        } else if (request instanceof SelectionRequest) {
            boolean expandSource = false;
            boolean expandTarget = false;
            Rectangle sourceBoundsWithMargin = null;
            Rectangle targetBoundsWithMargin = null;
            int horizontalSourceIncrement = 0;
            int verticalSourceIncrement = 0;
            int horizontalTargetIncrement = 0;
            int verticalTargetIncrement = 0;
            Rectangle sourceBounds = null;
            Rectangle targetBounds = null;

            // We compute the bounds of the source edit part with margins if it
            // is too small.
            if (getSource() instanceof AbstractBorderItemEditPart) {
                sourceBounds = GraphicalHelper.getAbsoluteBounds((IGraphicalEditPart) getSource());
                // The method used to add margins doubles the given horizontal
                // and vertical so we divide these values to always have the
                // same minimum selection box.
                horizontalSourceIncrement = SOURCE_TARGET_MINIMUM_SIZE_SELECTION > sourceBounds.width ? Math.round((SOURCE_TARGET_MINIMUM_SIZE_SELECTION - sourceBounds.width) / 2) : 0;
                verticalSourceIncrement = SOURCE_TARGET_MINIMUM_SIZE_SELECTION > sourceBounds.height ? Math.round((SOURCE_TARGET_MINIMUM_SIZE_SELECTION - sourceBounds.height) / 2) : 0;

                if (horizontalSourceIncrement > 0 || verticalSourceIncrement > 0) {
                    expandSource = true;
                    sourceBoundsWithMargin = sourceBounds.getExpanded(horizontalSourceIncrement, verticalSourceIncrement);
                } else {
                    sourceBoundsWithMargin = sourceBounds;
                }
            }
            // We compute the bounds of the target edit part with margins if it
            // is too small.
            if (getTarget() instanceof AbstractBorderItemEditPart) {
                targetBounds = GraphicalHelper.getAbsoluteBounds((IGraphicalEditPart) getTarget());
                horizontalTargetIncrement = SOURCE_TARGET_MINIMUM_SIZE_SELECTION > targetBounds.width ? Math.round((SOURCE_TARGET_MINIMUM_SIZE_SELECTION - targetBounds.width) / 2) : 0;
                verticalTargetIncrement = SOURCE_TARGET_MINIMUM_SIZE_SELECTION > targetBounds.height ? Math.round((SOURCE_TARGET_MINIMUM_SIZE_SELECTION - targetBounds.height) / 2) : 0;

                if (horizontalTargetIncrement > 0 || verticalTargetIncrement > 0) {
                    expandTarget = true;
                    targetBoundsWithMargin = targetBounds.getExpanded(horizontalTargetIncrement, verticalTargetIncrement);
                } else {
                    targetBoundsWithMargin = targetBounds;
                }

            }

            // We test if an edge selectable zone exists with the margined
            // source and target edit part. Margins can be zero if parts are big
            // enough.
            boolean isEdgeSelectableZonePresent = false;
            if (expandTarget || expandSource) {
                Rectangle edgeBounds = GraphicalHelper.getAbsoluteBounds(this);
                isEdgeSelectableZonePresent = isEdgeSelectableZonePresent(edgeBounds, sourceBoundsWithMargin, targetBoundsWithMargin);
            }

            Point location = ((SelectionRequest) request).getLocation();
            if (location != null) {
                boolean returnSource = isEdgeSelectableZonePresent && sourceBoundsWithMargin != null && sourceBoundsWithMargin.contains(location);
                returnSource = returnSource || (sourceBounds != null && sourceBounds.contains(location));
                if (returnSource) {
                    // The mouse is located in the margined source part and an
                    // edge selectable zone is present. So the considered
                    // selection is the source one.
                    result = getSource();
                } else if (result == null) {
                    boolean returnTarget = isEdgeSelectableZonePresent && targetBoundsWithMargin != null && targetBoundsWithMargin.contains(location);
                    returnTarget = returnTarget || (targetBounds != null && targetBounds.contains(location));
                    if (returnTarget) {
                        // The mouse is located in the margined target part and
                        // an edge selectable zone is present. So the considered
                        // selection is the target one.
                        result = getTarget();
                    }
                }
            }

        }
        if (result == null) {
            // The mouse is located in the edge bounds but not in the margined
            // source and target one so we return it.
            result = super.getTargetEditPart(request);
        }
        return result;
    }

    /**
     * Returns true if the edge represented by the given bounds has a zone with the minimum width or height allowing its
     * selection.
     * 
     * Either the edge has a box outside the one formed by the union of the nodes boxes that meets the minimum
     * requirement that is a thickness of {@link AbstractDiagramEdgeEditPart#EDGE_MINIMUM_THICKNESS}.
     * 
     * Or it does not. In this case we compute the edge length from its box by taking in consideration the margin
     * expansions of the nodes boxes. The computing is done based on the worst scenario of nodes occupation over the
     * edge. The computing depends on the existence of source and target bounds. If both exist, then the worst scenario
     * is the boxes of the two nodes are upon the edge's one. In this case, the edge's box is considered as selectable
     * if its bound width or height is greater than the minimum considered when removed from the width or height of the
     * margined nodes. If only one node exists, then the worst scenario is one node is completely over the edge. In this
     * case, the edge's box is considered as selectable if its width or height is greater than the minimum considered
     * when removed from the minimum width or height of the unique margined node.
     * 
     * @param edgeBounds
     *            the {@link Rectangle} encapsulating the selected edge.
     * @param sourceBoundsWithMargins
     *            the {@link Rectangle} encapsulating the source part of the selected edge.
     * @param targetBoundsWithMargins
     *            the {@link Rectangle} encapsulating the target part of the selected edge.
     * @return true if the edge represented by the given bounds has the minimum width or height allowing its selection.
     *         False otherwise.
     */
    private boolean isEdgeSelectableZonePresent(Rectangle edgeBounds, Rectangle sourceBoundsWithMargins, Rectangle targetBoundsWithMargins) {
        if (sourceBoundsWithMargins == null && targetBoundsWithMargins == null) {
            return true;
        } else {
            boolean isEdgeSelectableZonePresent = false;
            if (sourceBoundsWithMargins != null && targetBoundsWithMargins != null) {
                // We check that the edge is selectable on the edge boxes not in
                // the union of nodes boxes in all directions (top, bottom,
                // left, right).
                Rectangle sourceTargetUnionBox = sourceBoundsWithMargins.getUnion(targetBoundsWithMargins);
                // compute left box
                isEdgeSelectableZonePresent = isEdgeSelectableZonePresent || (sourceTargetUnionBox.x - edgeBounds.x > EDGE_MINIMUM_THICKNESS);

                // compute right box
                isEdgeSelectableZonePresent = isEdgeSelectableZonePresent || ((edgeBounds.x + edgeBounds.width) - (sourceTargetUnionBox.x + sourceTargetUnionBox.width) > EDGE_MINIMUM_THICKNESS);

                // compute top box
                isEdgeSelectableZonePresent = isEdgeSelectableZonePresent || (sourceTargetUnionBox.y - edgeBounds.y > EDGE_MINIMUM_THICKNESS);

                // compute bottom box
                isEdgeSelectableZonePresent = isEdgeSelectableZonePresent || ((edgeBounds.y + edgeBounds.height) - (sourceTargetUnionBox.y + sourceTargetUnionBox.height) > EDGE_MINIMUM_THICKNESS);

            }
            if (!isEdgeSelectableZonePresent) {
                // there are no edge boxes selectable outside of the box made of
                // the union of the nodes boxes. So we checks we have the
                // minimum width or height between the two nodes boxes with
                // maximum margins.
                isEdgeSelectableZonePresent = sourceBoundsWithMargins == null || targetBoundsWithMargins == null
                        ? edgeBounds.width - SOURCE_TARGET_MINIMUM_SIZE_SELECTION >= EDGE_MINIMUM_LENGTH || edgeBounds.height - SOURCE_TARGET_MINIMUM_SIZE_SELECTION > EDGE_MINIMUM_LENGTH
                        : edgeBounds.width - (SOURCE_TARGET_MINIMUM_SIZE_SELECTION * 2) >= EDGE_MINIMUM_LENGTH || edgeBounds.height - (SOURCE_TARGET_MINIMUM_SIZE_SELECTION * 2) >= EDGE_MINIMUM_LENGTH;
            }
            return isEdgeSelectableZonePresent;
        }
    }

    @Override
    public Object getAdapter(Class key) {
        if (key == SnapToHelper.class) {
            return SiriusSnapToHelperUtil.getSnapHelper((org.eclipse.gef.GraphicalEditPart) this.getSource());
        }
        return super.getAdapter(key);
    }

    /**
     * Get the routingStyle for which a listener is added in the DiagramEventBroker during the activation of this edit
     * part.
     * 
     * @return the routingStyle for which a listener is added in the DiagramEventBroker during the activation
     */
    public RoutingStyle getRoutingStyle() {
        return routingStyle;
    }

    /**
     * Set the routing style for which a listener is added in the DiagramEventBroker during the activation of this edit
     * part.<BR>
     * Warning: This method is not supposed to be used elsewhere of the scope of
     * {@link AbstractDiagramEdgeEditPart#activate()} method.
     * 
     * @param activatedRoutingStyle
     *            the routingStyle to set
     */
    public void setRoutingStyle(RoutingStyle activatedRoutingStyle) {
        this.routingStyle = activatedRoutingStyle;
    }

    /**
     * Get the listener added in the DiagramEventBroker for the above routingStyle during the activation of this edit
     * part.
     * 
     * @return the listener added in the DiagramEventBroker for the above routingStyle
     */
    public NotificationPreCommitListener getRoutingStyleNotificationPreCommitListener() {
        return routingStyleNotificationPreCommitListener;
    }

    /**
     * Set the listener added in the DiagramEventBroker for the above routingStyle during the activation of this edit
     * part.<BR>
     * Warning: This method is not supposed to be used elsewhere of the scope of
     * {@link AbstractDiagramEdgeEditPart#activate()} method.
     * 
     * @param activatedRoutingStyleNotificationPreCommitListener
     *            the activatedRoutingStyleNotificationPreCommitListener to set
     */
    public void setRoutingStyleNotificationPreCommitListener(NotificationPreCommitListener activatedRoutingStyleNotificationPreCommitListener) {
        this.routingStyleNotificationPreCommitListener = activatedRoutingStyleNotificationPreCommitListener;
    }
}
