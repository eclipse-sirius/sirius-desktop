/*******************************************************************************
 * Copyright (c) 2008, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.edit.api.part;

import java.util.List;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
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
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeConnectionRequest;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.ITreeConnection;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.description.tool.RequestDescription;
import org.eclipse.sirius.diagram.edit.internal.part.CommonEditPartOperation;
import org.eclipse.sirius.diagram.edit.internal.part.DiagramEdgeEditPartOperation;
import org.eclipse.sirius.diagram.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.edit.internal.part.EditStatusUpdater;
import org.eclipse.sirius.diagram.graphical.edit.policies.DEdgeSelectionFeedbackEditPolicy;
import org.eclipse.sirius.diagram.graphical.edit.policies.EdgeCreationEditPolicy;
import org.eclipse.sirius.diagram.graphical.edit.policies.LaunchToolEditPolicy;
import org.eclipse.sirius.diagram.graphical.edit.policies.SiriusPropertyHandlerEditPolicy;
import org.eclipse.sirius.diagram.tools.api.permission.EditPartAuthorityListener;
import org.eclipse.sirius.diagram.tools.api.requests.RequestConstants;
import org.eclipse.sirius.diagram.tools.internal.graphical.edit.policies.SiriusConnectionEndPointEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.diagram.ui.tools.api.policy.CompoundEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.internal.routers.SiriusBendpointConnectionRouter;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.swt.graphics.Image;

/**
 * Implementation of the default behaviors of edges.
 * 
 * @author ymortier
 */
public abstract class AbstractDiagramEdgeEditPart extends ConnectionNodeEditPart implements IDiagramEdgeEditPart {

    /**
     * Define the minimum selection box size for the source and target of the
     * edge (if source or target is bordered node), to fix the horizontal and
     * vertical increment around which the selection of this edge select the
     * source or the target and not the edge.
     */
    private static final int SOURCE_TARGET_MINIMUM_SIZE_SELECTION = 20;

    /** The path router. */
    private static final BendpointConnectionRouter ROUTER = new SiriusBendpointConnectionRouter();

    /** The authority listener. */
    protected EditPartAuthorityListener authListener = new EditPartAuthorityListener(this);

    /** Listens the diagram element. */
    private NotificationListener adapterDiagramElement;

    /** Listens the semantic elements. */
    private NotificationPreCommitListener adapterSemanticElements;

    /** Listens the routing style. */
    private NotificationPreCommitListener adapterRoutingStyle;

    /** Listens the stroke color. */
    private NotificationPreCommitListener adapterStrokeColor;

    /** listen to semantic elements container */
    private NotificationListener editModeListener = new EditStatusUpdater(this);

