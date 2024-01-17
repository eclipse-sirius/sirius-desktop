/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.dialogs;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * Used to create an image representing a RGB color.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
class InventoryColorDescriptor extends ImageDescriptor {

    /** default color icon width. */
    public static final Point ICON_SIZE = new Point(20, 20);

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
     * using the image's dispose call. The image will not be automatically garbage collected. In the even of an error, a
     * default image is returned if <code>returnMissingImageOnError</code> is true, otherwise <code>null</code> is
     * returned.
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
         * Try to create the supplied image. If there is an SWT Exception try and create the default image if that was
         * requested. Return null if this fails.
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
