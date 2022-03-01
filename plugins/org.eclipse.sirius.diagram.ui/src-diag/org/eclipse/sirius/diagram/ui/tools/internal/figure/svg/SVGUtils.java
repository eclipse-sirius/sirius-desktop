/**
 * Copyright (c) 2008, 2022 Borland Software Corporation
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Dmitry Stadnik - initial API and implementation
 *    Laurent Redor (Obeo) <laurent.redor@obeo.fr>  - Extract from plug-in org.eclipse.gmf.runtime.lite.svg
 */
package org.eclipse.sirius.diagram.ui.tools.internal.figure.svg;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import org.eclipse.draw2d.Graphics;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;

//CHECKSTYLE:OFF
public class SVGUtils {

    private SVGUtils() {
    }

    /**
     * Converts an AWT based buffered image into an SWT <code>Image</code>. This
     * will always return an <code>Image</code> that has 24 bit depth regardless
     * of the type of AWT buffered image that is passed into the method.
     * 
     * @param awtImage
     *            the {@link java.awt.image.BufferedImage} to be converted to an
     *            <code>Image</code>
     * @return an <code>Image</code> that represents the same image data as the
     *         AWT <code>BufferedImage</code> type.
     */
    public static org.eclipse.swt.graphics.Image toSWT(Device device, BufferedImage awtImage) {
        // We can force bitdepth to be 24 bit because BufferedImage getRGB
        // allows us to always retrieve 24 bit data regardless of source color
        // depth.
        PaletteData palette = new PaletteData(0xFF0000, 0xFF00, 0xFF);
        ImageData swtImageData = new ImageData(awtImage.getWidth(), awtImage.getHeight(), 24, palette);
        // Ensure scansize is aligned on 32 bit.
        int scansize = (((awtImage.getWidth() * 3) + 3) * 4) / 4;
        WritableRaster alphaRaster = awtImage.getAlphaRaster();
        byte[] alphaBytes = new byte[awtImage.getWidth()];
        for (int y = 0; y < awtImage.getHeight(); y++) {
            int[] buff = awtImage.getRGB(0, y, awtImage.getWidth(), 1, null, 0, scansize);
            swtImageData.setPixels(0, y, awtImage.getWidth(), buff, 0);
            if (alphaRaster != null) {
                int[] alpha = alphaRaster.getPixels(0, y, awtImage.getWidth(), 1, (int[]) null);
                for (int i = 0; i < awtImage.getWidth(); i++) {
                    alphaBytes[i] = (byte) alpha[i];
                }
                swtImageData.setAlphas(0, y, awtImage.getWidth(), alphaBytes, 0);
            }
        }
        return new org.eclipse.swt.graphics.Image(device, swtImageData);
    }

    public static Object getAntialiasHint(Graphics graphics) {
        int aa = SWT.DEFAULT;
        try {
            if (graphics != null) {
                aa = graphics.getAntialias();
            }
        } catch (Exception e) {
            // not supported
        }
        Object aaHint;
        if (aa == SWT.ON) {
            aaHint = RenderingHints.VALUE_ANTIALIAS_ON;
        } else if (aa == SWT.OFF) {
            aaHint = RenderingHints.VALUE_ANTIALIAS_OFF;
        } else {
            aaHint = RenderingHints.VALUE_ANTIALIAS_DEFAULT;
        }
        return aaHint;
    }

    public static Object getTextAntialiasHint(Graphics graphics) {
        int aa = SWT.DEFAULT;
        try {
            if (graphics != null) {
                aa = graphics.getTextAntialias();
            }
        } catch (Exception e) {
            // not supported
        }
        Object aaHint;
        if (aa == SWT.ON) {
            aaHint = RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
        } else if (aa == SWT.OFF) {
            aaHint = RenderingHints.VALUE_TEXT_ANTIALIAS_OFF;
        } else {
            aaHint = RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT;
        }
        return aaHint;
    }
}
// CHECKSTYLE:ON
