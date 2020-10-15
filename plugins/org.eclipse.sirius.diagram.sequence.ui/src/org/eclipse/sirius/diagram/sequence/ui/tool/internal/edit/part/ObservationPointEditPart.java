/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ObservationPoint;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ConnectionAnchorOperation;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.ObservationPointSelectionPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceLaunchToolEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.ui.SequenceNoCopyDragEditPartsTrackerEx;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramBorderNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.AirDefaultSizeNodeFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.anchor.AnchorProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.viewpoint.DStylizable;

/**
 * The edit part for {@link ObservationPoint}s.
 * 
 * @author mporhel
 */
public class ObservationPointEditPart extends DNodeEditPart {

    private DefaultSizeNodeFigure nodePlate;

    /**
     * Constructor.
     * 
     * @param view
     *            the view.
     */
    public ObservationPointEditPart(View view) {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addNotify() {
        SequenceEditPartsOperations.registerDiagramElement(this, resolveDiagramElement());
        super.addNotify();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeNotify() {
        super.removeNotify();
        SequenceEditPartsOperations.unregisterDiagramElement(this, resolveDiagramElement());
    }

    /**
     * Use the same y location as the corresponding source connection anchor, stored in ViewLocationHint, to improve
     * user feedback.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return ConnectionAnchorOperation.safeCenterAnchor(super.getSourceConnectionAnchor(request));
    }

    /**
     * Use the same y location as the corresponding source connection anchor, stored in ViewLocationHint, to improve
     * user feedback.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return ConnectionAnchorOperation.safeCenterAnchor(super.getTargetConnectionAnchor(request));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditPolicy getPrimaryDragEditPolicy() {
        ResizableEditPolicy result = new ObservationPointSelectionPolicy();
        DDiagramElement dde = resolveDiagramElement();
        if (dde instanceof DNode) {
            DNode node = (DNode) dde;
            DiagramNodeEditPartOperation.updateResizeKind(result, node);
        }
        return result;
    }

    /**
     * Overridden to install a specific edit policy managing the moving and resizing requests on lost message ends.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void installEditPolicy(Object key, EditPolicy editPolicy) {
        if (EditPolicy.PRIMARY_DRAG_ROLE.equals(key)) {
            super.installEditPolicy(key, getPrimaryDragEditPolicy());
        } else {
            super.installEditPolicy(key, editPolicy);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();

        // Handle $endBefore for launch tools.
        installEditPolicy(org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants.REQ_LAUNCH_TOOL, new SequenceLaunchToolEditPolicy());
    }

    public ObservationPoint getObservationPoint() {
        return ISequenceElementAccessor.getObservationPoint(getNotationView()).get();
    }

    @Override
    protected void handleNotificationEvent(Notification notification) {
        super.handleNotificationEvent(notification);

        if (DiagramPackage.eINSTANCE.getDDiagramElement_GraphicalFilters().equals(notification.getFeature())) {
            boolean collapse = notification.getNewValue() instanceof CollapseFilter && notification.getOldValue() == null;
            boolean uncollapse = notification.getOldValue() instanceof CollapseFilter && notification.getNewValue() == null;
            if (collapse || uncollapse) {
                DDiagramElement dde = resolveDiagramElement();
                if (dde instanceof DNode) {
                    DNode node = (DNode) dde;

                    EditPolicy ep = getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
                    if (ep instanceof ResizableEditPolicy) {
                        DiagramBorderNodeEditPartOperation.updateResizeKind((ResizableEditPolicy) ep, node);
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();

        DDiagramElement node = resolveDiagramElement();
        if (new DDiagramElementQuery(node).isCollapsed()) {
            final int x = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_X())).intValue();
            final int y = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_Y())).intValue();
            final int width = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Width())).intValue();
            final int height = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height())).intValue();

            Rectangle collpasedSize = new Rectangle(x, y, width, height);
            ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), collpasedSize);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.AbstractDiagramBorderNodeEditPart#activate()
     */
    @Override
    public void activate() {
        if (nodePlate instanceof AirDefaultSizeNodeFigure)
            ((AirDefaultSizeNodeFigure) nodePlate).setZoomManager(getZoomManager());
        super.activate();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.AbstractDiagramBorderNodeEditPart#deactivate()
     */
    @Override
    public void deactivate() {
        super.deactivate();
        if (nodePlate instanceof AirDefaultSizeNodeFigure)
            ((AirDefaultSizeNodeFigure) nodePlate).setZoomManager(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IFigure getNodePlate() {
        return nodePlate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected NodeFigure createNodePlate() {
        DefaultSizeNodeFigure result = new ObservationPointFigure(10, 10, null);
        final EObject eObj = resolveSemanticElement();
        if (eObj instanceof DStylizable && eObj instanceof DDiagramElement) {
            final DStylizable viewNode = (DStylizable) eObj;
            final StyleConfiguration styleConfiguration = IStyleConfigurationRegistry.INSTANCE.getStyleConfiguration(((DDiagramElement) eObj).getDiagramElementMapping(), viewNode.getStyle());
            final AnchorProvider anchorProvider = styleConfiguration.getAnchorProvider();
            result = new ObservationPointFigure(getMapMode().DPtoLP(5), getMapMode().DPtoLP(5), anchorProvider);
            nodePlate = result;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DragTracker getDragTracker(Request request) {
        return new SequenceNoCopyDragEditPartsTrackerEx(this);
    }

    /**
     * ObservationPoint is a top level node but collapsing should be possible.
     * 
     * @author mporhel
     */
    private class ObservationPointFigure extends AirDefaultSizeNodeFigure {
        /**
         * Create a new {@link AirDefaultSizeNodeFigure}.
         * 
         * @param defSize
         *            the size.
         * @param anchorProvider
         *            the anchor provider.
         */
        ObservationPointFigure(final Dimension defSize, final AnchorProvider anchorProvider) {
            super(defSize, anchorProvider);
        }

        /**
         * Create a new {@link AirDefaultSizeNodeFigure}.
         * 
         * @param width
         *            the width.
         * @param height
         *            the height.
         * @param anchorProvider
         *            the anchor provider.
         */
        ObservationPointFigure(final int width, final int height, final AnchorProvider anchorProvider) {
            super(width, height, anchorProvider);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Dimension getMinimumSize(int hint, int hint2) {
            return new Dimension(0, 0);
        }

    }

}
