/******************************************************************************
 * Copyright (c) 2002, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.ui.util.WindowUtil;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.PropertyChangeContributionItem;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsMessages;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsPluginImages;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.core.util.PackageUtil;
import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.ColorDialog;
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
 * Copied from org.eclipse.gmf.runtime.diagram.ui.actions.internal.ColorPropertyContributionItem.
 *
 * @author melaasar
 */
public class ColorPropertyContributionItem extends PropertyChangeContributionItem implements Listener {

    /**
     * An image descriptor that overlays two images: a basic icon and a thin
     * color bar underneath it
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

    /**
     * An image descriptor that creates a box with a given color
     */
    private static class ColorBoxImageDescriptor extends ImageDescriptor {
        /** the color value in RGB scheme */
        private RGB rgb;

        /**
         * Creates a new instance of the color box image descriptor with a given
         * color
         * 
         * @param rgb
         *            The color value in RGB scheme
         */
        ColorBoxImageDescriptor(RGB rgb) {
            this.rgb = rgb;
        }

        /**
         * @see org.eclipse.jface.resource.ImageDescriptor#getImageData()
         */
        @Override
        public ImageData getImageData() {
            ImageData data = new ImageData(ICON_SIZE.x, ICON_SIZE.y, 1, new PaletteData(new RGB[] { rgb, OUTLINE_COLOR }));

            for (int i = 0; i < ICON_SIZE.y; i++) {
                data.setPixel(0, i, 1);
            }
            for (int i = 0; i < ICON_SIZE.y; i++) {
                data.setPixel(ICON_SIZE.x - 1, i, 1);
            }
            for (int i = 0; i < ICON_SIZE.x; i++) {
                data.setPixel(i, 0, 1);
            }
            for (int i = 0; i < ICON_SIZE.x; i++) {
                data.setPixel(i, ICON_SIZE.y - 1, 1);
            }
            return data;
        }
    }

    /**
     * A descirptor for an inventory color
     */
    private static final class InventoryColorDescriptor {
        private RGB colorValue;

        private String colorName;

        private InventoryColorDescriptor(RGB colorValue, String colorName) {
            this.colorValue = colorValue;
            this.colorName = colorName;
        }
    }

    /** inventory colors */
    private static final InventoryColorDescriptor WHITE = new InventoryColorDescriptor(new RGB(255, 255, 255), DiagramUIActionsMessages.ColorPropertyChangeAction_white);

    private static final InventoryColorDescriptor BLACK = new InventoryColorDescriptor(new RGB(0, 0, 0), DiagramUIActionsMessages.ColorPropertyChangeAction_black);

    private static final InventoryColorDescriptor LIGHT_GRAY = new InventoryColorDescriptor(new RGB(192, 192, 192), DiagramUIActionsMessages.ColorPropertyChangeAction_lightGray);

    private static final InventoryColorDescriptor GRAY = new InventoryColorDescriptor(new RGB(128, 128, 128), DiagramUIActionsMessages.ColorPropertyChangeAction_gray);

    private static final InventoryColorDescriptor DARK_GRAY = new InventoryColorDescriptor(new RGB(64, 64, 64), DiagramUIActionsMessages.ColorPropertyChangeAction_darkGray);

    private static final InventoryColorDescriptor RED = new InventoryColorDescriptor(new RGB(227, 164, 156), DiagramUIActionsMessages.ColorPropertyChangeAction_red);

    private static final InventoryColorDescriptor GREEN = new InventoryColorDescriptor(new RGB(166, 193, 152), DiagramUIActionsMessages.ColorPropertyChangeAction_green);

    private static final InventoryColorDescriptor BLUE = new InventoryColorDescriptor(new RGB(152, 168, 191), DiagramUIActionsMessages.ColorPropertyChangeAction_blue);

    private static final InventoryColorDescriptor YELLOW = new InventoryColorDescriptor(new RGB(225, 225, 135), DiagramUIActionsMessages.ColorPropertyChangeAction_yellow);

