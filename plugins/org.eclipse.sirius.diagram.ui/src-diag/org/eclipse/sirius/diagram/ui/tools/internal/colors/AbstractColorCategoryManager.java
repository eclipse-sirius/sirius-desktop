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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.core.util.PackageUtil;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.color.ColorCategoryManager;
import org.eclipse.sirius.diagram.ui.tools.api.color.ColorManager;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;
import org.eclipse.sirius.viewpoint.description.SystemColors;
import org.eclipse.swt.graphics.RGB;

/**
 * This abstract class provides some useful methods to manage colors of all color categories.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public abstract class AbstractColorCategoryManager implements ColorCategoryManager {

    /**
     * The source value used to retrieve the {@link DAnnotationEntry} for the "Custom Colors" category of the "Fill
     * Color" property.
     */
    public static final String FILL_CUSTOM_COLORS_ANNOTATION_SOURCE_NAME = "FillCustomColors"; //$NON-NLS-1$

    /**
     * The source value used to retrieve the {@link DAnnotationEntry} for the "Suggested Colors" category of the "Fill
     * Color" property.
     */
    public static final String FILL_SUGGESTED_COLORS_ANNOTATION_SOURCE_NAME = "FillSuggestedColors"; //$NON-NLS-1$

    /**
     * The source value used to retrieve the {@link DAnnotationEntry} for the "Custom Colors" category of the "Font
     * Color" property.
     */
    public static final String FONT_CUSTOM_COLORS_ANNOTATION_SOURCE_NAME = "FontCustomColors"; //$NON-NLS-1$

    /**
     * The source value used to retrieve the {@link DAnnotationEntry} for the "Suggested Colors" category of the "Font
     * Color" property.
     */
    public static final String FONT_SUGGESTED_COLORS_ANNOTATION_SOURCE_NAME = "FontSuggestedColors"; //$NON-NLS-1$

    /**
     * The source value used to retrieve the {@link DAnnotationEntry} for the "Custom Colors" category of the "Line
     * Color" property.
     */
    public static final String LINE_CUSTOM_COLORS_ANNOTATION_SOURCE_NAME = "LineCustomColors"; //$NON-NLS-1$

    /**
     * The source value used to retrieve the {@link DAnnotationEntry} for the "Suggested Colors" category of the "Line
     * Color" property.
     */
    public static final String LINE_SUGGESTED_COLORS_ANNOTATION_SOURCE_NAME = "LineSuggestedColors"; //$NON-NLS-1$

    /**
     * A list of ten useful colors used for the "Basic" color category.
     */
    private static final List<SystemColors> BASIC_COLORS = Arrays.asList(SystemColors.BLACK_LITERAL, SystemColors.DARK_GRAY_LITERAL, SystemColors.LIGHT_GRAY_LITERAL, SystemColors.WHITE_LITERAL,
            SystemColors.RED_LITERAL, SystemColors.ORANGE_LITERAL, SystemColors.YELLOW_LITERAL, SystemColors.GREEN_LITERAL, SystemColors.BLUE_LITERAL, SystemColors.PURPLE_LITERAL);

    /**
     * Maximum number of "Last Used" colors to store.
     */
    private static final int NB_MAX_LAST_USED_COLORS = 10;

    /**
     * Maximum number of "Suggested" colors to store.
     */
    private static final int NB_MAX_SUGGESTED_COLORS = 10;

    /**
     * The separator between each integer value of RGB color.
     */
    private static final String RGB_VALUES_SEPARATOR = ","; //$NON-NLS-1$

    /**
     * A helper used to retrieves or modify colors persisted in the aird file.
     */
    protected ColorsAnnotationHelper colorsAnnotationHelper;

    /**
     * The list of selected edit parts.
     */
    private List<IGraphicalEditPart> editParts;

    /**
     * Constructor which should be used by subclasses.
     * 
     * @param session
     *            the current sirius session.
     * @param editParts
     *            the list of selected edit parts.
     */
    public AbstractColorCategoryManager(Session session, List<IGraphicalEditPart> editParts) {
        this.editParts = editParts;
        this.colorsAnnotationHelper = new ColorsAnnotationHelper(session);
    }

    @Override
    public List<RGB> getBasicColors() {
        List<RGB> basicColors = new LinkedList<>();
        Map<String, RGB> systemColors = VisualBindingManager.getDefault().getSystemPalette();
        for (SystemColors color : BASIC_COLORS) {
            basicColors.add(systemColors.get(color.getName()));
        }
        return basicColors;
    }

    /**
     * Retrieves the ten last used colors for one of the "Font Color", "Fill Color", or "Line Color" property. Each of
     * these property has a preference defined to store its last used colors.
     * 
     * @param lastUsedColorsPreference
     *            the preference used to retrieve the colors of the property.
     * @return the ten last used colors for one of the "Font Color", "Fill Color", or "Line Color" property.
     */
    protected List<RGB> getLastUsedColors(String lastUsedColorsPreference) {
        String lastUsedColorsString = DiagramUIPlugin.getPlugin().getPreferenceStore().getString(lastUsedColorsPreference);
        List<RGB> lastUsedColors = new LinkedList<>();
        if (!lastUsedColorsString.isEmpty()) {
            for (String s : lastUsedColorsString.split("},")) { //$NON-NLS-1$
                RGB color = ColorManager.getDefault().stringToRGB(s + "}"); //$NON-NLS-1$
                lastUsedColors.add(color);
            }
        }
        return lastUsedColors;
    }

    /**
     * Adds a color to the "Last Used" colors category for one of the "Font Color", "Fill Color", or "Line Color"
     * property.
     * 
     * @param lastUsedColor
     *            the color to add.
     * @param lastUsedColorsPreference
     *            the preference used to retrieve the colors of the property.
     */
    protected void addLastUsedColor(RGB lastUsedColor, String lastUsedColorsPreference) {
        List<RGB> lastUsedColors = getLastUsedColors(lastUsedColorsPreference);
        if (lastUsedColor != null) {
            if (!lastUsedColors.contains(lastUsedColor)) {
                lastUsedColors.add(0, lastUsedColor);
            } else {
                lastUsedColors.remove(lastUsedColor);
                lastUsedColors.add(0, lastUsedColor);
            }
            String lastUsedColorsString = new String();
            for (int i = 0; i < lastUsedColors.size() && i < NB_MAX_LAST_USED_COLORS; i++) {
                RGB rgb = lastUsedColors.get(i);
                lastUsedColorsString = lastUsedColorsString + ColorManager.getDefault().rgbToString(rgb) + RGB_VALUES_SEPARATOR;
            }
            IPreferenceStore preferenceStore = DiagramUIPlugin.getPlugin().getPreferenceStore();
            preferenceStore.putValue(lastUsedColorsPreference, lastUsedColorsString);
        }
    }

    /**
     * Used to get the list of colors of the specified category.
     * 
     * @param colorsAnnotationSourceName
     *            the label used to store the annotation containing the colors.
     * @return the list of colors of the specified category.
     */
    protected List<RGB> getColors(String colorsAnnotationSourceName) {
        return colorsAnnotationHelper.getColorsDetails(colorsAnnotationSourceName);
    }

    /**
     * Used to set the list of colors of the specified category.
     * 
     * @param colorsAnnotationSourceName
     *            the label used to store the annotation containing the colors.
     * @param colorsList
     *            the list to set.
     */
    protected void setColors(String colorsAnnotationSourceName, List<RGB> colorsList) {
        if (colorsList != null) {
            colorsAnnotationHelper.setColorsDetails(colorsAnnotationSourceName, colorsList);
        }
    }

    /**
     * Used to set the list of suggested colors.
     * 
     * @param colorsAnnotationSourceName
     *            the label used to store the annotation containing the colors.
     * @param colorsList
     *            the list to set.
     */
    protected void setSuggestedColors(String colorsAnnotationSourceName, List<RGB> colorsList) {
        if (colorsList != null) {
            List<RGB> newColorsList;
            if (colorsList.size() > NB_MAX_SUGGESTED_COLORS) {
                newColorsList = new ArrayList<>(colorsList.subList(0, NB_MAX_SUGGESTED_COLORS));
            } else {
                newColorsList = new ArrayList<>(colorsList);
            }
            setColors(colorsAnnotationSourceName, newColorsList);
        }
    }

    /**
     * Returns the list of colors used for all objects selected in the representation for a specific propertyId
     * ("notation.FillStyle.fillColor", "notation.LineStyle.lineColor", or "notation.FontStyle.fontColor").
     * 
     * @param propertyId
     *            the propertyID, which could be "notation.FillStyle.fillColor", "notation.LineStyle.lineColor", or
     *            "notation.FontStyle.fontColor".
     * @return the list of colors used for all objects selected in the representation for a specific propertyId.
     */
    protected List<RGB> getSelectedColorsByPropertyId(String propertyId) {
        List<RGB> selectedColorsByPropertyId = Collections.EMPTY_LIST;
        if (editParts != null) {
            final EStructuralFeature feature = (EStructuralFeature) PackageUtil.getElement(propertyId);
            selectedColorsByPropertyId = editParts.stream() //
                    .map(ep -> ep.getStructuralFeatureValue(feature)) //
                    .filter(Integer.class::isInstance) //
                    .map(Integer.class::cast) //
                    .map(colorInteger -> FigureUtilities.integerToRGB(colorInteger)) //
                    .distinct() //
                    .toList();
        }
        return selectedColorsByPropertyId;
    }

}
