/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.editor.provider;

import java.text.MessageFormat;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.ui.editor.Messages;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationDescriptionItemImpl;
import org.eclipse.sirius.ui.tools.internal.views.common.item.ViewpointItemImpl;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonLabelProvider;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

/**
 * This label provider is used to provides labels, images and foreground for
 * items of the block showing representations and viewpoint on the same viewer.
 * 
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class SiriusRepresentationLabelProvider extends SiriusCommonLabelProvider {

    @Override
    public Color getForeground(Object element) {
        // we show a gray out the item if it is a viewpoint item not enabled in
        // the session of the current opened editor.
        Color result = null;
        if (element instanceof ViewpointItemImpl) {

            ViewpointItemImpl viewpointItem = (ViewpointItemImpl) element;

            if (!viewpointItem.isViewpointEnabledInSession()) {
                result = VisualBindingManager.getDefault().getColorFromName("gray"); //$NON-NLS-1$
            }
        }
        if (result == null) {
            result = super.getForeground(element);
        }
        return result;
    }

    @Override
    public String getText(Object element) {
        // We add a disabled label to the viewpoint item label if it is not
        // enabled in the session of the current opened editor.
        String text = super.getText(element);
        if (element instanceof ViewpointItemImpl) {
            ViewpointItemImpl viewpointItem = (ViewpointItemImpl) element;

            if (!viewpointItem.isViewpointEnabledInSession()) {
                text += " (" + Messages.GraphicalRepresentationHandler_disabledViewpoint_label + ")"; //$NON-NLS-1$ //$NON-NLS-2$
            }
        } else if (element instanceof RepresentationDescriptionItemImpl) {
            RepresentationDescriptionItemImpl descriptionItem = (RepresentationDescriptionItemImpl) element;
            text += MessageFormat.format(Messages.GraphicalRepresentationHandler_representationNumber_label, descriptionItem.getChildren().size()); // $NON-NLS-1$
        }
        return text;
    }

    @Override
    public Image getImage(Object element) {
        Image image = null;
        // For viewpoint item we show as icon the one corresponding to the
        // status of the viewpoint (coming from a plugin in the runtime context
        // or in the workspace).
        if (element instanceof ViewpointItemImpl) {
            ViewpointItemImpl viewpointItem = (ViewpointItemImpl) element;
            final Viewpoint vp = viewpointItem.getViewpoint();
            if (vp.getIcon() != null && vp.getIcon().length() > 0) {
                final ImageDescriptor desc = SiriusEditPlugin.Implementation.findImageDescriptor(vp.getIcon());
                if (desc != null) {
                    image = SiriusEditPlugin.getPlugin().getImage(desc);
                    image = getEnhancedImage(image, vp);
                }
            }
            if (image == null) {
                image = SiriusEditPlugin.getPlugin().getImage(SiriusEditPlugin.getPlugin().getItemImageDescriptor(vp));
                image = getEnhancedImage(image, vp);
            }
        } else {
            image = super.getImage(element);
        }
        return image;
    }

    /**
     * Return a composition of the viewpoint item image with the viewpoint image
     * and a folder image as an overlay.
     * 
     * @param image
     *            the viewpoint image.
     * @param viewpoint
     *            the viewpoint from which we want the composed image.
     * @return a composition of the viewpoint item image with the viewpoint
     *         image as an overlay and a folder image.
     */
    private Image getEnhancedImage(final Image image, final Viewpoint viewpoint) {
        // Add decorator if the viewpoint comes from workspace
        if (!ViewpointRegistry.getInstance().isFromPlugin(viewpoint) && image != null) {
            return SiriusEditPlugin.getPlugin().getImage(getOverlayedDescriptor(image, "icons/full/decorator/folder_close.gif")); //$NON-NLS-1$
        }
        return image;
    }

    /**
     * Returns an image descriptor of the folder image as overlay of the
     * viewpoint image.
     * 
     * @param baseImage
     *            viewpoint image
     * @param decoratorPath
     *            path to the folder image.
     * @return an image descriptor of the folder image as overlay of the
     *         viewpoint image.
     */
    private ImageDescriptor getOverlayedDescriptor(final Image baseImage, final String decoratorPath) {
        final ImageDescriptor decoratorDescriptor = SiriusEditPlugin.Implementation.getBundledImageDescriptor(decoratorPath);
        return new DecorationOverlayIcon(baseImage, decoratorDescriptor, IDecoration.BOTTOM_LEFT);
    }

}
