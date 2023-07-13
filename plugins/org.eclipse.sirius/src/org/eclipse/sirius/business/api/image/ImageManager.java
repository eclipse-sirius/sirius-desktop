/*******************************************************************************
 * Copyright (c) 2021, 2023 THALES GLOBAL SERVICES.
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

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;

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
     * Timestamp format for image filenames generated.
     */
    String IMAGE_NAME_FORMAT = "yyyyMMdd_HHmmss_SSSSSS"; //$NON-NLS-1$

    /**
     * This class store the function that creates the file and its file name. Result of {@link #getCreateFileFunc}
     * 
     * @author scosta
     *
     */
    final class CreateImageFileProvider {

        private Supplier<Optional<CoreException>> createFileFunc;

        private String filename;

        /**
         * Default constructor.
         * 
         * @param createFileFunc
         *            The function to create file
         * @param filename
         *            The name of the file
         */
        public CreateImageFileProvider(Supplier<Optional<CoreException>> createFileFunc, String filename) {
            this.createFileFunc = createFileFunc;
            this.filename = filename;
        }

        public String getFileName() {
            return filename;
        }

        /**
         * Execute createFileFunc and throw error if any.
         * 
         * @throws CoreException
         *             Exception of createFileFunc
         */
        public void exec() throws CoreException {
            Optional<CoreException> error = createFileFunc.get();
            if (error.isPresent()) {
                throw error.get();
            }
        }

        /**
         * Convert this function as recording command. If any error, the error is logged in SiriusPlugin.
         * 
         * @param domain
         *            the transactional editing domain for the command
         * @return the command version of this function
         */
        public RecordingCommand asRecordingCommand(TransactionalEditingDomain domain) {
            return new RecordingCommand(domain) {
                @Override
                protected void doExecute() {
                    try {
                        exec();
                    } catch (CoreException error) {
                        SiriusPlugin.getDefault().error(MessageFormat.format(Messages.ImageManager_imageCreationFailure, filename), error);
                    }
                }
            };
        }
    }
    
    /**
     * Generate file name for image using timestamp.
     * 
     * @param extension
     *            The extension of the image file without the dot character
     * @return A file name for image
     */
    static String generateName(String extension) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(IMAGE_NAME_FORMAT).withZone(ZoneId.systemDefault());
        String strDate = formatter.format(Instant.now());
        return strDate + '.' + extension;
    }

    /**
     * Returns function that create and serialize a file from a Base64 encoding image and the path that will be used to
     * get this file.
     * 
     * @param contextObject
     *            eObject used as context. According to the context the image file may be created at a different
     *            location(for example in the workspace or in a database).
     * @param simpleImageName
     *            the non qualified name of the image
     * @param base64
     *            the base64 encoding
     * @return the function to create file and the path used to reach the created file.
     */
    CreateImageFileProvider getCreateFileFunc(EObject contextObject, String simpleImageName, String base64) throws IOException, CoreException;

    /**
     * Return function that create an image file from an input stream and it the path that will be used to get this
     * file.
     * 
     * @param contextObject
     *            eObject used as context. According to the context the image file may be created at a different
     *            location (fore example in the workspace or in a database).
     * @param simpleImageName
     *            the non qualified name of the image.
     * @param stream
     *            the stream of the image data.
     * @return the function to create file and the path used to reach the created file.
     */
    CreateImageFileProvider getCreateFileFunc(EObject contextObject, String simpleImageName, InputStream stream) throws CoreException, IOException;

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
     * @throws Exception
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
