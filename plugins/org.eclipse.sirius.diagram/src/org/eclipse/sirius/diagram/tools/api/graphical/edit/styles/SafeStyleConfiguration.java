/*******************************************************************************
 * Copyright (c) 2009, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.api.graphical.edit.styles;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.internal.query.StyleConfigurationQuery;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.diagram.ui.tools.api.figure.anchor.AnchorProvider;
import org.eclipse.swt.graphics.Image;

/**
 * A safe style configuration to preserve the integrity of designer.
 * 
 * @author ymortier
 */
public class SafeStyleConfiguration implements StyleConfiguration {

    /** The default style configuration. */
    private static final StyleConfiguration DEFAULT = new SimpleStyleConfiguration();

    /** The delegated style configuration. */
    private StyleConfiguration delegated;

    /**
     * Creates a new {@link SafeStyleConfiguration}.
     * 
     * @param delegated
     *            the delegated configuration.
     */
    public SafeStyleConfiguration(final StyleConfiguration delegated) {
        this.delegated = delegated == null ? DEFAULT : delegated;
    }

    // CHECKSTYLE:OFF

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration#adaptNodeLabel(org.eclipse.sirius.viewpoint.DNode,
     *      org.eclipse.sirius.common.ui.tools.api.draw2d.ui.figures.SiriusWrapLabel)
     */
    public void adaptNodeLabel(final DNode node, final SiriusWrapLabel nodeLabel) {
        try {
            this.delegated.adaptNodeLabel(node, nodeLabel);
        } catch (final Exception e) {
            log(e);
            DEFAULT.adaptNodeLabel(node, nodeLabel);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration#adaptViewNodeSizeWithLabel(org.eclipse.sirius.viewpoint.DNode,
     *      org.eclipse.sirius.common.ui.tools.api.draw2d.ui.figures.SiriusWrapLabel,
     *      int)
     */
    public int adaptViewNodeSizeWithLabel(final DNode viewNode, final SiriusWrapLabel nodeLabel, final int nodeWidth) {
        try {
            return this.delegated.adaptViewNodeSizeWithLabel(viewNode, nodeLabel, nodeWidth);
        } catch (final Exception e) {
            log(e);
            return DEFAULT.adaptViewNodeSizeWithLabel(viewNode, nodeLabel, nodeWidth);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration#fitToText(org.eclipse.sirius.viewpoint.DNode,
     *      org.eclipse.sirius.common.ui.tools.api.draw2d.ui.figures.SiriusWrapLabel,
     *      org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure)
     */
    public Dimension fitToText(DNode node, SiriusWrapLabel nodeLabel, DefaultSizeNodeFigure defaultSizeNodeFigure) {
        try {
            return this.delegated.fitToText(node, nodeLabel, defaultSizeNodeFigure);
        } catch (final Exception e) {
            log(e);
            return DEFAULT.fitToText(node, nodeLabel, defaultSizeNodeFigure);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration#getAnchorProvider()
     */
    public AnchorProvider getAnchorProvider() {
        try {
            return this.delegated.getAnchorProvider();
        } catch (final Exception e) {
            log(e);
            return DEFAULT.getAnchorProvider();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration#getBorderItemLocatorProvider()
     */
    public BorderItemLocatorProvider getBorderItemLocatorProvider() {
        try {
            return this.delegated.getBorderItemLocatorProvider();
        } catch (final Exception e) {
            log(e);
            return DEFAULT.getBorderItemLocatorProvider();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration#getLabelIcon(DDiagramElement,
     *      IGraphicalEditPart)
     */
    public Image getLabelIcon(DDiagramElement vpElement, IGraphicalEditPart editPart) {
        try {
            return new StyleConfigurationQuery(this.delegated).getLabelIcon(vpElement, editPart);
        } catch (final Exception e) {
            log(e);
            return DEFAULT.getLabelIcon(vpElement, editPart);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration#getNameBorderItemLocator(DNode,
     *      IFigure)
     */
    public IBorderItemLocator getNameBorderItemLocator(DNode node, IFigure mainFigure) {
        try {
            return this.delegated.getNameBorderItemLocator(node, mainFigure);
        } catch (final Exception e) {
            log(e);
            return DEFAULT.getNameBorderItemLocator(node, mainFigure);
        }

    }

    // CHECKSTYLE:ON

    private void log(final Exception e) {
        SiriusDiagramEditorPlugin.getInstance().logError("Error while invoking custom style configuration : " + this.delegated.getClass().getName(), e);
    }

}
