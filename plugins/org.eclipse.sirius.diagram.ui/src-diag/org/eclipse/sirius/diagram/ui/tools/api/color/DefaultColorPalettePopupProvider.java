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
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.ColorPalettePopup;
import org.eclipse.swt.widgets.Shell;

/**
 * Defines the default provider for ColorPalettePopup.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public class DefaultColorPalettePopupProvider implements ColorPalettePopupProvider {

    @Override
    public boolean provides(Session session) {
        return true;
    }

    @Override
    public IColorPalettePopup getColorPalettePopup(Shell parent, Session session, List<IGraphicalEditPart> editParts, String propertyId) {
        return new ColorPalettePopup(parent, session, editParts, propertyId);
    }

    @Override
    public int getPriority() {
        return 0;
    }

}
