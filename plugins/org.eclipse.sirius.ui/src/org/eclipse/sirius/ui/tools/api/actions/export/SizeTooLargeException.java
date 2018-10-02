/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.tools.api.actions.export;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;

/**
 * To know if exception is due of export image to large.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class SizeTooLargeException extends CoreException {

    /**
     * All serializable objects should have a stable serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Status exception.
     */
    private final IStatus status;

    /**
     * Default constructor.
     * 
     * @param status
     *            status
     */
    public SizeTooLargeException(IStatus status) {
        super(status);
        this.status = status;
    }

    /**
     * Return the representations not exported because was too large.
     * 
     * @return the representations name not exported.
     */
    public String getReprensentationNameNotExported() {
        return status.getMessage();
    }

}