    private static final InventoryColorDescriptor PURPLE = new InventoryColorDescriptor(new RGB(184, 151, 192), DiagramUIActionsMessages.ColorPropertyChangeAction_magenta);

    private static final InventoryColorDescriptor TEAL = new InventoryColorDescriptor(new RGB(155, 199, 204), DiagramUIActionsMessages.ColorPropertyChangeAction_cyan);

    private static final InventoryColorDescriptor PINK = new InventoryColorDescriptor(new RGB(228, 179, 229), DiagramUIActionsMessages.ColorPropertyChangeAction_pink);

    private static final InventoryColorDescriptor ORANGE = new InventoryColorDescriptor(new RGB(237, 201, 122), DiagramUIActionsMessages.ColorPropertyChangeAction_orange);

    /** default color icon width */
    private static final Point ICON_SIZE = new Point(16, 16);

    /** custom color group maximum length */
    private static final int CUSTOM_SIZE = 3;

    /** the default preference color */
    private static final RGB DEFAULT_PREF_COLOR = new RGB(0, 0, 0);

    /** the default preference color */
    private static final RGB OUTLINE_COLOR = new RGB(192, 192, 192);

    /** a color value that indicates the default color */
    private static final String DEFAULT = "Default"; //$NON-NLS-1$

    /** a color value that indicates to browse for a color */
    private static final String CHOOSE = "Choose"; //$NON-NLS-1$

    /** a color value that indicates to browse for a color */
    private static final String CLEAR = "Clear"; //$NON-NLS-1$

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

    /** the custom color list */
    private List customColors = new ArrayList();

    /** the inventory color list */
    private List inventoryColors = new ArrayList();

    /** the inventory color list key: anRGB, value: anImage */
    private HashMap imageColorMap = new HashMap();

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
    public ColorPropertyContributionItem(IWorkbenchPage workbenchPage, String id, String propertyId, String propertyName, String toolTipText, ImageData basicImageData, ImageData disabledBasicImageData) {
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
        // CHECKSTYLE:OFF
        for (Iterator i = imageColorMap.values().iterator(); i.hasNext();) {
            // CHECKSTYLE:ON
            Image image = (Image) i.next();
            if (!image.isDisposed()) {
                image.dispose();
            }
        }
        if (disabledBasicImage != null && !disabledBasicImage.isDisposed()) {
            disabledBasicImage.dispose();
            disabledBasicImage = null;
        }
        imageColorMap = new HashMap();

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
        MenuItem mi = index >= 0 ? new MenuItem(parent, SWT.CASCADE, index) : new MenuItem(parent, SWT.CASCADE);
        createMenu(mi);
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
        if (e.detail == 4) { // on drop-down button
            createMenu(getItem());
        } else { // on icon button
            if (lastColor != null) {
                runWithEvent(e);
            }
        }
    }

