/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.color.ColorManager;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.Color;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * A {@link BundledImageFigure} is a Figure corresponding to an Image defined in
 * a plugin.
 * 
 * @author cbrun
 */
public class BundledImageFigure extends AbstractCachedSVGFigure {

    /**
     * The stroke tag in the SVG file.
     */
    private static final String SVG_STROKE = "stroke"; //$NON-NLS-1$

    /**
     * The fill tag in the SVG file.
     */
    private static final String SVG_FILL = "fill"; //$NON-NLS-1$

    /**
     * The stop-color tag in the SVG file.
     */
    private static final String SVG_STOP_COLOR = "stop-color"; //$NON-NLS-1$

    /**
     * The name of the style attribute in the SVG file.
     */
    private static final String SVG_STYLE_ATTRIBUTE_NAME = "style"; //$NON-NLS-1$

    /**
     * The id of the lighter stop color of the gradient in the SVG file.
     */
    private static final String SVG_STOP_LIGHTER_ID = "stop1"; //$NON-NLS-1$

    /**
     * The id of the main stop color of the gradient in the SVG file.
     */
    private static final String SVG_STOP_MAIN_ID = "stop2"; //$NON-NLS-1$

    /**
     * The id of the gradient element in the SVG file.
     */
    private static final String SVG_GRADIENT_ELEMENT_ID = "elementWithGradient"; //$NON-NLS-1$

    /**
     * The id of the shadow element in the SVG file.
     */
    private static final String SVG_SHADOW_ELEMENT_ID = "shadow"; //$NON-NLS-1$

    private static final String IMAGE_DIR = "images/"; //$NON-NLS-1$

    private static final String IMAGE_EXT = ".svg"; //$NON-NLS-1$

    /**
     * * The actual shapeName use to draw the SVG figure
     */
    private String shapeName;

    /**
     * The actual border color use to draw the SVG figure
     */
    private String mainBorderColor;

    /**
     * The actual lighter border color use to draw the shadow of SVG figure
     */
    private String lighterBorderColor;

    /**
     * The actual lighter gradient color use to draw the SVG figure
     */
    private String lighterGradientColor;

    /**
     * The actual main gradient color use to draw the SVG figure
     */
    private String mainGradientColor;

    /**
     * Build a new {@link BundledImageFigure} from an Image instance.
     * 
     */
    public BundledImageFigure() {
        this.setLayoutManager(new XYLayout());
    }

    /**
     * Create the {@link BundledImageFigure} from a {@link BundledImage}
     * instance.
     * 
     * @param bundle
     *            {@link BundledImage} specification.
     * @return new Figure.
     */
    public static IFigure createImageFigure(final BundledImage bundle) {
        final BundledImageFigure fig = new BundledImageFigure();
        fig.refreshFigure(bundle);
        return fig;
    }

    /**
     * @param bundle
     */
    private boolean updateShape(BundledImage bundledImage) {
        boolean updated = false;
        if (bundledImage != null && bundledImage.getShape() != null) {
            String newShapeName = bundledImage.getShape().getName();
            if (!StringUtil.isEmpty(newShapeName) && !newShapeName.equals(getShapeName())) {
                this.setURI(getImageFileURI(newShapeName), false);
                this.setShapeName(newShapeName);
                updated = true;
            }
        }
        return updated;
    }

    /**
     * @param bundledImage
     * @param force
     *            If the color must be force to refresh (in case of shape update
     *            for sample)
     */
    private boolean updateColors(BundledImage bundledImage, boolean force) {
        boolean updated = updateColorFields(bundledImage);
        updated = updateDocumentColors(force || updated);
        return updated;
    }

