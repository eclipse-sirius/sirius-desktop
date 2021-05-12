/******************************************************************************
 * Copyright (c) 2005, 2021 IBM Corporation and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.dialogs;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.gmf.runtime.common.ui.util.WindowUtil;
import org.eclipse.gmf.runtime.diagram.ui.properties.internal.l10n.DiagramUIPropertiesMessages;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * Copy of gmf code.
 */
public class ColorPalettePopup {

    private static final Integer MAXIMUM_BUTTON_NUMBER = 50;

    private static final Integer BUTTON_COLUMN_NUMBER = 10;

    /**
     * A map associating a RGB color with the corresponding Image.
     */
    private static Map<RGB, Image> rgbToImages = new HashMap<>();

    /** variable to store previous color */
    private int previousColor;

    private Button customColorButton;

    private Map<Object, Object> buttonMap = new LinkedHashMap<>();

    /**
     * A descirptor for an inventory color
     */
    private static class InventoryColorDescriptor extends ImageDescriptor {

        /** the default preference color */
        private static final RGB OUTLINE_COLOR = new RGB(192, 192, 192);

        private RGB rgb;

        InventoryColorDescriptor(RGB colorValue) {
            this.rgb = colorValue;
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

        /**
         * Creates and returns a new SWT image for this image descriptor. The returned image must be explicitly disposed
         * using the image's dispose call. The image will not be automatically garbage collected. In the even of an
         * error, a default image is returned if <code>returnMissingImageOnError</code> is true, otherwise
         * <code>null</code> is returned.
         * <p>
         * Note: Even if <code>returnMissingImageOnError</code> is true, it is still possible for this method to return
         * <code>null</code> in extreme cases, for example if SWT runs out of image handles.
         * </p>
         * 
         * @return a new image or <code>null</code> if the image could not be created
         * 
         */
        // CHECKSTYLE:OFF
        @Override
        public Image createImage() {

            Device device = Display.getCurrent();
            ImageData data = getImageData();
            if (data == null) {
                data = DEFAULT_IMAGE_DATA;
            }

            /*
             * Try to create the supplied image. If there is an SWT Exception try and create the default image if that
             * was requested. Return null if this fails.
             */

            try {
                if (data.transparentPixel >= 0) {
                    ImageData maskData = data.getTransparencyMask();
                    return new Image(device, data, maskData);
                }
                return new Image(device, data);
            } catch (SWTException exception) {

                try {
                    return new Image(device, DEFAULT_IMAGE_DATA);
                } catch (SWTException nextException) {
                    return null;
                }

            }
        }
    }

    private static final String CUSTOM_COLOR_STRING = DiagramUIPropertiesMessages.ColorPalettePopup_custom;

    /** default color icon width. */
    public static final Point ICON_SIZE = new Point(20, 20);

    private Shell shell;

    private RGB selectedColor;

    /**
     * The default color to be used if the user presses the default button.
     */
    private boolean useDefaultColor;

    /**
     * Creates a PopupList above the specified shell.
     * 
     * @param parent
     *            a widget which will be the parent of the new instance (cannot be null)
     * @param rowHeight
     *            the row height
     * @param defaultColors
     *            color used to fill the default colors
     */
    public ColorPalettePopup(Shell parent, int rowHeight, Map<String, RGB> defaultColors) {
        shell = new Shell(parent, ColorPalettePopup.checkStyle(SWT.NONE));
        GridLayout layout = new GridLayout(BUTTON_COLUMN_NUMBER, true);
        layout.horizontalSpacing = 0;
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        layout.verticalSpacing = 0;
        shell.setLayout(layout);

        int count = 0;
        for (String colorName : defaultColors.keySet()) {
            count++;
            // CHECKSTYLE:ON
            Button button = new Button(shell, SWT.PUSH);
            GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
            data.heightHint = rowHeight;
            data.widthHint = rowHeight;
            button.setLayoutData(data);

            final RGB rgb = defaultColors.get(colorName);
            if (!rgbToImages.containsKey(rgb)) {
                InventoryColorDescriptor colorDesc = new InventoryColorDescriptor(rgb);
                rgbToImages.put(rgb, colorDesc.createImage());
            }
            final Image image = rgbToImages.get(rgb);
            button.setImage(image);
            button.setToolTipText(colorName);
            button.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(SelectionEvent e1) {
                    selectedColor = rgb;
                    shell.dispose();
                }
            });
            buttonMap.put(rgb, button);

