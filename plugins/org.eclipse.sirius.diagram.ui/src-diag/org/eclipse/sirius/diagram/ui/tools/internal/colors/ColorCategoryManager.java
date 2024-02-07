/*******************************************************************************
 * Copyright (c) 2024 Obeo.
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
package org.eclipse.sirius.diagram.ui.tools.internal.colors;

import java.util.List;

import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.ColorPalettePopup;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;
import org.eclipse.swt.graphics.RGB;

/**
 * This interface provides some methods to manage Color Category defined in the {@link ColorPalettePopup}:
 * <ul>
 * <li>Last used colors, the ten last colors used by the user. Persisted in preferences by default.</li>
 * <li>Custom colors, the colors defined by the user. Persisted in aird by default.</li>
 * <li>Suggested colors, the ten preferred colors defined in the VSM. Persisted in aird by default.</li>
 * <li>Basic colors, ten useful colors. Not persisted by default.</li>
 * </ul>
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public interface ColorCategoryManager {

    /**
     * The source value used to retrieve the {@link DAnnotationEntry} for the "Custom Colors" category of the "Fill
     * Color" property.
     */
    String FILL_CUSTOM_COLORS_ANNOTATION_SOURCE_NAME = "FillCustomColors"; //$NON-NLS-1$

    /**
     * The source value used to retrieve the {@link DAnnotationEntry} for the "Suggested Colors" category of the "Fill
     * Color" property.
     */
    String FILL_SUGGESTED_COLORS_ANNOTATION_SOURCE_NAME = "FillSuggestedColors"; //$NON-NLS-1$

    /**
     * The source value used to retrieve the {@link DAnnotationEntry} for the "Custom Colors" category of the "Font
     * Color" property.
     */
    String FONT_CUSTOM_COLORS_ANNOTATION_SOURCE_NAME = "FontCustomColors"; //$NON-NLS-1$

    /**
     * The source value used to retrieve the {@link DAnnotationEntry} for the "Suggested Colors" category of the "Font
     * Color" property.
     */
    String FONT_SUGGESTED_COLORS_ANNOTATION_SOURCE_NAME = "FontSuggestedColors"; //$NON-NLS-1$

    /**
     * The source value used to retrieve the {@link DAnnotationEntry} for the "Custom Colors" category of the "Line
     * Color" property.
     */
    String LINE_CUSTOM_COLORS_ANNOTATION_SOURCE_NAME = "LineCustomColors"; //$NON-NLS-1$

    /**
     * The source value used to retrieve the {@link DAnnotationEntry} for the "Suggested Colors" category of the "Line
     * Color" property.
     */
    String LINE_SUGGESTED_COLORS_ANNOTATION_SOURCE_NAME = "LineSuggestedColors"; //$NON-NLS-1$

    /**
     * Returns the list of last colors used by the user.
     * 
     * @return the list of last colors used by the user.
     */
    List<RGB> getLastUsedColors();

    /**
     * Returns the list of custom colors defined by the user.
     * 
     * @return the list of custom colors defined by the user.
     */
    List<RGB> getCustomColors();

    /**
     * Returns the list of the ten preferred colors defined in the VSM.
     * 
     * @return the list of the ten preferred colors defined in the VSM.
     */
    List<RGB> getSuggestedColors();

    /**
     * Returns ten useful colors.
     * 
     * @return ten useful colors.
     */
    List<RGB> getBasicColors();

    /**
     * Adds a color to the list of last used colors.
     * 
     * @param lastUsedColor
     *            the color to add to "Last Used" category.
     */
    void addLastUsedColor(RGB lastUsedColor);

    /**
     * Sets the list of custom colors defined by the user.
     * 
     * @param customColorsList
     *            the list of custom colors to set.
     */
    void setCustomColors(List<RGB> customColorsList);

    /**
     * Sets the list of the preferred suggested colors.
     * 
     * @param suggestedColorsList
     *            the list of suggested colors to set.
     */
    void setSuggestedColors(List<RGB> suggestedColorsList);

    /**
     * The list of colors used for all objects selected in the representation for a specific propertyId
     * ("notation.FillStyle.fillColor", "notation.LineStyle.lineColor", or "notation.FontStyle.fontColor").
     * 
     * @return the list of colors used for all objects selected in the representation for a specific propertyId.
     */
    List<RGB> getSelectedColorsByPropertyId();
}
