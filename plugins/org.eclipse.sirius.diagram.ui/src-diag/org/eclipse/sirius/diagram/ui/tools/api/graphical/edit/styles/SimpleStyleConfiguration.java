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
package org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles;

import java.io.File;
import java.net.MalformedURLException;
import java.text.MessageFormat;

import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.diagram.ui.internal.figures.BorderItemContainerFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.business.api.query.DNodeQuery;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.GaugeCompositeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.swt.graphics.Image;

/**
 * A simple configuration. Do nothing.
 * 
 * @author ymortier
 */
@SuppressWarnings("restriction")
public class SimpleStyleConfiguration implements StyleConfiguration {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration#adaptNodeLabel(org.eclipse.sirius.viewpoint.DNode,
     *      org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.draw2d.ui.figures.SiriusWrapLabel)
     */
    @Override
    public void adaptNodeLabel(final DNode node, final SiriusWrapLabel nodeLabel) {
        if (nodeLabel.getParent() != null) {
            if (nodeLabel.getParent() instanceof BorderItemContainerFigure) {
                // The label is on border of its "parent" so the constraint of the parent is ignored.
                return;
            }
            final Rectangle constraint = new Rectangle(nodeLabel.getParent().getBounds());
            if (nodeLabel.getParent() instanceof GaugeCompositeFigure) {
                constraint.setX(0);
                constraint.setY(0);
            }

            final Insets borderDimension = this.getBorderDimension(node);
            constraint.setHeight(constraint.height - borderDimension.top + borderDimension.bottom);
            constraint.setWidth(constraint.width - borderDimension.left + borderDimension.right);
            constraint.setX(constraint.x + borderDimension.left);
            constraint.setY(constraint.y + borderDimension.top);

            nodeLabel.setBounds(constraint);
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
        if (viewNode.getResizeKind() != ResizeKind.NONE_LITERAL) {
            // empty
        }
        return nodeWidth;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration#getAnchorProvider()
     */
    @Override
    public AnchorProvider getAnchorProvider() {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration#getBorderItemLocatorProvider()
     */
    @Override
    public BorderItemLocatorProvider getBorderItemLocatorProvider() {
        return DefaultBorderItemLocatorProvider.getInstance();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration#getNameBorderItemLocator(org.eclipse.sirius.viewpoint.DNode,
     *      org.eclipse.draw2d.IFigure)
     */
    @Override
    public IBorderItemLocator getNameBorderItemLocator(final DNode node, final IFigure mainFigure) {
        final BorderItemLocator locator = new DBorderItemLocator(mainFigure, PositionConstants.NSEW);
        locator.setBorderItemOffset(IBorderItemOffsets.NO_OFFSET);
        return locator;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration#getLabelIcon(DDiagramElement,
     *      IGraphicalEditPart)
     */
    @Override
    public Image getLabelIcon(final DDiagramElement vpElement, IGraphicalEditPart editPart) {
        Image icon = null;

        if (vpElement != null) {
            if (isShowIcon(vpElement, editPart)) {
                ImageDescriptor descriptor = null;
                EObject target = vpElement.getTarget();

                if (useCustomIcon(vpElement, editPart)) {
                    descriptor = getCustomIconDescriptor(vpElement, editPart);
                } else if (target != null) {
                    final IItemLabelProvider labelProvider = (IItemLabelProvider) DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory().adapt(target, IItemLabelProvider.class);
                    if (labelProvider != null) {
                        descriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(labelProvider.getImage(target));
                    }
                }

                if (descriptor == null) {
                    descriptor = ImageDescriptor.getMissingImageDescriptor();
                }
                icon = DiagramUIPlugin.getPlugin().getImage(descriptor);
            }
        }
        return icon;
    }

    private ImageDescriptor getCustomIconDescriptor(DDiagramElement vpElement, IGraphicalEditPart editPart) {
        ImageDescriptor imgDesc = null;
        BasicLabelStyle bls = getBasicLabelStyle(vpElement, editPart);
        if (bls != null && !StringUtil.isEmpty(bls.getIconPath())) {
            String iconPath = bls.getIconPath();
            final File imageFile = FileProvider.getDefault().getFile(new Path(iconPath));
            if (imageFile != null && imageFile.exists() && imageFile.canRead()) {
                try {
                    imgDesc = DiagramUIPlugin.Implementation.findImageDescriptor(imageFile.toURI().toURL());
                } catch (MalformedURLException e) {
                    logIconNotFoundWarning(iconPath, e);
                }
            } else {
                logIconNotFoundWarning(iconPath, null);
            }
        }
        return imgDesc;
    }

    private void logIconNotFoundWarning(String iconPath, MalformedURLException e) {
        SiriusPlugin.getDefault().warning(MessageFormat.format(Messages.SimpleStyleConfiguration_iconFileNotFound, iconPath), e);
    }

    /**
     * Return <code>true</code> if the icon of the specified {@link DDiagramElement} is shown.
     * 
     * @param vpElement
     *            the {@link DDiagramElement}.
     * @param editPart
     *            the edit part
     * @return <code>true</code> if the icon of the specified {@link DDiagramElement} is shown.
     */
    protected boolean isShowIcon(final DDiagramElement vpElement, final IGraphicalEditPart editPart) {
        BasicLabelStyle bls = getBasicLabelStyle(vpElement, editPart);
        return bls != null && bls.isShowIcon();
    }

    /**
     * Return <code>true</code> if the current style defines a custom icon.
     * 
     * @param vpElement
     *            the {@link DDiagramElement}.
     * @param editPart
     *            the edit part
     * @return <code>true</code> if a custom icon is defined.
     */
    protected boolean useCustomIcon(DDiagramElement vpElement, IGraphicalEditPart editPart) {
        BasicLabelStyle bls = getBasicLabelStyle(vpElement, editPart);
        return bls != null && !StringUtil.isEmpty(bls.getIconPath());
    }

    private BasicLabelStyle getBasicLabelStyle(DDiagramElement vpElement, IGraphicalEditPart editPart) {
        BasicLabelStyle bls = null;
        if (vpElement.getStyle() instanceof LabelStyle) {
            bls = (LabelStyle) vpElement.getStyle();
        } else if (vpElement instanceof DEdge && vpElement.getStyle() instanceof EdgeStyle) {
            if (editPart instanceof DEdgeNameEditPart) {
                bls = ((EdgeStyle) vpElement.getStyle()).getCenterLabelStyle();
            } else if (editPart instanceof DEdgeBeginNameEditPart) {
                bls = ((EdgeStyle) vpElement.getStyle()).getBeginLabelStyle();
            } else if (editPart instanceof DEdgeEndNameEditPart) {
                bls = ((EdgeStyle) vpElement.getStyle()).getEndLabelStyle();
            }

        }
        return bls;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration#fitToText(org.eclipse.sirius.viewpoint.DNode,
     *      org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.draw2d.ui.figures.SiriusWrapLabel,
     *      org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure)
     */
    @Override
    public Dimension fitToText(final DNode node, final SiriusWrapLabel nodeLabel, final DefaultSizeNodeFigure defaultSizeNodeFigure) {
        if (nodeLabel.getFont() != null) {
            final String text = node.getName();

            final float charHeight = FigureUtilities.getStringExtents("ABCDEF", nodeLabel.getFont()).height + 5f; //$NON-NLS-1$
            final float charWidth = FigureUtilities.getTextWidth("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", nodeLabel.getFont()) / 52f; //$NON-NLS-1$

            final double ratio = charHeight / charWidth;

            final int nbLines = text != null ? (int) ((Math.sqrt(text.length()) / ratio)) + 1 : 0;
            int nbCols = text != null ? (int) ((Math.sqrt(text.length()) * ratio)) + 1 : 0;

            final int longestWord = text != null ? this.getTheLongestWord(text.split("\\s")) : 0; //$NON-NLS-1$
            nbCols = Math.max(nbCols, longestWord);

            final int hHeight = (int) (nbLines * charHeight);
            final int hWidth = (int) (nbCols * charWidth);

            int wHint = hWidth + nodeLabel.getIconBounds().width + nodeLabel.getIconTextGap();
            int hHint = hHeight;
            final Insets borderDimension = this.getBorderDimension(node);
            // 
            if (new DNodeQuery(node).isAutoSize()) {
                // In auto-size provide constraints from the computed size as preferred size.
                Dimension size = defaultSizeNodeFigure.getSize();
                //@formatter:off
                Dimension maximumSize = ReflectionHelper.getFieldValueWithoutException(defaultSizeNodeFigure, "maxSize") //$NON-NLS-1$
                        .filter(Dimension.class::isInstance)
                        .map(Dimension.class::cast)
                        .orElse(size);
                //@formatter:on
                if (maximumSize.width != -1) {
                    wHint = size.width - 20 - borderDimension.left - borderDimension.right;
                }
                if (maximumSize.height != -1) {
                    hHint = size.height - 20 - borderDimension.top - borderDimension.bottom;
                }
            }

            final Dimension size = nodeLabel.getPreferredSize(wHint, hHint).getCopy();

            size.setWidth(size.width + 20);
            size.setHeight(size.height + 30);

            size.setWidth(size.width + borderDimension.left + borderDimension.right);
            size.setHeight(size.height + borderDimension.top + borderDimension.bottom);
            return size;
        }
        return defaultSizeNodeFigure.getBounds().getSize().getCopy();
    }

    private int getTheLongestWord(final String[] strings) {
        int max = -1;
        for (String string : strings) {
            if (string.length() > max) {
                max = string.length();
            }
        }
        return max;
    }

    /**
     * Return the dimension of the border.
     * 
     * @param node
     *            the view node.
     * @return the dimension of the border.
     */
    public Insets getBorderDimension(final DNode node) {
        final Insets result = new Insets(0, 0, 0, 0);
        if (node.getStyle() instanceof BorderedStyle) {
            final BorderedStyle borderedStyle = (BorderedStyle) node.getStyle();
            result.left = borderedStyle.getBorderSize().intValue();
            result.right = borderedStyle.getBorderSize().intValue();
            result.top = borderedStyle.getBorderSize().intValue();
            result.bottom = borderedStyle.getBorderSize().intValue();
        }
        return result;
    }
}
