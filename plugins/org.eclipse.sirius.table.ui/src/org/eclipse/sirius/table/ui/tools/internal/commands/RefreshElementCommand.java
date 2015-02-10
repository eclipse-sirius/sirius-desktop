/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.commands;

import org.eclipse.sirius.table.ui.tools.internal.editor.action.RefreshAction;
import org.eclipse.sirius.ui.tools.internal.commands.AbstractActionWrapperHandler;

/**
 * A {@link AbstractActionWrapperHandler} to launch refresh.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class RefreshElementCommand extends AbstractActionWrapperHandler {

    /**
     * Construct a new instance.
     */
    public RefreshElementCommand() {
        super(new RefreshAction(null));
    }

}
