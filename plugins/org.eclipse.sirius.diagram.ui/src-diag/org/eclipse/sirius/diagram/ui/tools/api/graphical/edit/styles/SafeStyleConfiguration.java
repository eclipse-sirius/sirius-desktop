/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles;

import java.text.MessageFormat;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.business.internal.query.StyleConfigurationQuery;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;
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
     *      org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.draw2d.ui.figures.SiriusWrapLabel)
     */
    @Override
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
     *      org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.draw2d.ui.figures.SiriusWrapLabel, int)
     */
    @Override
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
     *      org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.draw2d.ui.figures.SiriusWrapLabel,
     *      org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure)
     */
    @Override
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
    @Override
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
    @Override
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
    @Override
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
    @Override
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
        DiagramPlugin.getDefault().logError(MessageFormat.format(Messages.SafeStyleConfiguration_customStyleInvocationError, this.delegated.getClass().getName()), e);
    }

}
