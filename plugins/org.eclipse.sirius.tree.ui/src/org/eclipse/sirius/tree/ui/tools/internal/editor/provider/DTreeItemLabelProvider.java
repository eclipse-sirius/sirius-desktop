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
import java.util.List;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.TreeItemStyle;
import org.eclipse.sirius.tree.ui.provider.TreeUIPlugin;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.ui.tools.api.provider.DSemanticTargetBasedLabelProvider;
import org.eclipse.sirius.ui.tools.internal.editor.DefaultFontStyler;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider for all the tree item.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class DTreeItemLabelProvider extends DSemanticTargetBasedLabelProvider implements IStyledLabelProvider {

    /**
     * Default constructor.
     * 
     * @param adapterFactoryLabelProvider
     *            Provider which to delegate label and image resolution.
     */
    public DTreeItemLabelProvider(final AdapterFactory adapterFactoryLabelProvider) {
        super(adapterFactoryLabelProvider);
    }

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

    @Override
    public Font getFont(final Object element) {
        if (element instanceof DTreeItem) {
            final DTreeItem item = (DTreeItem) element;
            if (item.getOwnedStyle() != null) {
                TreeItemStyle style = item.getOwnedStyle();
                return VisualBindingManager.getDefault().getFontFromLabelFormatAndSize(style.getLabelFormat(), style.getLabelSize());
            }
        }
        return null;
    }

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

    @Override
    public String getText(final Object element) {
        String result = StringUtil.EMPTY_STRING;
        if (element instanceof DTreeItem) {
            DTreeItem item = (DTreeItem) element;
            result = item.getName();
        }
        return result;
    }

    @Override
    public StyledString getStyledText(Object element) {
        String text = getText(element);
        DefaultFontStyler styler = new DefaultFontStyler(getFont(element), getForeground(element), getBackground(element), getUnderline(element), getStrikeout(element));
        if (text == null) {
            text = ""; //$NON-NLS-1$
        }
        StyledString styledString = new StyledString(text, styler);

        return styledString;
    }

    private boolean getStrikeout(Object element) {
        if (element instanceof DTreeItem) {
            final DTreeItem item = (DTreeItem) element;
            TreeItemStyle ownedStyle = item.getOwnedStyle();
            if (ownedStyle != null) {
                List<FontFormat> labelFormat = ownedStyle.getLabelFormat();
                if (labelFormat != null) {
                    return labelFormat.contains(FontFormat.STRIKE_THROUGH_LITERAL);
                }
            }
        }
        return false;
    }

    private boolean getUnderline(Object element) {
        if (element instanceof DTreeItem) {
            final DTreeItem item = (DTreeItem) element;
            TreeItemStyle ownedStyle = item.getOwnedStyle();
            if (ownedStyle != null) {
                List<FontFormat> labelFormat = ownedStyle.getLabelFormat();
                if (labelFormat != null) {
                    return labelFormat.contains(FontFormat.UNDERLINE_LITERAL);
                }
            }
        }
        return false;
    }
}
