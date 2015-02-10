/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.commands;

import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromDiagramAction;
import org.eclipse.sirius.ui.tools.internal.commands.AbstractActionWrapperHandler;

/**
 * Handles the delete from diagram action (Ctrl + Shift + D).
 * 
 * @author fbarbin
 */
public class DeleteFromDiagramHandler extends AbstractActionWrapperHandler {
    /**
     * Construct a new instance.
     */
    public DeleteFromDiagramHandler() {
        super(new DeleteFromDiagramAction());
    }

}
