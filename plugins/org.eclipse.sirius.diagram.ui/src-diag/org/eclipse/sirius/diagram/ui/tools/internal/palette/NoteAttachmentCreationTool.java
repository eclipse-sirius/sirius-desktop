/*******************************************************************************
 * Copyright (c) 2010, 2022 THALES GLOBAL SERVICES.
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

import org.eclipse.gmf.runtime.diagram.ui.tools.ConnectionCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * Specific ConnectionCreationTool to have the same behavior as usual in Sirius (2 clicks for link creation instead of
 * one click with drag).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class NoteAttachmentCreationTool extends ConnectionCreationTool {

    /**
     * Default constructor.
     */
    public NoteAttachmentCreationTool() {
        super();
    }

    /**
     * Creates a new CreationTool with the given elementType.
     * 
     * @param elementType
     *            The type of the element to create.
     */
    public NoteAttachmentCreationTool(IElementType elementType) {
        super(elementType);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.tools.ConnectionCreationTool#handleButtonUp(int)
     */
    @Override
    protected boolean handleButtonUp(int button) {
        setCtrlKeyDown(getCurrentInput().isControlKeyDown());

        if (isInState(STATE_TERMINAL | STATE_INVALID)) {
            handleFinished();
        }
        return true;
    }

    /**
     * Overridden to make sure the exposeHelper is reset to null. <br/>
     * Otherwise QueuedAutoexpose is rescheduled from setAutoexposeHelper creating an infinite loop in
     * org.eclipse.gef.tools.TargetingTool.doAutoexpose()<br/>
     * This happens if the user has clicked near the border of the diagram. In this case, exposeHelper is set with
     * SiriusScroll and the NoteCreation is possible. So when the creation is done QueuedAutoexpose is executed in loop.
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.tools.ConnectionCreationTool#deactivate()
     */
    @Override
    protected boolean handleCreateConnection() {
        setAutoexposeHelper(null);
        return super.handleCreateConnection();
    }
}
