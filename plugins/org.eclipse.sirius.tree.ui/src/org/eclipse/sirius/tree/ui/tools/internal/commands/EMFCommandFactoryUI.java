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
package org.eclipse.sirius.tree.ui.tools.internal.commands;

import org.eclipse.sirius.tree.tools.api.interpreter.IInterpreterSiriusTreeVariables;
import org.eclipse.sirius.ui.tools.api.command.AbstractSWTCallback;

/**
 * Implementation of the
 * {@link org.eclipse.sirius.tools.api.command.ui.UICallBack} interface using
 * SWT.
 * 
 * @author nlepine
 */
public class EMFCommandFactoryUI extends AbstractSWTCallback {
    /**
     * {@inheritDoc}
     */
    @Override
    protected String getVariableNameForRepresentation() {
        return IInterpreterSiriusTreeVariables.TREE;
    }
}
