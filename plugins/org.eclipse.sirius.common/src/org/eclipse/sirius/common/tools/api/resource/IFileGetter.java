/*******************************************************************************
 * Copyright (c) 2012, 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.common.tools.api.resource;

import java.io.File;
import java.util.Optional;

import org.eclipse.core.runtime.IPath;

/**
 * A File getter.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public interface IFileGetter {

    /**
     * Get a {@link File} associated to the specified {@link IPath} if this {@link IFileGetter} support it.
     * 
     * @param path
     *            the specified {@link IPath}
     * @return the {@link File} associated to the specified {@link IPath}, if supported, null else
     */
    File getFile(IPath path);

    /**
     * Indicate if the given full path corresponds to an existing image.<br/>
     * 
     * @param fullPath
     *            is the full path of the file
     * @return an empty optional if the implementation can not manage this case, otherwise it returns a Boolean value
     *         depending if the file exists or not.
     */
    Optional<Boolean> exists(IPath fullPath);
}
