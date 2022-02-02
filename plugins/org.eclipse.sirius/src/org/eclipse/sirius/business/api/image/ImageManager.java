/*******************************************************************************
 * Copyright (c) 2021, 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.image;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;

/**
 * This API is used to manage the image used by Sirius.<br/>
 * According to the application using Sirius, the serialization of an image or the image cache management may be
 * different. It is then possible to override the implementation with {@link ImageManagerProvider}.
 * 
 * @author lfasani
 */
public interface ImageManager {
    /**
     * Name of the folder used to create images from base64 string.
     */
    String IMAGE_FOLDER_NAME = "images"; //$NON-NLS-1$

    /**
     * Pattern used to get the image path.
     */
    String HTML_IMAGE_PATH_PATTERN = "<img.*?src=\"(.*?)\".*?/>"; //$NON-NLS-1$

    /**
     * Create and serialize a file from a Base64 encoding image. It returns the path that will be used to get this file.
     * 
     * @param contextObject
     *            eObject used as context. According to the context the image file may be created at a different
     *            location(for example in the workspace or in a database).
     * @param simpleImageName
     *            the non qualified name of the image
     * @param base64
     *            the base64 encoding
     * @return the path used to reach the created file.
     */
    String createFile(EObject contextObject, String simpleImageName, String base64);

    /**
     * Undo the creation of the file done with createFile(String ) method.<br/>
     * 
     * @param session
     *            The session is used as context. According to the context the image file may be at a different
     *            location(for example in the workspace or in a database).
     * @param createdFiles
     *            the base64 string associated to the path to the file that must be deleted.
     */
    void undoCreatedFiles(Session session, Map<String, String> createdFiles);

    /**
     * Redo the creation of the file done the first time with createFile(String ) method.<br/>
     * 
     * @param session
     *            The session is used as context. According to the context the image file may be created at a different
     *            location(for example in the workspace or in a database).
     * @param createdFiles
     *            the base64 string associated to the path to the file that must be recreated.
     */
    void redoCreateFiles(Session session, Map<String, String> createdFiles);

    /**
     * This method transforms the serialized text to a text that will be used for html page. This method only changes
     * the image paths.<br/>
     * It also ensures that the image is available at the modified location.
     * 
     * @param contextObject
     *            eObject used as context. According to the context the path the image will have a specific location.
     * @param textWithOriginalImagePaths
     *            the text containing the original paths
     * @return the text containing the paths used for html
     */
    String computeAndConvertPathsToHtmlFromOriginal(EObject contextObject, String textWithOriginalImagePaths);

    /**
     * This method transforms the text used for html page to the text that will be serialized. This method only change
     * the image paths.
     * 
     * @param contextObject
     *            eObject used as context. According to the context the path the image will have a specific location.
     * @param textWithPathForHtml
     *            the text containing the paths used for html
     * @return the text containing the original paths
     */
    String convertToOriginalPathFromPathUsedForHtml(EObject contextObject, String textWithPathForHtml);

}
