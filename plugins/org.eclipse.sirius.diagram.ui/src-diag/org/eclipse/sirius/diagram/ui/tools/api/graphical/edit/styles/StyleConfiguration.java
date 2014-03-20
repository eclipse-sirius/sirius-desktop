/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.diagram.ui.tools.api.figure.anchor.AnchorProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Configuration des styles.
 * 
 * @author ymortier
 */
public interface StyleConfiguration {

    /**
     * Adapt the node label.
     * 
     * @param node
     *            the node.
     * @param nodeLabel
     *            the node label.
     */
    void adaptNodeLabel(DNode node, SiriusWrapLabel nodeLabel);

    /**
     * Return the provider of {@link IBorderItemLocator}s.
     * 
     * @return the provider of {@link IBorderItemLocator}s.
     */
    BorderItemLocatorProvider getBorderItemLocatorProvider();

    /**
     * Return the locator of the name of the node.
     * 
     * @param node
     *            the node.
     * @param mainFigure
     *            the main figure.
     * @return the locator of the name of the node.
     */
    IBorderItemLocator getNameBorderItemLocator(DNode node, IFigure mainFigure);

    /**
     * Return the width/height to use according to the viewNode and the label.
     * 
     * @param viewNode
     *            the view node.
     * @param nodeLabel
     *            the label.
     * @param nodeWidth
     *            the node width.
     * @return the width/height to use according to the viewNode and the label.
     */
    int adaptViewNodeSizeWithLabel(DNode viewNode, SiriusWrapLabel nodeLabel, int nodeWidth);

    /**
     * Return the anchor provider of the style.
     * 
     * @return the anchor provider of the style.
     */
    AnchorProvider getAnchorProvider();

    /**
     * <p>
     * Return the icon of the specified {@link DDiagramElement}.
     * </p>
     * 
     * <p>
     * <b>Note : </b> this method should never be called directly. Please use
     * {@link org.eclipse.sirius.diagram.business.internal.query.StyleConfigurationQuery#getLabelIcon(DDiagramElement)}
     * instead.
     * </p>
     * 
     * @param vpElement
     *            the {@link DDiagramElement}.
     * @param editPart
     *            the edit part
     * @return the icon of the specified {@link DDiagramElement}.
     */
    Image getLabelIcon(DDiagramElement vpElement, IGraphicalEditPart editPart);

    /**
     * Fits the dimension of the node to text.
     * 
     * @param node
     *            the node.
     * @param nodeLabel
     *            the label.
     * @param defaultSizeNodeFigure
     *            the figure.
     * @return the dimension to use.
     */
    Dimension fitToText(DNode node, SiriusWrapLabel nodeLabel, DefaultSizeNodeFigure defaultSizeNodeFigure);
}
