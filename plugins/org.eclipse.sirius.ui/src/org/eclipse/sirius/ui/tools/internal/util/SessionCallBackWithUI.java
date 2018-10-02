/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.tools.internal.util;

import org.eclipse.sirius.ui.tools.api.command.AbstractSWTCallback;

/**
 * CallBack without representation variable name, used to handle synchronization
 * from the session.
 * 
 * @author mporhel
 * 
 */
public class SessionCallBackWithUI extends AbstractSWTCallback {

    @Override
    protected String getVariableNameForRepresentation() {
        return ""; //$NON-NLS-1$
    }
}
