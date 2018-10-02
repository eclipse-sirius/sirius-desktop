/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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

import org.eclipse.gmf.runtime.diagram.ui.internal.parts.PaletteToolTransferDragSourceListener;
import org.eclipse.gmf.runtime.gef.ui.palette.customize.PaletteViewerEx;
import org.eclipse.jface.util.TransferDragSourceListener;

/**
 * Sirius specific {@link PaletteViewerEx}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
@SuppressWarnings("restriction")
public class SiriusPaletteViewer extends PaletteViewerEx {

    /**
     * Overridden to not add native
     * {@link PaletteToolTransferDragSourceListener} of gmf.
     * 
     * NOTE.: it is to support creation from palette with drag'n drop
     * 
     * {@inheritDoc}
     */
    @Override
    public void addDragSourceListener(TransferDragSourceListener listener) {
        if (!(listener instanceof PaletteToolTransferDragSourceListener)) {
            super.addDragSourceListener(listener);
        }
    }

}
