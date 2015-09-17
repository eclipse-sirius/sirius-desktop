/**
 * Copyright (c) 2008 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dmitry Stadnik - initial API and implementation
 *    Laurent Redor (Obeo) <laurent.redor@obeo.fr>  - Extract from plug-in org.eclipse.gmf.runtime.lite.svg
 */
package org.eclipse.sirius.diagram.ui.tools.internal.figure.svg;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import org.apache.batik.svggen.SVGColor;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGPaintDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.widgets.Display;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSValue;
import org.w3c.dom.css.RGBColor;
import org.w3c.dom.css.ViewCSS;
import org.w3c.dom.svg.SVGPaint;

//CHECKSTYLE:OFF
public class SVGUtils {

    private SVGUtils() {
    }

    public static String toSVGColor(Document document, Color color) {
        java.awt.Color awtColor = new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue());
        SVGGeneratorContext svgContext = SVGGeneratorContext.createDefault(document);
        SVGPaintDescriptor paint = SVGColor.toSVG(awtColor, svgContext);
        return paint.getPaintValue();
    }

    public static Color toSWTColor(Element element, String attributeName) {
        Document document = element.getOwnerDocument();
        ViewCSS viewCSS = (ViewCSS) document.getDocumentElement();
        CSSStyleDeclaration computedStyle = viewCSS.getComputedStyle(element, null);
        SVGPaint svgPaint = (SVGPaint) computedStyle.getPropertyCSSValue(attributeName);
        if (svgPaint.getPaintType() == SVGPaint.SVG_PAINTTYPE_RGBCOLOR) {
            RGBColor rgb = svgPaint.getRGBColor();
            float red = rgb.getRed().getFloatValue(CSSValue.CSS_PRIMITIVE_VALUE);
            float green = rgb.getGreen().getFloatValue(CSSValue.CSS_PRIMITIVE_VALUE);
            float blue = rgb.getBlue().getFloatValue(CSSValue.CSS_PRIMITIVE_VALUE);
            return new Color(Display.getCurrent(), (int) red, (int) green, (int) blue);
        }
        return null;
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
}
// CHECKSTYLE:ON
