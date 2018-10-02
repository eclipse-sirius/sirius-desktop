/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
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
