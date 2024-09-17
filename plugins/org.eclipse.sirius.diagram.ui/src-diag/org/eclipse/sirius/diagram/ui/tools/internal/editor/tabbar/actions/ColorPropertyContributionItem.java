/******************************************************************************
 * Copyright (c) 2002, 2024 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions;

import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.PropertyChangeContributionItem;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsMessages;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsPluginImages;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.sirius.diagram.ui.tools.api.color.ColorPalettePopupService;
import org.eclipse.sirius.diagram.ui.tools.api.color.IColorPalettePopup;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.ColorPalettePopup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * Inspired from {@link org.eclipse.gmf.runtime.diagram.ui.actions.internal.ColorPropertyContributionItem}.
 *
 * @author gplouhinec
 */
public class ColorPropertyContributionItem extends PropertyChangeContributionItem implements Listener {

    /**
     * An image descriptor that overlays two images: a basic icon and a thin color bar underneath it
     */
    private static class ColorMenuImageDescriptor extends CompositeImageDescriptor {
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
        ColorMenuImageDescriptor(ImageData basicImgData, RGB rgb) {
            this.basicImgData = basicImgData;
            this.rgb = rgb;
        }

        /**
         * @see org.eclipse.jface.resource.CompositeImageDescriptor#drawCompositeImage(int, int)
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

    /** default color icon width */
    private static final Point ICON_SIZE = new Point(16, 16);

    /** the basic image data */
    private ImageData basicImageData;

    /** the disabledbasic image data */
    private ImageData disabledBasicImageData;

    /** the disabled basic image data **/
    private Image disabledBasicImage;

    /** the overlayed image */
    private Image overlyedImage;

    /** the last selected color */
    private Integer lastColor;

