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

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;
import org.eclipse.swt.graphics.RGB;

/**
 * This class is used to manage the color categories for the "Line Color" property.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public class LineColorCategoryManager extends AbstractColorCategoryManager implements ColorCategoryManager {

    /**
     * The source value used to retrieve the {@link DAnnotationEntry} for the "Custom Colors" category of the "Line
     * Color" property.
     */
    private static final String LINE_CUSTOM_COLORS_ANNOTATION_SOURCE_NAME = "LineCustomColors"; //$NON-NLS-1$

    /**
     * The source value used to retrieve the {@link DAnnotationEntry} for the "Suggested Colors" category of the "Line
     * Color" property.
     */
    private static final String LINE_SUGGESTED_COLORS_ANNOTATION_SOURCE_NAME = "LineSuggestedColors"; //$NON-NLS-1$

    /**
     * Creates an instance of {@link LineColorCategoryManager}.
     * 
     * @param session
     *            the current sirius session.
     * @param editParts
     *            the list of selected edit parts.
     * @param propertyId
     *            the propertyID, "notation.LineStyle.lineColor", used to retrieve all "Line Colors" used for the
     *            selected editParts.
     */
    public LineColorCategoryManager(Session session, List<IGraphicalEditPart> editParts, String propertyId) {
        super(session, editParts, propertyId);
    }

    @Override
    public List<RGB> getLastUsedColors() {
        return super.getLastUsedColors(SiriusDiagramUiPreferencesKeys.PREF_LINE_LAST_USED_COLORS.name());
    }

    @Override
    public List<RGB> getCustomColors() {
        return super.getColors(LINE_CUSTOM_COLORS_ANNOTATION_SOURCE_NAME);
    }

    @Override
    public List<RGB> getSuggestedColors() {
        return super.getColors(LINE_SUGGESTED_COLORS_ANNOTATION_SOURCE_NAME);
    }

    @Override
    public void addLastUsedColor(RGB lastUsedColor) {
        super.addLastUsedColor(lastUsedColor, SiriusDiagramUiPreferencesKeys.PREF_LINE_LAST_USED_COLORS.name());
    }

    @Override
    public void setCustomColors(List<RGB> customColorsList) {
        super.setColors(LINE_CUSTOM_COLORS_ANNOTATION_SOURCE_NAME, customColorsList);
    }

    @Override
    public void setSuggestedColors(List<RGB> suggestedColorsList) {
        super.setColors(LINE_SUGGESTED_COLORS_ANNOTATION_SOURCE_NAME, suggestedColorsList);
    }

}
