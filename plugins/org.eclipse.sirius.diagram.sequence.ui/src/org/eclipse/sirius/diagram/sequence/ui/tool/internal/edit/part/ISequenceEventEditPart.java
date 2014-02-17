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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part;

import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;

/**
 * Common interface for all the elements of a sequence diagram which represent
 * an event associated to a (logical) time interval and thus a range of vertical
 * coordinates. This includes lifelines (considered as a special case of
 * executions), executions and messages.
 * 
 * @author pcdavid, smonnier, mporhel
 */
public interface ISequenceEventEditPart extends IDiagramElementEditPart {
    /**
     * Returns the {@link ISequenceEvent} this {@link ISequenceEventEditPart}.
     * 
     * @return the {@link ISequenceEvent} this {@link ISequenceEventEditPart}.
     */
    ISequenceEvent getISequenceEvent();
}