    /** the drop down menu */
    private Menu menu;

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
     *            the disabled basic image data
     */
    public ColorPropertyContributionItem(IWorkbenchPage workbenchPage, String id, String propertyId, String propertyName, String toolTipText, ImageData basicImageData,
            ImageData disabledBasicImageData) {
        super(workbenchPage, id, propertyId, propertyName);
        assert null != toolTipText;
        assert null != basicImageData;
        this.basicImageData = basicImageData;
        this.disabledBasicImageData = disabledBasicImageData;
        setLabel(toolTipText);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.ui.action.AbstractContributionItem#init()
     */
    @Override
    protected void init() {
        super.init();
        super.setWorkbenchPart(getWorkbenchPart());
        this.overlyedImage = new ColorMenuImageDescriptor(getBasicImageData(), null).createImage();
        this.disabledBasicImage = new ColorMenuImageDescriptor(this.disabledBasicImageData, null).createImage();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.IContributionItem#dispose()
     */
    @Override
    public void dispose() {
        if (overlyedImage != null && !overlyedImage.isDisposed()) {
            overlyedImage.dispose();
            overlyedImage = null;
        }
        if (menu != null && !menu.isDisposed()) {
            menu.dispose();
            menu = null;
        }
        if (disabledBasicImage != null && !disabledBasicImage.isDisposed()) {
            disabledBasicImage.dispose();
            disabledBasicImage = null;
        }

        super.dispose();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.ui.action.AbstractContributionItem#createToolItem(org.eclipse.swt.widgets.ToolBar,
     *      int)
     */
    @Override
    protected ToolItem createToolItem(ToolBar parent, int index) {
        ToolItem ti = new ToolItem(parent, SWT.DROP_DOWN, index);
        ti.addListener(SWT.Selection, getItemListener());
        ti.setImage(overlyedImage);
        ti.setDisabledImage(this.disabledBasicImage);
        return ti;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.ui.action.AbstractContributionItem#createMenuItem(org.eclipse.swt.widgets.Menu,
     *      int)
     */
    @Override
    protected MenuItem createMenuItem(Menu parent, int index) {
        MenuItem mi = new MenuItem(parent, SWT.PUSH);
        mi.addListener(SWT.Selection, this);
        mi.setImage(overlyedImage);
        return mi;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.ui.action.AbstractContributionItem#handleWidgetEvent(org.eclipse.swt.widgets.Event)
     */
    @Override
    protected void handleWidgetEvent(Event e) {
        switch (e.type) {
        case SWT.Selection:
            handleWidgetSelection(e);
            break;
        default:
            super.handleWidgetEvent(e);
        }
    }

    /**
     * Handles a widget selection event.
     */
    private void handleWidgetSelection(Event e) {
        if (e.widget instanceof ToolItem toolItem) {
            if (e.detail == 4) { // on drop-down button
                invokeColorPalettePopup(toolItem);
            } else { // on icon button
                if (lastColor != null) {
                    runWithEvent(e);
                } else {
                    // No previous color? Open the popup.
                    invokeColorPalettePopup(toolItem);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object getNewPropertyValue() {
        return lastColor;
    }

    /**
     * {@inheritDoc}
     */
    public void handleEvent(Event event) {
        invokeColorPalettePopup(getItem());
    }

    /**
     * Invokes the ColorPalettePopup to choose a color.
     * 
     * @param toolItem
     *            the item clicked on the tabbar.
     */
    private void invokeColorPalettePopup(Item item) {
        final IColorPalettePopup popup = ColorPalettePopupService.getColorPalettePopup(Display.getCurrent().getActiveShell(), ((DDiagramEditor) getWorkbenchPart()).getSession(), getOperationSet(), getPropertyId());
        popup.init();
        popup.open(computePopupLocation(item));

        final RGB rgb = popup.getSelectedColor();
        if (rgb != null) {
            // create a new overlayed icon with the new color
            if (overlyedImage != null) {
                overlyedImage.dispose();
            }
            overlyedImage = new ColorMenuImageDescriptor(getBasicImageData(), rgb).createImage();
            if (getItem() != null) {
                getItem().setImage(overlyedImage);
            }
            // set the new color as the last color
            lastColor = FigureUtilities.RGBToInteger(rgb);
            runWithEvent(null);
        }
    }

    private Point computePopupLocation(Item item) {
        Point location = new Point(0, 0);
        if (item instanceof ToolItem toolItem) {
            location = ColorPalettePopup.getValidPopupLocation(toolItem);
        } else if (item instanceof MenuItem menuItem) {
            location =  ColorPalettePopup.getValidPopupLocation(menuItem);
        }
        return location;
    }

    /**
     * Overrides the getWorkbenchPart method to be able to find the WorkbenchPart even after dispose. The action can be
     * executed after the dispose because it is accessible through the contextual menu action (which is disposed during
     * the OS Color Palette opening).
     * 
     * @return The current {@link IWorkbenchPart}
     */
    protected IWorkbenchPart getWorkbenchPart() {
        IWorkbenchPart resultWorkbenchPart = super.getWorkbenchPart();
        if (resultWorkbenchPart == null && PlatformUI.getWorkbench() != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
            resultWorkbenchPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
        }
        return resultWorkbenchPart;
    }

    /**
     * Gets the basic image data.
     * 
     * @return ImageData basicImageData
     */
    protected ImageData getBasicImageData() {
        return this.basicImageData;
    }

    /**
     * Create the FONT color menu.
     * 
     * @param workbenchPage
     *            The part service
     * @return The FONT color menu
     */
    public static ColorPropertyContributionItem createFontColorContributionItem(IWorkbenchPage workbenchPage) {
        String propertyName = DiagramUIActionsMessages.PropertyDescriptorFactory_FontColor;
        String toolTipText = DiagramUIActionsMessages.ColorChangeActionMenu_fontColor;
        ImageData basicImageData = DiagramUIActionsPluginImages.DESC_FONT_COLOR.getImageData(100);
        ImageData disabledBasicImageData = DiagramUIActionsPluginImages.DESC_FONT_COLOR_DISABLED.getImageData(100);

        return new ColorPropertyContributionItem(workbenchPage, ActionIds.CUSTOM_FONT_COLOR, Properties.ID_FONTCOLOR, propertyName, toolTipText, basicImageData, disabledBasicImageData);
    }

    /**
     * Create the LINE color menu.
     * 
     * @param workbenchPage
     *            The part service
     * @return The LINE color menu
     */
    public static ColorPropertyContributionItem createLineColorContributionItem(IWorkbenchPage workbenchPage) {
        String propertyName = DiagramUIActionsMessages.PropertyDescriptorFactory_LineColor;
        String toolTipText = DiagramUIActionsMessages.ColorChangeActionMenu_lineColor;
        ImageData basicImageData = DiagramUIActionsPluginImages.DESC_LINE_COLOR.getImageData(100);
        ImageData disabledBasicImageData = DiagramUIActionsPluginImages.DESC_LINE_COLOR_DISABLED.getImageData(100);

        return new ColorPropertyContributionItem(workbenchPage, ActionIds.CUSTOM_LINE_COLOR, Properties.ID_LINECOLOR, propertyName, toolTipText, basicImageData, disabledBasicImageData);
    }

    /**
     * Create the FILL color menu.
     * 
     * @param workbenchPage
     *            The part service
     * @return The FILL color menu
     */
    public static ColorPropertyContributionItem createFillColorContributionItem(IWorkbenchPage workbenchPage) {
        String propertyName = DiagramUIActionsMessages.PropertyDescriptorFactory_FillColor;
        String toolTipText = DiagramUIActionsMessages.ColorChangeActionMenu_fillColor;
        ImageData basicImageData = DiagramUIActionsPluginImages.DESC_FILL_COLOR.getImageData(100);
        ImageData disabledBasicImageData = DiagramUIActionsPluginImages.DESC_FILL_COLOR_DISABLED.getImageData(100);

        return new ColorPropertyContributionItem(workbenchPage, ActionIds.CUSTOM_FILL_COLOR, Properties.ID_FILLCOLOR, propertyName, toolTipText, basicImageData, disabledBasicImageData);
    }

}