    /**
     * Creates the color menu
     */
    private void createMenu(Item item) {
        if (menu != null && !menu.isDisposed()) {
            menu.dispose();
        }

        if (item instanceof ToolItem) {
            ToolItem toolItem = (ToolItem) item;
            menu = new Menu(toolItem.getParent());
            Rectangle b = toolItem.getBounds();
            Point p = toolItem.getParent().toDisplay(new Point(b.x, b.y + b.height));
            menu.setLocation(p.x, p.y); // waiting for SWT 0.42
            menu.setVisible(true);
        } else if (item instanceof MenuItem) {
            MenuItem menuItem = (MenuItem) item;
            menu = new Menu(menuItem.getParent());
            menuItem.setMenu(menu);
        }

        assert null != menu : "falid to create menu"; //$NON-NLS-1$
        buildMenu(menu);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.internal.PropertyChangeContributionItem#getNewPropertyValue()
     */
    @Override
    protected Object getNewPropertyValue() {
        return lastColor;
    }

    /**
     * Builds the color menu consisting of : inventory colors, custom colors,
     * default and color picker
     * 
     * @param theMenu
     *            The menu
     */
    private void buildMenu(Menu theMenu) {
        // inventory colors
        createInventoryColorMenuItem(theMenu, WHITE);
        createInventoryColorMenuItem(theMenu, BLACK);
        createInventoryColorMenuItem(theMenu, LIGHT_GRAY);
        createInventoryColorMenuItem(theMenu, GRAY);
        createInventoryColorMenuItem(theMenu, DARK_GRAY);
        createInventoryColorMenuItem(theMenu, RED);
        createInventoryColorMenuItem(theMenu, GREEN);
        createInventoryColorMenuItem(theMenu, BLUE);
        createInventoryColorMenuItem(theMenu, YELLOW);
        createInventoryColorMenuItem(theMenu, PURPLE);
        createInventoryColorMenuItem(theMenu, TEAL);
        createInventoryColorMenuItem(theMenu, PINK);
        createInventoryColorMenuItem(theMenu, ORANGE);

        // history colors
        if (!customColors.isEmpty()) {
            createMenuSeparator(theMenu);
            Iterator iter = customColors.iterator();
            while (iter.hasNext()) {
                RGB rgb = (RGB) iter.next();
                createColorMenuItem(theMenu, rgb);
            }
            createClearCustomColorMenuItem(theMenu);
        }

        // default color and color picker
        createMenuSeparator(theMenu);
        // createDefaultColorMenuItem(theMenu);
        createChooseColorMenuItem(theMenu);
    }

    /**
     * Creates a new menu separator
     * 
     * @param theMenu
     *            The menu
     */
    private void createMenuSeparator(Menu theMenu) {
        new MenuItem(theMenu, SWT.SEPARATOR);
    }

    /**
     * Creates a inventory color menu item with the given color name and RGB
     * value
     * 
     * @param theMenu
     *            The menu
     * @param color
     *            The color RGB value
     * @param colorName
     *            the color name (externalizable)
     */
    private void createInventoryColorMenuItem(Menu theMenu, InventoryColorDescriptor color) {

        RGB rgb = color.colorValue;
        Image image = (Image) imageColorMap.get(rgb);
        if (image == null) {
            image = new ColorBoxImageDescriptor(color.colorValue).createImage();
            imageColorMap.put(rgb, image);
        }
        MenuItem mi = createMenuItem(theMenu, color.colorName, image);
        mi.setData(rgb);
        inventoryColors.add(rgb);
    }

    /**
     * Creates a color menu item with the RGB value as a name
     * 
     * @param theMenu
     *            The menu
     * @param rgb
     *            The color RGB value
     */
    private void createColorMenuItem(Menu theMenu, RGB rgb) {
        Image image = (Image) imageColorMap.get(rgb);
        if (image == null) {
            image = new ColorBoxImageDescriptor(rgb).createImage();
            imageColorMap.put(rgb, image);
        }
        MenuItem mi = createMenuItem(theMenu, rgb.toString(), image);
        mi.setData(rgb);
    }

    /**
     * Creates a menu item for the color picker
     * 
     * @param theMenu
     *            The menu
     */
    private void createChooseColorMenuItem(Menu theMenu) {
        String text = DiagramUIActionsMessages.ColorPropertyChangeAction_moreColors;
        Image image = null; // new ColorBoxImageDescriptor(color).createImage();
        MenuItem mi = createMenuItem(theMenu, text, image);
        mi.setData(CHOOSE);
    }

    /**
     * Creates a menu item to clear the custom color menu group
     * 
     * @param theMenu
     *            The menu
     */
    private void createClearCustomColorMenuItem(Menu theMenu) {
        String text = DiagramUIActionsMessages.ColorPropertyChangeAction_clearColors;
        Image image = null; // new ColorBoxImageDescriptor(color).createImage();
        MenuItem mi = createMenuItem(theMenu, text, image);
        mi.setData(CLEAR);
    }

    /**
     * Creates a menu item with a given text and image with the push style
     * 
     * @param theMenu
     *            The menu
     * @param text
     *            The menu item text
     * @param image
     *            The menu item image
     */
    private MenuItem createMenuItem(Menu theMenu, String text, Image image) {
        MenuItem mi = new MenuItem(theMenu, SWT.PUSH);
        if (text != null) {
            mi.setText(text);
        }
        if (image != null) {
            mi.setImage(image);
        }
        mi.addListener(SWT.Selection, this);
        return mi;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    public void handleEvent(Event event) {
        MenuItem menuItem = (MenuItem) event.widget;
        Object data = menuItem.getData();

        RGB rgb = null;

        if (data instanceof RGB) {
            rgb = (RGB) data;
        } else if (data.equals(CHOOSE)) {
            rgb = getBrowseColor();
        } else if (data.equals(DEFAULT)) {
            rgb = getDefaultColor();
        } else if (data.equals(CLEAR)) {
            customColors.clear();
        }

        if (rgb != null) {
            if (getToolItem() != null) {
                // if a new custom color add it to history
                if (!customColors.contains(rgb) && !inventoryColors.contains(rgb)) {
                    if (customColors.size() == CUSTOM_SIZE) {
                        customColors.remove(0);
                    }
                    customColors.add(rgb);
                }

                // create a new overlayed icon with the new color
                if (overlyedImage != null) {
                    overlyedImage.dispose();
                }
                overlyedImage = new ColorMenuImageDescriptor(getBasicImageData(), rgb).createImage();
                getItem().setImage(overlyedImage);
            }

            // set the new color as the last color
            lastColor = FigureUtilities.RGBToInteger(rgb);

            // run the action
            runWithEvent(event);
        }
    }

    /**
     * Overrides the getWorkbenchPart method to be able to find the
     * WorkbenchPart even after dispose. The action can be executed after the
     * dispose because it is accessible through the contextual menu action
     * (which is disposed during the OS Color Palette opening).
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
     * Returns the color to use in the browse mode. By default, this comes from
     * the color picker dialog
     * 
     * @return The color to use in browse mode
     */
    protected RGB getBrowseColor() {
        ColorDialog dialog = new ColorDialog(Display.getCurrent().getActiveShell());
        WindowUtil.centerDialog(dialog.getParent(), Display.getCurrent().getActiveShell());
        if (lastColor != null) {
            dialog.setRGB(FigureUtilities.integerToRGB(lastColor));
        }
        dialog.open();
        return dialog.getRGB();
    }

    /**
     * Returns the color to use in the default mode. A limitation is that if
     * there are multiple editparts with different default colors only the
     * default color of the first is returned.
     * 
     * @return The color to use in default mode
     */
    protected RGB getDefaultColor() {
        // CHECKSTYLE:OFF
        for (Iterator iter = getOperationSet().iterator(); iter.hasNext();) {
            // CHECKSTYLE:ON
            EditPart editpart = (EditPart) iter.next();
            if (editpart instanceof IGraphicalEditPart) {
                final EStructuralFeature feature = (EStructuralFeature) PackageUtil.getElement(getPropertyId());

                Object preferredValue = ((IGraphicalEditPart) editpart).getPreferredValue(feature);
                if (preferredValue instanceof Integer) {
                    return FigureUtilities.integerToRGB((Integer) preferredValue);
                }

            }

        }

        return DEFAULT_PREF_COLOR;
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
        ImageData basicImageData = DiagramUIActionsPluginImages.DESC_FONT_COLOR.getImageData();
        ImageData disabledBasicImageData = DiagramUIActionsPluginImages.DESC_FONT_COLOR_DISABLED.getImageData();

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
        ImageData basicImageData = DiagramUIActionsPluginImages.DESC_LINE_COLOR.getImageData();
        ImageData disabledBasicImageData = DiagramUIActionsPluginImages.DESC_LINE_COLOR_DISABLED.getImageData();

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
        ImageData basicImageData = DiagramUIActionsPluginImages.DESC_FILL_COLOR.getImageData();
        ImageData disabledBasicImageData = DiagramUIActionsPluginImages.DESC_FILL_COLOR_DISABLED.getImageData();

        return new ColorPropertyContributionItem(workbenchPage, ActionIds.CUSTOM_FILL_COLOR, Properties.ID_FILLCOLOR, propertyName, toolTipText, basicImageData, disabledBasicImageData);
    }

}
