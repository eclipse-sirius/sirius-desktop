/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
package org.eclipse.sirius.tree.ui.tools.internal.commands;

import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.RefreshAction;
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
