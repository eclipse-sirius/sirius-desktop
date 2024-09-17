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
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.swt.widgets.Shell;

/**
 * Interface for providing a {@link IColorPalettePopup}.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public interface ColorPalettePopupProvider {

    /**
     * Determines if this provider can supply a {@link IColorPalettePopup} for the specified session.
     * 
     * @param session
     *            the active Sirius session.
     * @return <code>true</code> if this provider supports the given session, <code>false</code> otherwise.
     */
    boolean provides(Session session);

    /**
     * Creates and returns an instance of {@link IColorPalettePopup}.
     * 
     * @param parent
     *            the parent shell where the popup should appear on.
     * @param session
     *            the current sirius session.
     * @param editParts
     *            the list of selected edit parts.
     * @param propertyId
     *            the propertyID, which could be "notation.FillStyle.fillColor", "notation.LineStyle.lineColor", or
     *            "notation.FontStyle.fontColor".
     * @return the appropriate {@link IColorPalettePopup} instance.
     */
    IColorPalettePopup getColorPalettePopup(Shell parent, Session session, List<IGraphicalEditPart> editParts, String propertyId);

    /**
     * Returns the priority of this provider. Providers with higher priority are selected first by the
     * {@link ColorPalettePopupService#getColorPalettePopup}.
     * 
     * @return the priority, where zero indicates the lowest priority.
     */
    int getPriority();
}