    private boolean updateColorFields(BundledImage bundledImage) {
        // Compute colors
        RGBValues color = bundledImage.getColor();
        Color newLighterColor = ColorManager.getDefault().getLighterColor(color);

        RGBValues borderColor = bundledImage.getBorderColor();
        Color newBorderLighterColor = ColorManager.getDefault().getLighterColor(borderColor);

        // Get Hexa values
        String hexaColor = getRGBValuesColorToHexa(color);
        String hexaLighterColor = getColorToHexa(newLighterColor);
        String hexaBorderColor = getRGBValuesColorToHexa(borderColor);
        String hexaLighterBorderColor = getColorToHexa(newBorderLighterColor);

        boolean updated = false;

        if (hexaColor != null && (!hexaColor.equals(this.getMainGradientColor()))) {
            this.setMainGradientColor(hexaColor);
            updated = true;
        }

        if (hexaLighterColor != null && (!hexaLighterColor.equals(this.getLighterGradientColor()))) {
            this.setLighterGradientColor(hexaLighterColor);
            updated = true;
        }

        if (hexaBorderColor != null && (!hexaBorderColor.equals(this.getMainBorderColor()))) {
            this.setMainBorderColor(hexaBorderColor);
            updated = true;
        }

        if (hexaLighterBorderColor != null && (!hexaLighterBorderColor.equals(this.getLighterBorderColor()))) {
            this.setLighterBorderColor(hexaLighterBorderColor);
            updated = true;
        }

        return updated;
    }

    private boolean updateDocumentColors(boolean needsUpdate) {
        boolean updated = false;
        if (needsUpdate) {
            setURI(getURI(), false);
            Document document = this.getDocument();
            if (document != null && needsUpdate) {
                /* Update the border color (if exists). */
                Element gradientStep1 = document.getElementById(BundledImageFigure.SVG_STOP_LIGHTER_ID);
                if (gradientStep1 != null) {
                    String gradientStep1Style = gradientStep1.getAttribute(BundledImageFigure.SVG_STYLE_ATTRIBUTE_NAME);
                    gradientStep1.setAttribute(BundledImageFigure.SVG_STYLE_ATTRIBUTE_NAME, getNewStyle(gradientStep1Style, BundledImageFigure.SVG_STOP_COLOR, getLighterGradientColor()));
                    updated = true;
                }

                /* Update the main gradient color (if exists). */
                Element gradientStep2 = document.getElementById(BundledImageFigure.SVG_STOP_MAIN_ID);
                if (gradientStep2 != null) {
                    String gradientStep2Style = gradientStep2.getAttribute(BundledImageFigure.SVG_STYLE_ATTRIBUTE_NAME);
                    gradientStep2.setAttribute(BundledImageFigure.SVG_STYLE_ATTRIBUTE_NAME, getNewStyle(gradientStep2Style, BundledImageFigure.SVG_STOP_COLOR, getMainGradientColor()));
                    updated = true;
                }

                /* Update the shadow border (if exists). */
                Element shadow = document.getElementById(SVG_SHADOW_ELEMENT_ID);
                if (shadow != null) {
                    String shadowStyle = shadow.getAttribute(BundledImageFigure.SVG_STYLE_ATTRIBUTE_NAME);
                    shadow.setAttribute(BundledImageFigure.SVG_STYLE_ATTRIBUTE_NAME, getNewStyle(shadowStyle, SVG_FILL, getLighterBorderColor()));
                    updated = true;
                }

                /* Update the border color (if exists). */
                Element elementWithGradient = document.getElementById(BundledImageFigure.SVG_GRADIENT_ELEMENT_ID);
                if (elementWithGradient != null) {
                    String elementWithGradientStyle = elementWithGradient.getAttribute(BundledImageFigure.SVG_STYLE_ATTRIBUTE_NAME);
                    elementWithGradient.setAttribute(BundledImageFigure.SVG_STYLE_ATTRIBUTE_NAME, getNewStyle(elementWithGradientStyle, BundledImageFigure.SVG_STROKE, getMainBorderColor()));
                    updated = true;
                }
            }
        }
        return updated;
    }

