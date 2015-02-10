/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.commands;

import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.RevealAllElementsAction;
import org.eclipse.sirius.ui.tools.internal.commands.AbstractActionWrapperHandler;

/**
 * Command to wrap reveal all elements action.
 * 
 * @author mchauvin
 */
public class RevealAllElementsCommand extends AbstractActionWrapperHandler {

    /**
     * Construct a new instance.
     */
    public RevealAllElementsCommand() {
        super(new RevealAllElementsAction());
    }

}
