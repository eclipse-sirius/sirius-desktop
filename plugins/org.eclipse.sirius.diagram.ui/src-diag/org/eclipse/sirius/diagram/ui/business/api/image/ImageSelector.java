/*******************************************************************************
 * Copyright (c) 2012, 2024 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * A interface use to select a image from a image source (workspace, CDO repo, ...).
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public interface ImageSelector {

    /**
     * Used to know how many images can be selected.
     * 
     * @author lfasani
     */
    enum SelectionMode {
        /**
         * Used to select a single image.
         */
        MONO_SELECTION,
        /**
         * Used to select multiple images.
         */
        MULTI_SELECTION
    };

    /**
     * Get the selected image paths.
     * 
     * @param eObject
     *            the {@link EObject} used to associate the image with
     * @param selectionMode
     *            the selectionMode
     * @param currentSelection
     *            the current image path.
     * @param displayImagePaths
     *            if true, the dialog will display the current and new selected image path and a message if the image is
     *            not found.
     * @return a non null list of the selected image paths
     */
    List<String> selectImages(EObject eObject, SelectionMode selectionMode, String currentSelection, boolean displayImagePaths);

    /**
     * Get the selected image paths.
     * 
     * @param eObject
     *            the {@link EObject} used to associate the image with
     * @param selectionMode
     *            the selectionMode
     * @param currentSelection
     *            the current image path. If not null, the dialog will display the current and new selected image path
     *            and a message if the image is not found.
     * @return a non null list of the selected image paths
     */
    default List<String> selectImages(EObject eObject, SelectionMode selectionMode, String currentSelection) {
        return selectImages(eObject, selectionMode, currentSelection, true);
    }

    /**
     * Get the selected image paths.
     * 
     * @param eObject
     *            the {@link EObject} used to associate the image with
     * @param selectionMode
     *            the selectionMode
     * @return a non null list of the selected image paths
     */
    default List<String> selectImages(EObject eObject, SelectionMode selectionMode) {
        return selectImages(eObject, selectionMode, null);
    }

}
