/*******************************************************************************
 * Copyright (c) 2021, 2022 THALES GLOBAL SERVICES and others.
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

import java.io.File;
import java.util.Optional;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.TreeImagesGalleryComposite;

/**
 * An extended {@link ITreeContentProvider} interface to retrieve image information that can be used by
 * {@link TreeImagesGalleryComposite}.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
public interface ITreeImagesContentProvider extends ITreeContentProvider {

    /**
     * Returns the image file associated to an element.
     * 
     * @param element
     *            the element
     * @return an optional image file
     */
    Optional<File> getImageFile(Object element);

    /**
     * Returns the path of the image file associated to an element.
     * 
     * @param element
     *            the element
     * @return an optional path of the image as a String
     */
    Optional<String> getPath(Object element);

    /**
     * Checks if the element is an image.
     * 
     * @param element
     *            the element
     * @return true if the element is an image.
     */
    boolean isImage(Object element);
}