    /**
     * @param shapeName
     * @return
     */
    private static String getImageFileURI(String shapeName) {
        final String path = new StringBuffer(IMAGE_DIR).append(shapeName).append(IMAGE_EXT).toString();
        String pluginId = DiagramUIPlugin.getPlugin().getSymbolicName();
        return "platform:/plugin/" + pluginId + "/" + path; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * @param color
     *            The color to transform in hexa value
     * @return The hexa representation of the color.
     */
    private static String getRGBValuesColorToHexa(final RGBValues color) {
        String blankDigit = "0"; //$NON-NLS-1$
        StringBuffer colorInHexa = new StringBuffer();
        String hexaColor = Integer.toHexString(color.getRed());
        if (hexaColor.length() == 1) {
            colorInHexa.append(blankDigit);
        }
        colorInHexa.append(hexaColor);
        hexaColor = Integer.toHexString(color.getGreen());
        if (hexaColor.length() == 1) {
            colorInHexa.append(blankDigit);
        }
        colorInHexa.append(hexaColor);
        hexaColor = Integer.toHexString(color.getBlue());
        if (hexaColor.length() == 1) {
            colorInHexa.append(blankDigit);
        }
        colorInHexa.append(hexaColor);
        return colorInHexa.toString();
    }

    /**
     * @param color
     *            The color to transform in hexa value
     * @return The hexa representation of the color.
     */
    private String getColorToHexa(Color color) {
        String blankDigit = "0"; //$NON-NLS-1$
        StringBuffer colorInHexa = new StringBuffer();
        String hexaColor = Integer.toHexString(color.getRed());
        if (hexaColor.length() == 1) {
            colorInHexa.append(blankDigit);
        }
        colorInHexa.append(hexaColor);
        hexaColor = Integer.toHexString(color.getGreen());
        if (hexaColor.length() == 1) {
            colorInHexa.append(blankDigit);
        }
        colorInHexa.append(hexaColor);
        hexaColor = Integer.toHexString(color.getBlue());
        if (hexaColor.length() == 1) {
            colorInHexa.append(blankDigit);
        }
        colorInHexa.append(hexaColor);
        return colorInHexa.toString();
    }

    private static String getNewStyle(String actualStyle, String colorAttribute, String newColor) {
        int indexOfColorAttribute = actualStyle.indexOf(colorAttribute);
        String newStyle = actualStyle.substring(0, indexOfColorAttribute + colorAttribute.length() + 2);
        newStyle = newStyle.concat(newColor);
        newStyle = newStyle.concat(actualStyle.substring(actualStyle.indexOf(";", indexOfColorAttribute), actualStyle.length())); //$NON-NLS-1$
        return newStyle;
    }

    /**
     * refresh the figure.
     * 
     * @param bundledImage
     *            the image associated to the figure
     */
    public void refreshFigure(final BundledImage bundledImage) {
        if (bundledImage != null) {
            boolean updated = this.updateShape(bundledImage);
            updated = this.updateColors(bundledImage, updated) || updated;
            if (updated) {
                this.contentChanged();
            }
        } else {
            this.setURI(null);
        }
    }

    protected String getShapeName() {
        return shapeName;
    }

    protected void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }

    protected String getMainBorderColor() {
        return mainBorderColor;
    }

    protected void setMainBorderColor(String mainBorderColor) {
        this.mainBorderColor = mainBorderColor;
    }

    protected String getLighterBorderColor() {
        return lighterBorderColor;
    }

    protected void setLighterBorderColor(String lighterBorderColor) {
        this.lighterBorderColor = lighterBorderColor;
    }

    protected String getLighterGradientColor() {
        return lighterGradientColor;
    }

    protected void setLighterGradientColor(String lighterGradientColor) {
        this.lighterGradientColor = lighterGradientColor;
    }

    protected String getMainGradientColor() {
        return mainGradientColor;
    }

    protected void setMainGradientColor(String mainGradientColor) {
        this.mainGradientColor = mainGradientColor;
    }

    /**
     * Compute a key for this BundleImageFigure. This key is used to store in
     * cache the corresponding {@link org.eclipse.swt.graphics.Image}.
     * 
     * {@inheritDoc}
     * 
     * @return The key corresponding to this BundleImageFigure.
     */
    protected String getKey() {
        StringBuffer result = new StringBuffer();
        result.append(getDocumentKey());
        result.append(SEPARATOR);
        result.append(getSiriusAlpha());
        result.append(SEPARATOR);
        return result.toString();
    }

    /**
     * Compute a key for this BundleImageFigure. This key is used to store in
     * cache the corresponding {@link org.eclipse.swt.graphics.Image}.
     * 
     * {@inheritDoc}
     * 
     * @return The key corresponding to this BundleImageFigure.
     */
    protected String getDocumentKey() {
        StringBuffer result = new StringBuffer();
        result.append(super.getDocumentKey());
        result.append(SEPARATOR);
        result.append(shapeName);
        result.append(SEPARATOR);
        result.append(mainBorderColor);
        result.append(SEPARATOR);
        result.append(mainGradientColor);
        return result.toString();
    }
}
