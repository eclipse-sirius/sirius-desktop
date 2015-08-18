/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.palette;

import java.lang.reflect.Field;

import org.eclipse.gef.Tool;
import org.eclipse.gmf.runtime.diagram.ui.services.palette.PaletteFactory.Adapter;
import org.eclipse.gmf.runtime.diagram.ui.util.INotationType;

/**
 * A palette factory for common viewpoint diagram entries. Currently, use only
 * for override the noteAttachment tool to make two clicks for create the note
 * attachment (as the other edge in Sirius).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class SiriusDiagramPaletteFactory extends Adapter {
    private static final String TOOL_NOTEATTACHMENT = "noteattachmentTool"; //$NON-NLS-1$

    /**
     * Store the DiagramNotationType.NOTE_ATTACHMENT. Because the constant is
     * not accessible as the same way in Eclipse 3.3
     * (org.eclipse.gmf.runtime.diagram
     * .ui.internal.util.DiagramNotationType.NOTE_ATTACHMENT) and in Eclipse 3.5
     * (org.eclipse.gmf.runtime.diagram.ui.type.DiagramNotationType.
     * NOTE_ATTACHMENT).
     */
    private INotationType noteAttachmentNotationType;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.palette.PaletteFactory.Adapter#createTool(java.lang.String)
     */
    @Override
    public Tool createTool(String toolId) {
        if (toolId.equals(TOOL_NOTEATTACHMENT)) {
            return new NoteAttachmentCreationTool(getNoteAttachmentNotationType());
        }
        return null;
    }

    private INotationType getNoteAttachmentNotationType() {
        if (noteAttachmentNotationType == null) {
            try {
                try {
                    Class<?> diagramNotationTypeClass = Class.forName("org.eclipse.gmf.runtime.diagram.ui.internal.util.DiagramNotationType"); //$NON-NLS-1$
                    // We are in eclipse 3.3
                    final Field searchField = diagramNotationTypeClass.getField("NOTE_ATTACHMENT"); //$NON-NLS-1$
                    noteAttachmentNotationType = (INotationType) searchField.get(null);
                } catch (final ClassNotFoundException cnfe) {
                    // We are in eclipse 3.5
                    try {
                        Class<?> diagramNotationTypeClass = Class.forName("org.eclipse.gmf.runtime.diagram.ui.type.DiagramNotationType"); //$NON-NLS-1$
                        final Field searchField = diagramNotationTypeClass.getField("NOTE_ATTACHMENT"); //$NON-NLS-1$
                        noteAttachmentNotationType = (INotationType) searchField.get(null);
                    } catch (final ClassNotFoundException cnfe2) {
                        // Do nothing
                    }
                }
            } catch (final SecurityException e) {
                // Do nothing
            } catch (final NoSuchFieldException e) {
                // Do nothing
            } catch (final IllegalArgumentException e) {
                // Do nothing
            } catch (final IllegalAccessException e) {
                // Do nothing
            }
        }
        return noteAttachmentNotationType;
    }
}
