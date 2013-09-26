/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor.provider;

import java.io.File;
import java.net.MalformedURLException;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.ui.provider.TreeUIPlugin;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.ui.tools.api.provider.DSemanticTargetBasedLabelProvider;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Label provider for all the tree item.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class DTreeColumnLabelProvider extends DSemanticTargetBasedLabelProvider {

    /**
     * Default constructor.
     * 
     * @param adapterFactoryLabelProvider
     *            Provider which to delegate label and image resolution.
     */
    public DTreeColumnLabelProvider(final AdapterFactory adapterFactoryLabelProvider) {
        super(adapterFactoryLabelProvider);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getBackground(java.lang.Object)
     */
    @Override
    public Color getBackground(final Object element) {
        Color result = null;
        if (element instanceof DTreeItem) {
            final DTreeItem item = (DTreeItem) element;
            if (item.getOwnedStyle() != null) {
                final RGBValues rgb = item.getOwnedStyle().getBackgroundColor();
                if (rgb != null) {
                    result = VisualBindingManager.getDefault().getColorFromRGBValues(rgb);
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getFont(java.lang.Object)
     */
    @Override
    public Font getFont(final Object element) {
        if (element instanceof DTreeItem) {
            final DTreeItem item = (DTreeItem) element;
            if (item.getOwnedStyle() != null) {
                final int size = item.getOwnedStyle().getLabelSize();
                FontFormat labelFormat = null;
                if (item.getOwnedStyle().getLabelFormat() != null) {
                    labelFormat = item.getOwnedStyle().getLabelFormat();
                } else {
                    labelFormat = FontFormat.NORMAL_LITERAL;
                }
                return VisualBindingManager.getDefault().getFontFromLabelFormatAndSize(labelFormat, size);
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getForeground(java.lang.Object)
     */
    @Override
    public Color getForeground(final Object element) {
        if (element instanceof DTreeItem) {
            final DTreeItem item = (DTreeItem) element;
            if (item.getOwnedStyle() != null && item.getOwnedStyle().getLabelColor() != null) {
                final RGBValues rgb = item.getOwnedStyle().getLabelColor();
                if (rgb != null) {
                    return VisualBindingManager.getDefault().getLabelColorFromRGBValues(rgb);
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(final Object element) {
        if (element instanceof DTreeItem) {
            final DTreeItem item = (DTreeItem) element;
            if (item.getOwnedStyle() != null && item.getOwnedStyle().isShowIcon()) {
                Image labelImage = null;

                if (StringUtil.isEmpty(item.getOwnedStyle().getIconPath())) {
                    labelImage = super.getImage(element);
                } else {
                    labelImage = getCustomImage(item.getOwnedStyle());
                }
                return labelImage;
            }
        }
        return null;
    }

    private Image getCustomImage(BasicLabelStyle bls) {
        Image customImage = null;
        if (bls != null && !StringUtil.isEmpty(bls.getIconPath())) {
            ImageDescriptor descriptor = null;
            String iconPath = bls.getIconPath();
            final File imageFile = FileProvider.getDefault().getFile(new Path(iconPath));
            if (imageFile != null && imageFile.exists() && imageFile.canRead()) {
                try {
                    descriptor = ImageDescriptor.createFromURL(imageFile.toURI().toURL());
                } catch (MalformedURLException e) {
                    // log a waring later.
                }
            }

            if (descriptor == null) {
                SiriusPlugin.getDefault().warning("Icon file \"" + iconPath + "\" not found", null);
                descriptor = ImageDescriptor.getMissingImageDescriptor();
            }

            customImage = TreeUIPlugin.getImage(descriptor);
        }
        return customImage;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(final Object element) {
        String result = StringUtil.EMPTY_STRING;
        if (element instanceof DTreeItem) {
            final DTreeItem item = (DTreeItem) element;
            if (item != null) {
                result = item.getName();
            }
        }
        return result;
    }
}
