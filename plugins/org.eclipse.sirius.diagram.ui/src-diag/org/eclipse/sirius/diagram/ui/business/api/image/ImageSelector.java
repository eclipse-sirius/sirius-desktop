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
package org.eclipse.sirius.diagram.ui.business.api.image;

import org.eclipse.sirius.viewpoint.BasicLabelStyle;

/**
 * A interface use to select a image from a image source (workspace, CDO repo,
 * ...).
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public interface ImageSelector {

    /**
     * The name of the CDOResource containing shared images at the top level of
     * the repo.
     */
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
