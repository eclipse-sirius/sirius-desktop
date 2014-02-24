/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.editor;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.palette.PaletteManager;

/**
 * A {@link Runnable} to update the {@link PaletteManager} in the ui thread.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class PaletteManagerUpdater implements Runnable {

    private PaletteManager paletteManager;

    private Diagram gmfDiagram;

    private boolean clean;

    /**
     * Default constructor.
     * 
     * @param paletteManager
     *            the {@link PaletteManager} to update
     * @param gmfDiagram
     *            the {@link Diagram} used for the update the diagram
     * @param clean
     *            true to clean the palette before the update.
     */
    public PaletteManagerUpdater(PaletteManager paletteManager, Diagram gmfDiagram, boolean clean) {
        this.paletteManager = paletteManager;
        this.gmfDiagram = gmfDiagram;
        this.clean = clean;
    }

    /**
     * Overridden to update the {@link PaletteManager}.
     * 
     * {@inheritDoc}
     */
    public void run() {
        if (!paletteManager.isDisposed()) {
            paletteManager.update(gmfDiagram, clean);
        }
    }

}
