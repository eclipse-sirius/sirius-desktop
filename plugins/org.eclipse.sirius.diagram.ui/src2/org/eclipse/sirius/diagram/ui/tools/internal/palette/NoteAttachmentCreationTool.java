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

import org.eclipse.gmf.runtime.diagram.ui.tools.ConnectionCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * Specific ConnectionCreationTool to have the same behavior as usual in
 * Sirius (2 clicks for link creation instead of one click with drag).
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
}
