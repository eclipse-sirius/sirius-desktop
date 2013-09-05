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
package org.eclipse.sirius.business.api.session;

import org.eclipse.core.runtime.CoreException;

/**
 * A common interface for session creation operations.
 * 
 * @author mchauvin
 */
public interface SessionCreationOperation {

    /**
     * Execute the creation operation.
     * 
     * @throws CoreException
     *             In case of session resource creation failed
     */
    void execute() throws CoreException;

    /**
     * Get the created session.
     * 
     * @return the created session, <code>null</code> otherwise
     */
    Session getCreatedSession();
}
