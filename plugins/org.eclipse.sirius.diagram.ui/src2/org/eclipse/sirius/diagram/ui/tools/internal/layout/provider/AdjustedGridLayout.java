/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.layout.provider;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.GridLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.ArrangeAllWithAutoSize;

/**
 * Specialization of the {@link GridLayoutProvider} to handle the specific
 * layout adjustements made on the arrange all.
 * 
 * @author cbrun
 * 
 */
public class AdjustedGridLayout extends GridLayoutProvider {
    /**
     * {@inheritDoc}
     */
    @Override
    protected Command createChangeBoundsCommand(final IGraphicalEditPart editPart, final Point newPosition) {
        Command result = null;
        final Command settingBounds = super.createChangeBoundsCommand(editPart, newPosition);
        if (ArrangeAllWithAutoSize.isEnabled() && ArrangeAllWithAutoSize.shouldBeAutosized(editPart)) {
            final Command autoSizeCmd = editPart.getCommand(new Request(RequestConstants.REQ_AUTOSIZE));
            if (settingBounds != null) {
                final CompoundCommand cpd = new CompoundCommand();
                cpd.add(settingBounds);
                cpd.add(autoSizeCmd);
                result = cpd;

            } else {
                result = autoSizeCmd;
            }

        } else {
            result = settingBounds;
        }
        return result;
    }

}
