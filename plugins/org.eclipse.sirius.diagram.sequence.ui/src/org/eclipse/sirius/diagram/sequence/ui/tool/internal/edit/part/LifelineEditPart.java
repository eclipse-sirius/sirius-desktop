/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part;

import java.util.List;
import java.util.Optional;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.description.EndOfLifeMapping;
import org.eclipse.sirius.diagram.sequence.description.ExecutionMapping;
import org.eclipse.sirius.diagram.sequence.description.StateMapping;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure.ExecutionItemLocator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure.SouthCenteredBorderItemLocator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.LayoutEditPartConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.ui.SequenceDragEditPartsTrackerEx.SequenceCacheDragTrackerHelper;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramBorderNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.LifelineNodeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

/**
 * Special edit part for Executions. They are treated as bordered nodes.
 * 
 * @author pcdavid, smonnier
 */
public class LifelineEditPart extends AbstractSequenceBorderedEditPart {
    private static final boolean ENABLE_LIFELINE_SELECTION = false;

    /**
     * Constructor.
     * 
     * @param view
     *            the view.
     */
    public LifelineEditPart(final View view) {
        super(view);
    }

    /**
     * This method is overridden to have the Lifeline (bordered node) starting from the border of the Instance Role.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void refreshVisuals() {
        updateLifelineWidthAndColor();

        super.refreshVisuals();
        SequenceEditPartsOperations.setBorderItemLocation(this, PositionConstants.SOUTH, LayoutEditPartConstants.ROOT_EXECUTION_BORDER_ITEM_OFFSET);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditPolicy getPrimaryDragEditPolicy() {
        final ResizableEditPolicy result = new ResizableEditPolicy();
        DDiagramElement dde = this.resolveDiagramElement();
        if (dde instanceof DNode) {
            DNode node = (DNode) dde;
            DiagramBorderNodeEditPartOperation.updateResizeKind(result, node);
        }
        return result;
    }

    private void updateLifelineWidthAndColor() {
        DDiagramElement dde = this.resolveDiagramElement();
        Style style = dde.getStyle();
        if (style instanceof BorderedStyle) {
            BorderedStyle borderedStyle = (BorderedStyle) style;
            int borderSize = borderedStyle.getBorderSize().intValue();
            Color fg = VisualBindingManager.getDefault().getColorFromRGBValues(borderedStyle.getBorderColor());

            // Update LifelineNodeFigure
            nodePlate.setLineWidth(borderSize);
            if (fg != null) {
                nodePlate.setForegroundColor(fg);
            }

            // Update its border.
            if (nodePlate.getBorder() instanceof LineBorder) {
                LineBorder lineBorder = (LineBorder) nodePlate.getBorder();
                lineBorder.setWidth(borderSize);
                if (fg != null) {
                    lineBorder.setColor(fg);
                }
            }
        }
    }

    /**
     * This method has been overridden to be able to select the parent InstanceRoleEditPart when selecting this
     * LifelineEditPart.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void setSelected(int value) {
        if (ENABLE_LIFELINE_SELECTION) {
            super.setSelected(value);
        } else {
            getParent().setSelected(value);
        }
    }

    /**
     * This method has been overridden to use a specific Tracker to be able to select the InstanceRole and group
     * requests.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public DragTracker getDragTracker(Request request) {
        if (!ENABLE_LIFELINE_SELECTION) {
            if (request instanceof SelectionRequest) {
                return new LifeLineSelectionDragEditPartsTrackerEx(this);
            }
        }
        return super.getDragTracker(request);
    }

    /**
     * This method has been overridden to use a specific BorderItemLocator to place the Destroy end item properly.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public IBorderItemLocator createBorderItemLocator(IFigure figure, DDiagramElement vpElementBorderItem) {
        IBorderItemLocator result;
        RepresentationElementMapping mapping = vpElementBorderItem.getMapping();
        if (mapping instanceof EndOfLifeMapping) {
            result = new SouthCenteredBorderItemLocator(figure, LayoutEditPartConstants.EOL_BORDER_ITEM_OFFSET);
        } else if (mapping instanceof ExecutionMapping || mapping instanceof StateMapping) {
            result = new ExecutionItemLocator(this, figure);
        } else {
            result = super.createBorderItemLocator(figure, vpElementBorderItem);
        }
        return result;
    }

    /**
     * This method is overridden to use a specific figure for this border node.
     * 
     * {@inheritDoc}
     */
    @Override
    protected NodeFigure createNodePlate() {
        DefaultSizeNodeFigure result = null;
        final EObject eObj = resolveSemanticElement();
        if (eObj instanceof DStylizable && eObj instanceof DDiagramElement) {
            final DStylizable viewNode = (DStylizable) eObj;
            final StyleConfiguration styleConfiguration = IStyleConfigurationRegistry.INSTANCE.getStyleConfiguration(((DDiagramElement) eObj).getDiagramElementMapping(), viewNode.getStyle());
            final AnchorProvider anchorProvider = styleConfiguration.getAnchorProvider();
            result = new LifelineNodeFigure(getMapMode().DPtoLP(5), getMapMode().DPtoLP(5), anchorProvider);
            nodePlate = result;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISequenceEvent getISequenceEvent() {
        return ISequenceElementAccessor.getLifeline(getNotationView()).get();
    }

    @Override
    protected void handleNotificationEvent(Notification notification) {
        super.handleNotificationEvent(notification);
        if (notification.getFeature().equals(NotationPackage.eINSTANCE.getSize_Height())) {
            // Get lifeline resize from notification
            int newHeight = notification.getNewIntValue();
            // Get lifeline location/dimension
            Rectangle lifelineBounds = GraphicalHelper.getAbsoluteBounds(this);
            // Add End of life handle height
            int endOfLifeHeight = 0;
            Optional<EndOfLifeEditPart> optionalEndOfLifeEditPart = getChildren().stream().filter(EndOfLifeEditPart.class::isInstance).map(EndOfLifeEditPart.class::cast).findFirst();
            if (optionalEndOfLifeEditPart.isPresent()) {
                endOfLifeHeight = GraphicalHelper.getAbsoluteBounds(optionalEndOfLifeEditPart.get()).height;
            }
            // Add margin
            int margin = InteractionContainerEditPart.MARGIN;
            // Compute new bottom location for interaction container
            int newBottomLocation = lifelineBounds.y + newHeight + endOfLifeHeight + margin;
            // Get interaction container edit part (child of the diagram edit part)
            Optional<InteractionContainerEditPart> findFirst = this.getParent().getParent().getChildren().stream().filter(InteractionContainerEditPart.class::isInstance)
                    .map(InteractionContainerEditPart.class::cast).findFirst();
            if (findFirst.isPresent()) {
                InteractionContainerEditPart interactionContainerEditPart = findFirst.get();
                Rectangle interactionContainerBounds = GraphicalHelper.getAbsoluteBounds(findFirst.get());
                // Change Interaction Container size along with the end of lifeline resize
                interactionContainerEditPart.setSize(new Dimension(interactionContainerBounds.width, newBottomLocation));
            }
        }
    }

    /**
     * Specific tracker used to select the parent InstanceRole instead of the RootExecution and append request on
     * RootExecution to InstanceRole.
     * 
     * @author smonnier
     */
    private final class LifeLineSelectionDragEditPartsTrackerEx extends DragEditPartsTrackerEx {

        /**
         * Constructor.
         * 
         * @param sourceEditPart
         *            the execution edit part
         */
        LifeLineSelectionDragEditPartsTrackerEx(EditPart sourceEditPart) {
            super(sourceEditPart);
        }

        /**
         * This method has been overridden to be able to manipulate the parent InstanceRoleEditPart as well as this
         * LifelineEditPart. For instance, moving the LifelineEditPart will also move the parent InstanceRoleEditPart.
         * <p>
         * {@inheritDoc}
         */
        @Override
        @SuppressWarnings({ "unchecked", "rawtypes" })
        protected List createOperationSet() {
            List createOperationSet = super.createOperationSet();
            if (createOperationSet.contains(LifelineEditPart.this)) {
                if (getParent() instanceof InstanceRoleEditPart) {
                    createOperationSet.add(getParent());
                }
            }
            return createOperationSet;
        }

        /**
         * This method has been overridden to be able to select the parent InstanceRoleEditPart when selecting this
         * LifelineEditPart.
         * <p>
         * {@inheritDoc}
         */
        @Override
        protected void performSelection() {
            if (hasSelectionOccurred()) {
                return;
            }
            setFlag(FLAG_SELECTION_PERFORMED, true);
            EditPartViewer viewer = getCurrentViewer();
            List<?> selectedObjects = viewer.getSelectedEditParts();

            if (getCurrentInput().isModKeyDown(SWT.MOD1)) {
                if (selectedObjects.contains(getSourceEditPart().getParent())) {
                    viewer.deselect(getSourceEditPart());
                } else {
                    viewer.appendSelection(getSourceEditPart().getParent());
                }
            } else if (getCurrentInput().isShiftKeyDown()) {
                viewer.appendSelection(getSourceEditPart().getParent());
            } else {
                viewer.select(getSourceEditPart().getParent());
            }
        }

        /**
         * Always disable the clone with Ctrl key in Sirius because it only clone the graphical element and not the
         * semantic element.
         * 
         * @param cloneActive
         *            true if cloning should be active (never considered here)
         * 
         * @see org.eclipse.gef.tools.DragEditPartsTracker#setCloneActive(boolean)
         */
        @Override
        protected void setCloneActive(boolean cloneActive) {
            super.setCloneActive(false);
        }

        /**
         * (non-Javadoc)
         * 
         * @see org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx#handleButtonDown(int) {@inheritDoc}
         */
        @Override
        protected boolean handleButtonUp(int button) {
            SequenceCacheDragTrackerHelper.handleButtonUp((IGraphicalEditPart) getSourceEditPart());
            return super.handleButtonUp(button);
        }

        /**
         * (non-Javadoc)
         * 
         * @see org.eclipse.gef.tools.DragEditPartsTracker#handleButtonUp(int) {@inheritDoc}
         */
        @Override
        protected boolean handleButtonDown(int button) {
            boolean handleButtonDown = super.handleButtonDown(button);
            SequenceCacheDragTrackerHelper.handleButtonDown((IGraphicalEditPart) getSourceEditPart());
            return handleButtonDown;
        }
    }
}
