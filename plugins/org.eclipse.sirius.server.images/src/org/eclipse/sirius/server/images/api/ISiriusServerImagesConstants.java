/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.server.images.api;

/**
 * Utility interface holding some constants.
 * 
 * @author sbegaudeau
 */
public interface ISiriusServerImagesConstants {
    /**
     * The path of the images API.
     */
    String IMAGES_PATH = "/images"; //$NON-NLS-1$

    /**
     * The name of the parameter used to send the fragment of the EObject.
     */
    String FRAGMENT = "fragment"; //$NON-NLS-1$
}
