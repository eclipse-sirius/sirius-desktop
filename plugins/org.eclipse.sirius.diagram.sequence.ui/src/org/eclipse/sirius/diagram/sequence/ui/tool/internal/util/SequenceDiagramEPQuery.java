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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.util;

import java.util.List;
import java.util.Objects;

import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;

/**
 * Queries on a sequence diagram.
 * 
 * @author pcdavid
 */
public class SequenceDiagramEPQuery {
    private final SequenceDiagramEditPart diagram;

    /**
     * Constructor.
     * 
     * @param diagram
     *            the diagram to query.
     */
    public SequenceDiagramEPQuery(SequenceDiagramEditPart diagram) {
        this.diagram = Objects.requireNonNull(diagram);
    }

    public List<LifelineEditPart> getAllLifelines() {
        return EditPartsHelper.getAllLifelines(diagram);
    }
}
