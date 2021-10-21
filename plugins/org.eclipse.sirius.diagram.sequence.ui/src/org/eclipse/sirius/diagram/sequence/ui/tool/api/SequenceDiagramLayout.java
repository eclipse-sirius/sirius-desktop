/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.api;

import java.util.Objects;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.SequenceLayout;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;

/**
 * API class to allow clients to control some aspects of the layout of sequence
 * diagrams graphically.
 * 
 * @author pcdavid
 */
public class SequenceDiagramLayout {
    private final DiagramEditPart sequenceDiagram;

    /**
     * Constructor.
     * 
     * @param sequenceDiagram
     *            the sequence diagram to manage.
     */
    public SequenceDiagramLayout(DiagramEditPart sequenceDiagram) {
        this.sequenceDiagram = Objects.requireNonNull(sequenceDiagram);
    }

    /**
     * Updates the layout of the diagram so that the graphical coverage of
     * Interaction Uses and Combined Fragments match the corresponding semantic
     * coverage. This method should be called in the context of a transaction
     * when the semantic model has changed in a way which may modify the
     * semantic coverage of the elements on the diagram.
     */
    public void refreshGraphicalCoverage() {
        if (sequenceDiagram instanceof SequenceDiagramEditPart) {
            new SequenceLayout(sequenceDiagram.getDiagramView()).horizontalLayout(false);
        }
    }
}