    /**
     * Creates a new <code>AbstractDiagramEdgeEditPart</code>.
     * 
     * @param view
     *            the GMF view.
     */
    public AbstractDiagramEdgeEditPart(final View view) {
        super(view);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#registerModel()
     */
    @Override
    protected void registerModel() {
        super.registerModel();
        DiagramElementEditPartOperation.registerModel(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#unregisterModel()
     */
    @Override
    protected void unregisterModel() {
        super.unregisterModel();
        DiagramElementEditPartOperation.unregisterModel(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart#createDefaultEditPolicies()
     */
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

        // Enable Specific tools (SelectionWizard, PaneBasedSelectionWizard,
        // RequestTool)
        final CompoundEditPolicy compoundEditPolicy = new CompoundEditPolicy();
        compoundEditPolicy.addEditPolicy(new EdgeCreationEditPolicy());
        if (getEditPolicy(EditPolicy.CONNECTION_ROLE) != null) {
            compoundEditPolicy.addEditPolicy(getEditPolicy(EditPolicy.CONNECTION_ROLE));
            removeEditPolicy(EditPolicy.CONNECTION_ROLE);
        }
        installEditPolicy(EditPolicy.CONNECTION_ROLE, compoundEditPolicy);
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command getCommand(final Request request) {
        final Command cmd = super.getCommand(request);
        return CommonEditPartOperation.handleAutoPinOnInteractiveMove(this, request, cmd);
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     * Tests that the semantic model is an Edge. Refresh the edit part if the
     * event is :
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
        if (resolveSemanticElement() instanceof DEdge) {
            super.handleNotificationEvent(notification);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramEdgeEditPart#getPolylineConnectionFigure()
     */
    public PolylineConnectionEx getPolylineConnectionFigure() {
        final Connection connection = this.getConnectionFigure();
        if (connection instanceof PolylineConnectionEx) {
            return (PolylineConnectionEx) connection;
        }
        throw new IllegalStateException();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart#refreshVisuals()
     */
    @Override
    public void refreshVisuals() {
        super.refreshVisuals();
        DiagramEdgeEditPartOperation.refreshVisuals(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void refreshFont() {
        super.refreshFont();
        DiagramEdgeEditPartOperation.refreshFont(this);
    }

    /**
     * {@inheritDoc} Replace the original refreshBendpoints to compute the
     * bendpoints of the edge with path.
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart#refreshBendpoints()
     */
    @Override
    protected void refreshBendpoints() {
        // Specific refresh for edge with path
        DEdge edge = getEdgeWithPath();
        if (edge == null) {
            super.refreshBendpoints();
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart#refreshForegroundColor()
     */
    @Override
    public void refreshForegroundColor() {
        // We don't change the foregroundColor to keep the selected color.
        if (!DiagramEdgeEditPartOperation.isSelected(this) && !DiagramEdgeEditPartOperation.isLabelSelected(this)) {
            super.refreshForegroundColor();
            DiagramEdgeEditPartOperation.refreshForegroundColor(this);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramEdgeEditPart#refreshSourceDecoration()
     */
    public void refreshSourceDecoration() {
        DiagramEdgeEditPartOperation.refreshSourceDecoration(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramEdgeEditPart#refreshTargetDecoration()
     */
    public void refreshTargetDecoration() {
        DiagramEdgeEditPartOperation.refreshTargetDecoration(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramEdgeEditPart#refreshLineStyle()
     */
    public void refreshLineStyle() {
        DiagramEdgeEditPartOperation.refreshLineStyle(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#getEAdapterDiagramElement()
     */
    public NotificationListener getEAdapterDiagramElement() {
        if (this.adapterDiagramElement == null) {
            this.adapterDiagramElement = DiagramElementEditPartOperation.createEApdaterDiagramElement(this);
        }
        return this.adapterDiagramElement;
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

    /**
     * {@inheritDoc}
     */
    public NotificationListener getEditModeListener() {
        return this.editModeListener;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramEdgeEditPart#getEAdapterRoutingStyle()
     */
    public NotificationPreCommitListener getEAdapterRoutingStyle() {
        if (this.adapterRoutingStyle == null) {
            this.adapterRoutingStyle = DiagramEdgeEditPartOperation.createEAdapterRoutingStyle(this);
        }
        return this.adapterRoutingStyle;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramEdgeEditPart#getEAdapterStrokeColor()
     */
    public NotificationPreCommitListener getEAdapterStrokeColor() {
        if (this.adapterStrokeColor == null) {
            this.adapterStrokeColor = DiagramEdgeEditPartOperation.createEAdapterStrokeColor(this);
        }
        return this.adapterStrokeColor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#getEditPartAuthorityListener()
     */
    public EditPartAuthorityListener getEditPartAuthorityListener() {
        return this.authListener;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#getMetamodelType()
     */
    public Class<?> getMetamodelType() {
        return DEdge.class;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#getStyleEditPart()
     */
    public IStyleEditPart getStyleEditPart() {
        return DiagramElementEditPartOperation.getStyleEditPart(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#resolveAllSemanticElements()
     */
    public List<EObject> resolveAllSemanticElements() {
        return DiagramElementEditPartOperation.resolveAllSemanticElements(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#resolveDiagramElement()
     */
    public DDiagramElement resolveDiagramElement() {
        return DiagramElementEditPartOperation.resolveDiagramElement(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#resolveTargetSemanticElement()
     */
    public EObject resolveTargetSemanticElement() {
        return DiagramElementEditPartOperation.resolveTargetSemanticElement(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramEdgeEditPart#routingStyleChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void routingStyleChanged(final Notification message) {
        DiagramEdgeEditPartOperation.routingStyleChanged(this, message);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart#activate()
     */
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart#deactivate()
     */
    @Override
    public void deactivate() {
        if (isActive()) {
            DiagramEdgeEditPartOperation.deactivate(this);
            DiagramElementEditPartOperation.deactivate(this);
            super.deactivate();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart#enableEditMode()
     */
    @Override
    public void enableEditMode() {
        /*
         * We want to be sure nobody is enabling the edit mode if the element is
         * locked.
         */
        if (!authListener.isLocked()) {
            super.enableEditMode();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart#installRouter()
     */
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart#refreshRoutingStyles()
     */
    @Override
    protected void refreshRoutingStyles() {
        final EObject element = resolveSemanticElement();
        if (element instanceof DEdge) {
            super.refreshRoutingStyles();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart#refreshRouterChange()
     */
    @Override
    protected void refreshRouterChange() {
        final EObject element = resolveSemanticElement();
        if (element instanceof DEdge) {
            super.refreshRouterChange();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#getLabelIcon()
     */
    public Image getLabelIcon() {
        return DiagramElementEditPartOperation.getLabelIcon(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart#createConnectionFigure()
     */
    @Override
    protected Connection createConnectionFigure() {
        return new ViewEdgeFigure();
    }

    /**
     * The figure.
     */
    public class ViewEdgeFigure extends PolylineConnectionEx implements ITreeConnection {
        /**
         * @was-generated
         */
        private SiriusWrapLabel fFigureViewEdgeNameFigure;

        private SiriusWrapLabel fFigureViewEdgeBeginNameFigure;

        private SiriusWrapLabel fFigureViewEdgeEndNameFigure;

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
        }

        /**
         * @param element
         */
        private void createCenterLabelFigure(final EObject element) {
            fFigureViewEdgeNameFigure = new SiriusWrapLabel();
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
            fFigureViewEdgeBeginNameFigure = new SiriusWrapLabel();
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
         * @param element
         */
        private void createEndLabelFigure(final EObject element) {
            fFigureViewEdgeEndNameFigure = new SiriusWrapLabel();
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

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.draw2d.PolylineConnection#layout()
         */
        @Override
        public void layout() {
            if (!isActive()) {
                return;
            }
            final EObject element = resolveSemanticElement();
            if (element != null && DEdge.class.isInstance(element)) {
                final DEdge edge = (DEdge) element;
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

                if (edge.getName() == null || StringUtil.isEmpty(edge.getName())) {
                    fFigureViewEdgeNameFigure.setVisible(false);
                }

                if (edge.getName() != null && !StringUtil.isEmpty(edge.getName()) && !(new DDiagramElementQuery(edge).isLabelHidden()) && !fFigureViewEdgeNameFigure.isVisible()) {
                    fFigureViewEdgeNameFigure.setVisible(true);
                }
                if (edge.getEndLabel() == null || StringUtil.isEmpty(edge.getEndLabel())) {
                    fFigureViewEdgeEndNameFigure.setVisible(false);
                }
                if (edge.getEndLabel() != null && !StringUtil.isEmpty(edge.getEndLabel()) && !(new DDiagramElementQuery(edge).isLabelHidden()) && !fFigureViewEdgeEndNameFigure.isVisible()) {
                    fFigureViewEdgeEndNameFigure.setVisible(true);
                }
                if (edge.getBeginLabel() == null || StringUtil.isEmpty(edge.getBeginLabel())) {
                    fFigureViewEdgeBeginNameFigure.setVisible(false);
                }
                if (edge.getBeginLabel() != null && !StringUtil.isEmpty(edge.getBeginLabel()) && !(new DDiagramElementQuery(edge).isLabelHidden()) && !fFigureViewEdgeBeginNameFigure.isVisible()) {
                    fFigureViewEdgeBeginNameFigure.setVisible(true);
                }
            }
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx#paintFigure(org.eclipse.draw2d.Graphics)
         */
        @Override
        public void paintFigure(final Graphics graphics) {
            final EObject element = resolveSemanticElement();
            if (element != null && DEdge.class.isInstance(element)) {
                final DEdge viewEdge = (DEdge) element;
                if (!viewEdge.isIsMockEdge() && viewEdge.isVisible()) {
                    super.paintFigure(graphics);
                }
            }
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.draw2d.Figure#paintChildren(org.eclipse.draw2d.Graphics)
         */
        @Override
        protected void paintChildren(Graphics graphics) {
            final EObject element = resolveSemanticElement();
            if (element != null && DEdge.class.isInstance(element)) {
                final DEdge viewEdge = (DEdge) element;
                if (viewEdge.isVisible()) {
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

        /**
         * {@inheritDoc}
         */
        public String getHint() {
            if (AbstractDiagramEdgeEditPart.this.getTarget() != null) {
                return AbstractDiagramEdgeEditPart.this.getTarget().toString();
            } else {
                return "base";
            }
        }

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
    }

    private static boolean invalidPath(final AbstractDiagramEdgeEditPart editPart, final DEdge edge) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
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
     * Return the current edit part for CreateRequest with RequestDescription,
     * SelectionWizardDescription or PaneBasedSelectionWizardDescription as new
     * object.<BR>
     * CreateUnspecifiedTypeConnectionRequest is used to create a NoteAtachment
     * directly from the Note.
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart#getTargetEditPart(org.eclipse.gef.Request)
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
            // if there is the source of the target near this edge select it.
            if (getSource() instanceof AbstractBorderItemEditPart) {
                final IFigure sourceFigure = ((AbstractBorderItemEditPart) getSource()).getFigure();
                final int horizontalIncrement = SOURCE_TARGET_MINIMUM_SIZE_SELECTION > sourceFigure.getSize().width ? SOURCE_TARGET_MINIMUM_SIZE_SELECTION - sourceFigure.getSize().width : 0;
                final int verticalIncrement = SOURCE_TARGET_MINIMUM_SIZE_SELECTION > sourceFigure.getSize().height ? SOURCE_TARGET_MINIMUM_SIZE_SELECTION - sourceFigure.getSize().height : 0;
                final Rectangle rectWithMarges = sourceFigure.getBounds().getExpanded(horizontalIncrement, verticalIncrement);
                sourceFigure.translateToAbsolute(rectWithMarges);
                // in very specific cases (Note attachment on specific
                // edges)
                // request.getLocation() can be null
                Point location = ((SelectionRequest) request).getLocation();
                if (location != null && rectWithMarges.contains(location)) {
                    result = getSource();
                }
            }
            if (result == null && getTarget() instanceof AbstractBorderItemEditPart) {
                final IFigure targetFigure = ((AbstractBorderItemEditPart) getTarget()).getFigure();
                final int horizontalIncrement = SOURCE_TARGET_MINIMUM_SIZE_SELECTION > targetFigure.getSize().width ? SOURCE_TARGET_MINIMUM_SIZE_SELECTION - targetFigure.getSize().width : 0;
                final int verticalIncrement = SOURCE_TARGET_MINIMUM_SIZE_SELECTION > targetFigure.getSize().height ? SOURCE_TARGET_MINIMUM_SIZE_SELECTION - targetFigure.getSize().height : 0;
                final Rectangle rectWithMarges = targetFigure.getBounds().getExpanded(horizontalIncrement, verticalIncrement);
                targetFigure.translateToAbsolute(rectWithMarges);
                // in very specific cases (Note attachment on specific
                // edges)
                // request.getLocation() can be null
                Point location = ((SelectionRequest) request).getLocation();
                if (location != null && rectWithMarges.contains(((SelectionRequest) request).getLocation())) {
                    result = getTarget();
                }
            }
        }
        if (result == null) {
            result = super.getTargetEditPart(request);
        }
        return result;
    }
}
