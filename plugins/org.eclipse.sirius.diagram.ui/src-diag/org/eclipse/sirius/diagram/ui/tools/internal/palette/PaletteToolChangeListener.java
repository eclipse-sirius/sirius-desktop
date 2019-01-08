/*******************************************************************************
 * Copyright (c) 2018-2019 Obeo.
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
package org.eclipse.sirius.diagram.ui.tools.internal.palette;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.tools.api.management.ToolChangeListener;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette.PaletteManager;

/**
 * This listener launch an update of the attached {@link PaletteManager} when tools are updated.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public final class PaletteToolChangeListener implements ToolChangeListener {

    private PaletteManager paletteManager;

    private Diagram diagram;

    /**
     * Init the listener.
     * 
     * @param paletteManager
     *            the {@link PaletteManager} from which listening will be done.
     * @param diagram
     *            the diagram from which tool changes will be listened to.
     */
    public PaletteToolChangeListener(PaletteManager paletteManager, Diagram diagram) {
        super();
        this.paletteManager = paletteManager;
        this.diagram = diagram;
    }

    @Override
    public void notifyToolChange(ChangeKind changeKind) {
        if (paletteManager != null && !paletteManager.isDisposed()) {
            if (changeKind == ChangeKind.VSM_UPDATE) {
                paletteManager.update((DDiagram) diagram.getElement(), true);
            } else
                paletteManager.update((DDiagram) diagram.getElement());
        }
    }
}
