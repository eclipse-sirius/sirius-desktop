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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.util;

import java.util.List;

import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;

import com.google.common.base.Preconditions;

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
        this.diagram = Preconditions.checkNotNull(diagram);
    }

    public List<LifelineEditPart> getAllLifelines() {
        return EditPartsHelper.getAllLifelines(diagram);
    }
}
