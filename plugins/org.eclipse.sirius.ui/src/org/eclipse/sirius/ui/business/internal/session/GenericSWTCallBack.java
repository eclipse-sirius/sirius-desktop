/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
