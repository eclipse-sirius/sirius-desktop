/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.commands.emf;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.ui.tools.api.command.AbstractSWTCallback;

/**
 * Implementation of the
 * {@link org.eclipse.sirius.tools.api.command.ui.UICallBack} interface using
 * SWT.
 * 
 * @author cbrun
 * 
 */
public class EMFCommandFactoryUI extends AbstractSWTCallback {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.api.command.AbstractSWTCallback#getVariableNameForRepresentation()
     */
    @Override
    protected String getVariableNameForRepresentation() {
        return IInterpreterSiriusVariables.DIAGRAM;
    }

}
