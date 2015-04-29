/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.views.providers.outline;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.business.api.provider.AbstractDDiagramElementLabelItemProvider;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider for the diagram outline tree.
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public class OutlineLabelProvider extends LabelProvider implements IFontProvider {

    /** */
    private AdapterFactory factory;

    /**
     * Constructor.
     * 
     * @param adapterFactory
     */
    public OutlineLabelProvider() {
        this.factory = DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(final Object element) {
        Image result = null;
        if (element instanceof DSemanticDecorator) {
            result = getImage((DSemanticDecorator) element);
        } else if (element instanceof AbstractDDiagramElementLabelItemProvider) {
            result = getImage((AbstractDDiagramElementLabelItemProvider) element);
        }
        return result;
    }

    /**
     * Returns the image for the label of the given DNodeLabelItemProvider.
     * 
     * @param element
     *            the element for which to provide the label image
     * @return the image used to label the element, or <code>null</code> if
     *         there is no image for the given object
     */
    private Image getImage(final AbstractDDiagramElementLabelItemProvider element) {
        Image result;
        ImageDescriptor descriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(element.getImage(element.getTarget()));
        if (descriptor == null) {
            descriptor = ImageDescriptor.getMissingImageDescriptor();
        }
        result = DiagramUIPlugin.getPlugin().getImage(descriptor);

        Option<DDiagramElement> optionTarget = element.getDiagramElementTarget();
        if (optionTarget.some() && new DDiagramElementQuery(optionTarget.get()).isLabelHidden()) {
            result = getDecoratedImage(result, DiagramImagesPath.HIDDEN_DECORATOR, IDecoration.TOP_LEFT);
        }

        return result;
    }

    private Image getDecoratedImage(Image baseImage, String decoratorPath, int decoratorPosition) {
        ImageDescriptor decoratorDescriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(DiagramUIPlugin.INSTANCE.getImage(decoratorPath));
        DecorationOverlayIcon finalDescriptor = new DecorationOverlayIcon(baseImage, decoratorDescriptor, decoratorPosition);
        return DiagramUIPlugin.getPlugin().getImage(finalDescriptor);
    }

    /**
     * Returns the image for the label of the given DSemanticDecorator.
     * 
     * @param element
     *            the element for which to provide the label image
     * @return the image used to label the element, or <code>null</code> if
     *         there is no image for the given object
     */
    private Image getImage(DSemanticDecorator element) {
        Image result = null;
        final EObject target = element.getTarget();
        if (target != null) {
            final IItemLabelProvider labelProvider = (IItemLabelProvider) this.factory.adapt(target, IItemLabelProvider.class);
            if (labelProvider != null) {
                ImageDescriptor descriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(labelProvider.getImage(target));
                if (descriptor == null) {
                    descriptor = ImageDescriptor.getMissingImageDescriptor();
                }
                result = DiagramUIPlugin.getPlugin().getImage(descriptor);

                if (element instanceof DEdge) {
                    result = getDecoratedImage(result, DiagramImagesPath.VIEW_EDGE_DECORATOR, IDecoration.BOTTOM_LEFT);
                    result = computeFoldDecorator(result, (DEdge) element);
                }

                if (element instanceof DDiagramElement) {
                    DDiagramElementQuery query = new DDiagramElementQuery((DDiagramElement) element);
                    if (query.isHidden()) {
                        result = getDecoratedImage(result, DiagramImagesPath.HIDDEN_DECORATOR, IDecoration.TOP_LEFT);
                    }
                    if (element instanceof DDiagramElementContainer && query.isLabelHidden()) {
                        result = getDecoratedImage(result, DiagramImagesPath.HIDDEN_LABEL_DECORATOR, IDecoration.BOTTOM_RIGHT);
                    }
                }
            }
        }
        return result;
    }

    private Image computeFoldDecorator(final Image baseImage, final DEdge edge) {
        if (new DDiagramElementQuery(edge).isFolded()) {
            return getDecoratedImage(baseImage, DiagramImagesPath.FOLD_DECORATOR, IDecoration.TOP_RIGHT);
        }
        return baseImage;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(final Object element) {
        String result = null;
        if (element instanceof DSemanticDecorator) {
            final EObject target = ((DSemanticDecorator) element).getTarget();
            if (target != null) {
                final IItemLabelProvider labelProvider = (IItemLabelProvider) this.factory.adapt(target, IItemLabelProvider.class);
                if (labelProvider != null) {
                    result = labelProvider.getText(target);
                }
            }
        } else if (element instanceof AbstractDDiagramElementLabelItemProvider) {
            result = ((AbstractDDiagramElementLabelItemProvider) element).getText(((AbstractDDiagramElementLabelItemProvider) element).getTarget());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IFontProvider#getFont(java.lang.Object)
     */
    public Font getFont(final Object element) {
        Font result = JFaceResources.getDefaultFont();
        if (element instanceof DDiagramElement) {
            final DDiagramElement vpe = (DDiagramElement) element;
            if (!vpe.isVisible() || (vpe instanceof DDiagramElementContainer && new DDiagramElementQuery(vpe).isLabelHidden())) {
                result = JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT);
            }
        } else if (element instanceof AbstractDDiagramElementLabelItemProvider) {
            Option<DDiagramElement> optionTarget = ((AbstractDDiagramElementLabelItemProvider) element).getDiagramElementTarget();
            if (optionTarget.some() && new DDiagramElementQuery(optionTarget.get()).isLabelHidden()) {
                result = JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT);
            }
        }
        return result;
    }
}
