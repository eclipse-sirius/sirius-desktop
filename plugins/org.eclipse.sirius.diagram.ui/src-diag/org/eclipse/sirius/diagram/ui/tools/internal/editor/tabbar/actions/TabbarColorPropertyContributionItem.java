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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions;

import java.lang.reflect.Field;

import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsMessages;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsPluginImages;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Service to set and get last color used on color property contribution items.
 * 
 * @author mchauvin
 */
public class TabbarColorPropertyContributionItem extends ColorPropertyContributionItem {

    private static final String FIELD_NAME = "lastColor"; //$NON-NLS-1$

    private IWorkbenchPart representationPart;

    /**
     * Creates a new color property contribution item.
     * 
     * @param workbenchPage
     *            The part service
     * @param id
     *            The item id
     * @param propertyName
     *            The color property name (externalizable)
     * @param propertyId
     *            The color property id
     * @param toolTipText
     *            The tool tip text
     * @param basicImageData
     *            The basic image data
     * @param disabledBasicImageData
     *            The disabled basic image data
     */
    public TabbarColorPropertyContributionItem(IWorkbenchPage workbenchPage, String id, String propertyId, String propertyName, String toolTipText, ImageData basicImageData,
            ImageData disabledBasicImageData) {
        super(workbenchPage, id, propertyId, propertyName, toolTipText, basicImageData, disabledBasicImageData);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.ui.action.AbstractContributionItem#init()
     */
    @Override
    protected void init() {
        super.init();

        RGB rgb = null;
        final String color = getLastColor();
        if (color != null) {
            rgb = FigureUtilities.integerToRGB(Integer.parseInt(color));
        }

        TabbarColorPropertyContributionItem.setElement(this, new ColorMenuImageDescriptor(getBasicImageData(), rgb).createImage(), "overlyedImage"); //$NON-NLS-1$
        TabbarColorPropertyContributionItem.setElement(this,
                new ColorMenuImageDescriptor((ImageData) TabbarColorPropertyContributionItem.getElement(this, "disabledBasicImageData"), rgb).createImage(), "disabledBasicImage"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Create the FONT color menu.
     * 
     * @param workbenchPage
     *            The part service
     * @return The FONT color menu
     */
    public static TabbarColorPropertyContributionItem createFontColorContributionItem(IWorkbenchPage workbenchPage) {
        String propertyName = DiagramUIActionsMessages.PropertyDescriptorFactory_FontColor;
        String toolTipText = DiagramUIActionsMessages.ColorChangeActionMenu_fontColor;
        ImageData basicImageData = DiagramUIActionsPluginImages.DESC_FONT_COLOR.getImageData();
        ImageData disabledBasicImageData = DiagramUIActionsPluginImages.DESC_FONT_COLOR_DISABLED.getImageData();

        return new TabbarColorPropertyContributionItem(workbenchPage, ActionIds.CUSTOM_FONT_COLOR, Properties.ID_FONTCOLOR, propertyName, toolTipText, basicImageData, disabledBasicImageData);
    }

    /**
     * Create the LINE color menu.
     * 
     * @param workbenchPage
     *            The part service
     * @return The LINE color menu
     */
    public static TabbarColorPropertyContributionItem createLineColorContributionItem(IWorkbenchPage workbenchPage) {
        String propertyName = DiagramUIActionsMessages.PropertyDescriptorFactory_LineColor;
        String toolTipText = DiagramUIActionsMessages.ColorChangeActionMenu_lineColor;
        ImageData basicImageData = DiagramUIActionsPluginImages.DESC_LINE_COLOR.getImageData();
        ImageData disabledBasicImageData = DiagramUIActionsPluginImages.DESC_LINE_COLOR_DISABLED.getImageData();

        return new TabbarColorPropertyContributionItem(workbenchPage, ActionIds.CUSTOM_LINE_COLOR, Properties.ID_LINECOLOR, propertyName, toolTipText, basicImageData, disabledBasicImageData);
    }

    /**
     * Create the FILL color menu.
     * 
     * @param workbenchPage
     *            The part service
     * @return The FILL color menu
     */
    public static TabbarColorPropertyContributionItem createFillColorContributionItem(IWorkbenchPage workbenchPage) {
        String propertyName = DiagramUIActionsMessages.PropertyDescriptorFactory_FillColor;
        String toolTipText = DiagramUIActionsMessages.ColorChangeActionMenu_fillColor;
        ImageData basicImageData = DiagramUIActionsPluginImages.DESC_FILL_COLOR.getImageData();
        ImageData disabledBasicImageData = DiagramUIActionsPluginImages.DESC_FILL_COLOR_DISABLED.getImageData();

        return new TabbarColorPropertyContributionItem(workbenchPage, ActionIds.CUSTOM_FILL_COLOR, Properties.ID_FILLCOLOR, propertyName, toolTipText, basicImageData, disabledBasicImageData);
    }

    /**
     * We should use reflection to access the last color method.
     * 
     * @param lastColor
     *            the last color to sert
     */
    public void setLastColor(final String lastColor) {
        TabbarColorPropertyContributionItem.setElement(this, Integer.parseInt(lastColor), FIELD_NAME);
    }

    /**
     * We should use reflection to access the last color method.
     * 
     * @return the last color or <code>null</code> if it cannot found
     */
    public String getLastColor() {
        Integer lastColor = (Integer) TabbarColorPropertyContributionItem.getElement(this, FIELD_NAME);
        if (lastColor != null) {
            return lastColor.toString();
        }
        return null;
    }

    @Override
    public void refresh() {
        // we only refresh the action if the current workbench part
        // corresponding to the diagram workbench page action.
        IWorkbenchPart part = getWorkbenchPart();
        if (representationPart != null && !representationPart.equals(part)) {
            return;
        }
        super.refresh();
    }

    /**
     * Set the part of this contribution item.
     * 
     * @param contributionItemWorkbenchPart
     *            the workbench part hosting this item tabbar.
     */
    public void setActionWorkbenchPart(IWorkbenchPart contributionItemWorkbenchPart) {
        this.representationPart = contributionItemWorkbenchPart;
    }

    @Override
    public void dispose() {
        representationPart = null;
        super.dispose();
    }

    /**
     * We should use reflection to access the last color method.
     * 
     * @param item
     *            the item on which to get the last color
     * @param featureName
     *            the feature name
     * @return the last color or <code>null</code> if it cannot found
     */
    private static Object getElement(final ColorPropertyContributionItem item, final String featureName) {
        Field field;
        try {
            field = ColorPropertyContributionItem.class.getDeclaredField(featureName);
            field.setAccessible(true);
            return field.get(item);
        } catch (SecurityException e) {
            /* do nothing should not happen */
        } catch (IllegalArgumentException e) {
            /* do nothing should not happen */
        } catch (IllegalAccessException e) {
            /* do nothing should not happen */
        } catch (NoSuchFieldException e) {
            /* do nothing should not happen */
        }
        return null;
    }

    /**
     * We should use reflection to access the last color method.
     * 
     * @param item
     *            the item on which to set the last color
     * @param lastColor
     *            the last color to sert
     */
    private static void setElement(final ColorPropertyContributionItem item, final Object object, final String featureName) {
        Field field;
        try {
            field = ColorPropertyContributionItem.class.getDeclaredField(featureName);
            field.setAccessible(true);
            Object fieldItem = field.get(item);
            if (fieldItem instanceof Image && fieldItem != object) {
                ((Image) fieldItem).dispose();
            }
            field.set(item, object);
        } catch (SecurityException e) {
            /* do nothing should not happen */
        } catch (IllegalArgumentException e) {
            /* do nothing should not happen */
        } catch (IllegalAccessException e) {
            /* do nothing should not happen */
        } catch (NoSuchFieldException e) {
            /* do nothing should not happen */
        }
    }

    /**
     * An image descriptor that overlays two images: a basic icon and a thin
     * color bar underneath it
     */
    private static class ColorMenuImageDescriptor extends CompositeImageDescriptor {

        /** default color icon width */
        private static final Point ICON_SIZE = new Point(16, 16);

        /** the basic icon */
        private ImageData basicImgData;

        /** the color of the thin color bar */
        private RGB rgb;

        /**
         * Creates a new color menu image descriptor
         * 
         * @param basicIcon
         *            The basic Image data
         * @param rgb
         *            The color bar RGB value
         */
        public ColorMenuImageDescriptor(ImageData basicImgData, RGB rgb) {
            this.basicImgData = basicImgData;
            this.rgb = rgb;
        }

        /**
         * @see org.eclipse.jface.resource.CompositeImageDescriptor#drawCompositeImage(int,
         *      int)
         */
        @Override
        protected void drawCompositeImage(int width, int height) {
            // draw the base image
            drawImage(basicImgData, 0, 0);

            // draw the thin color bar underneath
            if (rgb != null) {
                ImageData colorBar = new ImageData(14, 4, 1, new PaletteData(new RGB[] { rgb }));
                drawImage(colorBar, 1, height - 4);
            }
        }

        /**
         * @see org.eclipse.jface.resource.CompositeImageDescriptor#getSize()
         */
        @Override
        protected Point getSize() {
            return ICON_SIZE;
        }
    }

}
