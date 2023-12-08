/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.business.api.query.DNodeQuery;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.SiriusDBorderedNodeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;

/**
 * Displays nodes whose Size Computation Expression is set to "-1". These nodes must be resized according to their label
 * size.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public class AutoSizeNodeLayout implements LayoutManager {

    /**
     * A margin of 20 around the label.
     */
    private static final Dimension LABEL_MARGIN = new Dimension(20, 20);

    /**
     * The associated {@link DNode}.
     */
    private DNode dNode;

    /**
     * The main figure to resize, which is usually a {@link SiriusDBorderedNodeFigure}.
     */
    private IFigure mainFigure;

    /**
     * The {@link SiriusWrapLabel} used to get the size of the label.
     */
    private SiriusWrapLabel nodeLabel;

    /**
     * The default {@link LayoutManager}.
     */
    private LayoutManager wrappedLayoutManager;

    /**
     * The associated GMF {@link Node}.
     */
    private Node node;

    /**
     * Creates a new instance of {@link AutoSizeNodeLayout}.
     * 
     * @param wrappedLayoutManager
     *            the {@link LayoutManager} to use if the node should not be autosized.
     * @param node
     *            the associated GMF {@link Node}.
     * @param dnode
     *            the associated {@link DNode}.
     * @param mainFigure
     *            the main figure to resize, which is usually a {@link SiriusDBorderedNodeFigure}.
     * @param nodeLabel
     *            the {@link SiriusWrapLabel} used to get the size of the label.
     */
    public AutoSizeNodeLayout(LayoutManager wrappedLayoutManager, Node node, DNode dnode, IFigure mainFigure, SiriusWrapLabel nodeLabel) {
        this.wrappedLayoutManager = wrappedLayoutManager;
        this.node = node;
        this.dNode = dnode;
        this.mainFigure = mainFigure;
        this.nodeLabel = nodeLabel;
    }

    /**
     * {@inheritDoc} This method has been overridden to handle Size Computation Expression = "-1". In this case, if the
     * node has not been manually resized, the figure size should be resized automatically, depending the size of the
     * label.
     */
    @Override
    public void layout(IFigure parent) {
        if (node != null && dNode.getStyle().getDescription() instanceof SquareDescription) {
            Bounds bounds = (Bounds) this.node.getLayoutConstraint();
            boolean hasDefaultBounds = bounds.getHeight() == -1 || bounds.getWidth() == -1;
            if (new org.eclipse.sirius.diagram.ui.business.internal.query.DNodeQuery(this.dNode).isAutoSizeStyle() || hasDefaultBounds) {
                DNodeQuery dNodeQuery = new DNodeQuery(this.dNode);
                Dimension autoSizedDimension = getAutoSizedDimension(parent);
                Dimension defaultDimension = new org.eclipse.sirius.diagram.ui.business.internal.query.DNodeQuery(this.dNode).getDefaultDimension();
                Dimension newSize = autoSizedDimension.getCopy();
                if (!new org.eclipse.sirius.diagram.ui.business.internal.query.DNodeQuery(this.dNode).isAutoSizeStyle()) {
                    // It may happens if the style is updated before the figure (i.e. with conditional styles), it was
                    // in auto-size mode before: Size Computation Expression and GMF Bounds were set to -1, but now it's
                    // not in auto-size mode and we should set the defaultDimension
                    newSize = defaultDimension.getCopy();
                }
                if (!hasDefaultBounds) {
                    // If the node has been manually resized, we should not change its bounds.
                    newSize = new Dimension(bounds.getWidth(), bounds.getHeight());
                }
                if (hasDefaultBounds && dNode.getStyle() instanceof Square square && square.getWidth() != null && square.getWidth().intValue() > 0) {
                    // Handle specific case Width Expression of the Square Style.
                    newSize.setWidth(defaultDimension.width);
                }
                if (hasDefaultBounds && dNode.getStyle() instanceof Square square && square.getHeight() != null && square.getHeight().intValue() > 0) {
                    // Handle specific case Height Expression of the Square Style.
                    newSize.setHeight(defaultDimension.height);
                }

                if (bounds.getHeight() == -1 && bounds.getWidth() != -1 && dNodeQuery.allowsHorizontalResize()) {
                    // Horizontal resizing case.
                    newSize.setWidth(bounds.getWidth());
                }
                if (bounds.getHeight() != -1 && bounds.getWidth() == -1 && dNodeQuery.allowsVerticalResize()) {
                    // Vertical resizing case.
                    newSize.setHeight(bounds.getHeight());
                }
                parent.setSize(newSize);
                this.mainFigure.setSize(newSize);
                this.nodeLabel.setSize(newSize);
            }
        }
        this.wrappedLayoutManager.layout(parent);
    }

    private Dimension getAutoSizedDimension(IFigure parent) {
        Dimension autoSizedDimension;
        StyleConfiguration styleConfiguration = IStyleConfigurationRegistry.INSTANCE.getStyleConfiguration(dNode.getDiagramElementMapping(), dNode.getStyle());
        if (parent instanceof DefaultSizeNodeFigure defaultSizeNodeFigure && styleConfiguration != null) {
            autoSizedDimension = styleConfiguration.fitToText(this.dNode, this.nodeLabel, defaultSizeNodeFigure);
        } else {
            autoSizedDimension = this.nodeLabel.getPreferredSize().getCopy();
            autoSizedDimension.expand(LABEL_MARGIN);
        }
        return autoSizedDimension;
    }

    @Override
    public Object getConstraint(IFigure child) {
        return this.wrappedLayoutManager.getConstraint(child);
    }

    @Override
    public Dimension getMinimumSize(IFigure container, int wHint, int hHint) {
        return this.wrappedLayoutManager.getMinimumSize(container, wHint, hHint);
    }

    @Override
    public Dimension getPreferredSize(IFigure container, int wHint, int hHint) {
        return this.wrappedLayoutManager.getPreferredSize(container, wHint, hHint);
    }

    @Override
    public void invalidate() {
        this.wrappedLayoutManager.invalidate();
    }

    @Override
    public void remove(IFigure child) {
        this.wrappedLayoutManager.remove(child);
    }

    @Override
    public void setConstraint(IFigure child, Object constraint) {
        this.wrappedLayoutManager.setConstraint(child, constraint);
    }
}
