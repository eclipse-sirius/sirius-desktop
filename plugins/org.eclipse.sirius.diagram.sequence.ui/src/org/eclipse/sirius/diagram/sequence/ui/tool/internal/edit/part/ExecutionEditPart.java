/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.ExecutionSelectionEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure.ExecutionItemLocator;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramBorderNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.ext.draw2d.figure.ICollapseMode;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SequenceNodeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;
import org.eclipse.sirius.viewpoint.DStylizable;

/**
 * Special edit part for Executions. Implemented as bordered nodes, either directly on a lifeline parts or on another
 * execution.
 * 
 * @author pcdavid, smonnier
 */
public class ExecutionEditPart extends AbstractSequenceBorderedEditPart {
    /**
     * Constructor.
     * 
     * @param view
     *            the view.
     */
    public ExecutionEditPart(View view) {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditPolicy getPrimaryDragEditPolicy() {
        final ResizableEditPolicy result = new ExecutionSelectionEditPolicy();
        DDiagramElement dde = this.resolveDiagramElement();
        if (dde instanceof DNode) {
            DNode node = (DNode) dde;
            DiagramBorderNodeEditPartOperation.updateResizeKind(result, node);
        }
        return result;
    }

    /**
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
            result = new SequenceNodeFigure(getMapMode().DPtoLP(5), getMapMode().DPtoLP(5), anchorProvider);
            nodePlate = result;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IBorderItemLocator createBorderItemLocator(IFigure figure, DDiagramElement vpElementBorderItem) {
        if (AbstractNodeEvent.viewpointElementPredicate().apply(vpElementBorderItem)) {
            return new ExecutionItemLocator(this, figure);
        } else {
            return super.createBorderItemLocator(figure, vpElementBorderItem);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refreshVisuals() {
        super.refreshVisuals();
        collapsedExecutionRefreshVisuals();
    }

    private void collapsedExecutionRefreshVisuals() {
        DDiagramElement dde = resolveDiagramElement();
        IBorderItemLocator bil = getBorderItemLocator();

        if (dde == null || !(bil instanceof ExecutionItemLocator)) {
            return;
        }

        // Modify locator constraint to collapse only the width.
        if (new DDiagramElementQuery(dde).isIndirectlyCollapsed()) {
            final int x = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_X())).intValue();
            final int y = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_Y())).intValue();
            final int width = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Width())).intValue();
            final int height = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height())).intValue();

            bil.setConstraint(new Rectangle(x, y, width, height));

            if (bil instanceof BorderItemLocator) {
                ((BorderItemLocator) bil).setBorderItemOffset(ICollapseMode.COLLAPSE_DEFAULT_OFFSET);
            }

            if (getPrimaryFigure() != null) {
                refreshFigure();
                getPrimaryFigure().repaint();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISequenceEvent getISequenceEvent() {
        return ISequenceElementAccessor.getExecution(getNotationView()).get();
    }
}
