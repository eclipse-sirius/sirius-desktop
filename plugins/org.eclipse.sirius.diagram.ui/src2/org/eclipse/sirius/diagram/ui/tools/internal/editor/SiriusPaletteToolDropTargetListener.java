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
package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.Tool;
import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gmf.runtime.diagram.ui.internal.services.palette.PaletteToolEntry;
import org.eclipse.sirius.diagram.ui.tools.internal.palette.CreationTool;

/**
 * A Sirius specific {@link TemplateTransferDropTargetListener} to support
 * drag'n drop from the palette.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
@SuppressWarnings("restriction")
public class SiriusPaletteToolDropTargetListener extends TemplateTransferDropTargetListener {

    /**
     * Default constructor.
     * 
     * @param viewer
     *            the {@link EditPartViewer} on which ask a creation from a
     *            request.
     */
    public SiriusPaletteToolDropTargetListener(EditPartViewer viewer) {
        super(viewer);
    }

    /**
     * If the template is a palette entry with a creation tool, then the request
     * from the creation tool is used.
     * 
     * {@inheritDoc}
     */
    protected Request createTargetRequest() {

        CreationTool tool = getCreationTool();

        if (tool != null) {

            tool.setViewer(getViewer());
            tool.setEditDomain(getViewer().getEditDomain());

            return tool.createTargetRequest();
        }

        return null;
    }

    /**
     * Gets the creation tool from the template if it is a palette entry.
     * 
     * @return The creation tool or null if not applicable.
     */
    private CreationTool getCreationTool() {
        Object template = TemplateTransfer.getInstance().getTemplate();
        if (template instanceof PaletteToolEntry) {
            Tool tool = ((PaletteToolEntry) template).createTool();
            if (tool instanceof CreationTool) {
                return (CreationTool) tool;
            }
        }
        return null;
    }

    @Override
    protected CreationFactory getFactory(Object template) {
        CreationFactory creationFactory = null;
        if (template instanceof PaletteToolEntry) {
            PaletteToolEntry paletteToolEntry = (PaletteToolEntry) template;
            Object toolProperty = paletteToolEntry.getToolProperty(CreationTool.PROPERTY_CREATION_FACTORY);
            if (toolProperty instanceof CreationFactory) {
                creationFactory = (CreationFactory) toolProperty;
            }
        }
        return creationFactory;
    }

}
