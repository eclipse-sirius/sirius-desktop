/*******************************************************************************
 * Copyright (c) 2016 Obeo.
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
package org.eclipse.sirius.ui.properties.api;

import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.sirius.business.api.session.Session;

/**
 * Delegate the creation of the concrete {@link EditingContextAdapter} to use
 * for a given Sirius session.
 * 
 * @author pcdavid
 */
public interface IEditingContextAdapterProvider {
    /**
     * Creates and return the {@link EditingContextAdapter} to use for the
     * specified session.
     * 
     * @param session
     *            a Sirius session
     * @return the {@link EditingContextAdapter}, on <code>null</code> if this
     *         provider does not handle the given session.
     */
    EditingContextAdapter createEditingContextAdapter(Session session);
}
