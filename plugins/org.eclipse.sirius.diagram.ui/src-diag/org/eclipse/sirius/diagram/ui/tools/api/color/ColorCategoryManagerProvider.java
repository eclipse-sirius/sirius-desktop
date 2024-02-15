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
package org.eclipse.sirius.diagram.ui.tools.api.color;

import java.util.List;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.util.PackageUtil;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.ui.tools.internal.colors.FillColorCategoryManager;
import org.eclipse.sirius.diagram.ui.tools.internal.colors.FontColorCategoryManager;
import org.eclipse.sirius.diagram.ui.tools.internal.colors.LineColorCategoryManager;

/**
 * This class is used to provides a {@link ColorCategoryManager}, such as {@link FillColorCategoryManager},
 * {@link LineColorCategoryManager}, or {@link FontColorCategoryManager}.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public class ColorCategoryManagerProvider {

    /**
     * The propertyID for "Fill Color": "notation.FillStyle.fillColor".
     */
    private static final String FILL_COLOR_PROPERTY_ID = PackageUtil.getID(NotationPackage.eINSTANCE.getFillStyle_FillColor());

    /**
     * The propertyID for "Line Color": "notation.LineStyle.lineColor".
     */
    private static final String LINE_COLOR_PROPERTY_ID = PackageUtil.getID(NotationPackage.eINSTANCE.getLineStyle_LineColor());

    /**
     * The propertyID for "Font Color": "notation.FontStyle.fontColor".
     */
    private static final String FONT_COLOR_PROPERTY_ID = PackageUtil.getID(NotationPackage.eINSTANCE.getFontStyle_FontColor());

    /**
     * Returns the {@link ColorCategoryManager} to use with the specified propertyID.
     * 
     * @param session
     *            the current sirius session.
     * @param editParts
     *            the list of selected edit parts.
     * @param propertyId
     *            the propertyID, which could be "notation.FillStyle.fillColor", "notation.LineStyle.lineColor", or
     *            "notation.FontStyle.fontColor".
     * @return the {@link ColorCategoryManager} to use with the specified propertyID.
     */
    public ColorCategoryManager getColorCategoryManager(Session session, List<IGraphicalEditPart> editParts, String propertyId) {
        ColorCategoryManager colorCategoryManager = null;
        if (session != null) {
            if (FILL_COLOR_PROPERTY_ID.equals(propertyId)) {
                colorCategoryManager = new FillColorCategoryManager(session, editParts);
            } else if (LINE_COLOR_PROPERTY_ID.equals(propertyId)) {
                colorCategoryManager = new LineColorCategoryManager(session, editParts);
            } else if (FONT_COLOR_PROPERTY_ID.equals(propertyId)) {
                colorCategoryManager = new FontColorCategoryManager(session, editParts);
            }
        }
        return colorCategoryManager;
    }

}
