/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.resource;

import java.io.File;

import org.eclipse.core.runtime.IPath;

/**
 * A File getter.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public interface IFileGetter {

    /**
     * Get a {@link File} associated to the specified {@link IPath} if this
     * {@link IFileGetter} support it.
     * 
     * @param path
     *            the specified {@link IPath}
     * @return the {@link File} associated to the specified {@link IPath}, if
     *         supported, null else
     */
    File getFile(IPath path);
}
