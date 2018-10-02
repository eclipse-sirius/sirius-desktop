/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.diagram.ui.tools.internal.actions.pinning.UnpinElementsEclipseAction;
import org.eclipse.sirius.ui.tools.internal.commands.AbstractActionWrapperHandler;

/**
 * The default handler for the "un pin Elements" command.
 * 
 * @author pcdavid
 */
public class UnpinElementsHandler extends AbstractActionWrapperHandler {

    /**
     * Constructor.
     */
    public UnpinElementsHandler() {
        super(new UnpinElementsEclipseAction());
    }
}
