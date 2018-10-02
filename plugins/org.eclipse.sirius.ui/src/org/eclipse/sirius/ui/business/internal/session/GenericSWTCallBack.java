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
package org.eclipse.sirius.ui.business.internal.session;

import org.eclipse.sirius.ui.tools.api.command.AbstractSWTCallback;

/**
 * Implementation of the
 * {@link org.eclipse.sirius.tools.api.command.ui.UICallBack} interface using
 * SWT.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class GenericSWTCallBack extends AbstractSWTCallback {

    @Override
    protected String getVariableNameForRepresentation() {
        return "Representation"; //$NON-NLS-1$
    }

}