            if (count == MAXIMUM_BUTTON_NUMBER) {
                break;
            }
        }

        // Button defaultButton = new Button(shell, SWT.PUSH);
        // defaultButton.setText(DEAFULT_COLOR_STRING);
        // GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        // data.horizontalSpan = 4;
        // data.heightHint = rowHeight;
        // defaultButton.setLayoutData(data);
        //
        // defaultButton.addSelectionListener(new SelectionAdapter() {
        //
        // public void widgetSelected(SelectionEvent event) {
        // useDefaultColor = true;
        // shell.dispose();
        // }
        // });

        Button moreColors = new Button(shell, SWT.PUSH);
        moreColors.setText(CUSTOM_COLOR_STRING);
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        data.horizontalSpan = 4;
        data.heightHint = rowHeight;
        moreColors.setLayoutData(data);

        moreColors.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent event) {

                ColorDialog dialog = new ColorDialog(Display.getCurrent().getActiveShell());
                dialog.setRGB(FigureUtilities.integerToRGB(getPreviousColor()));
                WindowUtil.centerDialog(dialog.getParent(), Display.getCurrent().getActiveShell());
                RGB returnedSelectedColor = dialog.open();
                if (returnedSelectedColor != null) { // case of cancel
                    selectedColor = dialog.getRGB();
                }
                shell.dispose();

            }
        });
        // close dialog if user selects outside of the shell
        shell.addListener(SWT.Deactivate, new Listener() {

            @Override
            public void handleEvent(Event e) {
                shell.setVisible(false);
            }
        });
        customColorButton = moreColors;

    }

    /**
     * @param style
     * @return
     */
    private static int checkStyle(int style) {
        int mask = SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT;
        return style & mask;
    }

    /**
     * Launches the Popup List, waits for an item to be selected and then closes PopupList.
     * 
     * @param location
     *            the initial size and location of the PopupList; the dialog will be positioned so that it does not run
     *            off the screen and the largest number of items are visible
     * 
     * @return the text of the selected item or null if no item is selected
     */
    public RGB open(Point location) {

        Point listSize = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
        shell.setBounds(location.x, location.y, listSize.x, listSize.y);

        shell.open();
        shell.setFocus();
        Display display = shell.getDisplay();
        Button prevButton = (Button) buttonMap.get(FigureUtilities.integerToRGB(getPreviousColor()));
        if (prevButton != null) {
            shell.setDefaultButton(prevButton);
            prevButton.setFocus();
        } else {
            shell.setDefaultButton(customColorButton);
        }
        while (!shell.isDisposed() && shell.isVisible()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return getSelectedColor();
    }

    /**
     * Gets the color the user selected. Could be null as the user may have cancelled the gesture or they may have
     * selected the default color button. See {@link #useDefaultColor()}.
     * 
     * @return the selected color or null
     */
    public RGB getSelectedColor() {
        return selectedColor;
    }

    /**
     * Returns true if the user selected to use the default color.
     * 
     * @return true if the default color is to be used; false otherwise
     */
    public boolean useDefaultColor() {
        return useDefaultColor;
    }

    /**
     * Returns the previous color.
     * 
     * @return the previous color.
     */
    public int getPreviousColor() {
        return previousColor;
    }

    /**
     * Sets the previous color.
     * 
     * @param previousColor
     *            the previous color.
     */
    public void setPreviousColor(int previousColor) {
        this.previousColor = previousColor;
    }
}
