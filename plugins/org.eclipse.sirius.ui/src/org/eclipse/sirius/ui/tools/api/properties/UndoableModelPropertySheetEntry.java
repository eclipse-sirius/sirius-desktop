/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.properties;

import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.ui.views.properties.PropertySheetEntry;

/**
 * An property sheet entry for elements in the model. The changes to the model
 * element property are done through a model command
 * 
 * See if this adaptation of this class if OK or KO
 * 
 * @see org.eclipse.gmf.runtime.emf.ui.properties.sections.UndoableModelPropertySheetEntry
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class UndoableModelPropertySheetEntry extends PropertySheetEntry {

    /**
     * The operation history used by this entry to execute property change
     * commands. <code>Null</code> if I am not the root entry. Only the root
     * entry keeps track of the history on behalf of all of the child entries.
     */
    private final IOperationHistory operationHistory;

    /**
     * Initializes me with an operation history through which property change
     * commands will be executed, undone and redone.
     * 
     * @param operationHistory
     *            my operation history
     */
    public UndoableModelPropertySheetEntry(final IOperationHistory operationHistory) {
        this.operationHistory = operationHistory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetPropertyValue() {
        // Do nothing.
    }

    /**
     * Gets my operation history.
     * 
     * @return my operation history
     */
    protected final IOperationHistory getOperationHistory() {
        return operationHistory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected org.eclipse.ui.views.properties.PropertySheetEntry createChildEntry() {
        return new UndoableModelPropertySheetEntry(getOperationHistory());
    }
}
