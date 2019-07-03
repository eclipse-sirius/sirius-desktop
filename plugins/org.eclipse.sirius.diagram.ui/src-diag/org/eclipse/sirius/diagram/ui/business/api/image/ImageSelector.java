/*******************************************************************************
 * Copyright (c) 2012, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.api.image;

import org.eclipse.sirius.viewpoint.BasicLabelStyle;

/**
 * A interface use to select a image from a image source (workspace, CDO repo, ...).
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public interface ImageSelector {

    /**
     * The name of the CDOResource containing shared images at the top level of
     * the repo.
     */
    @Deprecated
    String IMAGES_RESOURCE_NAME = "images"; //$NON-NLS-1$

    /**
     * Get a image.
     * 
     * @param basicLabelStyle
     *            the {@link BasicLabelStyle} to use
     * @return the image path selected
     */
    String selectImage(BasicLabelStyle basicLabelStyle);

}
