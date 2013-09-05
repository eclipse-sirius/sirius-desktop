/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.resource;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;

/**
 * Interface for file modification validation.
 * 
 * @author smonnier
 */
public interface IFileModificationValidator {

    /**
     * Validates that the given files can be modified
     * {@link org.eclipse.core.resources.IFileModificationValidator}.
     * 
     * @param files2Validate
     *            the files that are to be modified; these files must all exist
     *            in the workspace
     * @return a status object that is OK if things are fine, otherwise a status
     *         describing reasons why modifying the given files is not
     *         reasonable
     */
    IStatus validateEdit(Collection<IFile> files2Validate);

}
