/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.image;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Base64;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.image.ImageManager;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * Implementation of {@link ImageManager} used for local project.
 * 
 * @author lfasani
 *
 */
public class ImageManagerForWorkspaceResource implements ImageManager {
    /**
     * BASE64.
     */
    protected static final String BASE64 = "base64"; //$NON-NLS-1$

    /**
     * SLASH.
     */
    protected static final CharSequence SLASH = "/"; //$NON-NLS-1$

    /**
     * QUOTE.
     */
    private static final CharSequence QUOTE = "\""; //$NON-NLS-1$

    /**
     * Map use to keep the link between the original image that is serialized and the path used as html.
     */
    protected Map<String, String> htmlToOriginalImagePath = new LinkedHashMap<>();

    @Override
    public Map<Object, String> createFile(EObject contextObject, String simpleImageName, String base64) {
        Map<Object, String> createdFile = new Hashtable<>();

        // Create an image file in the images folder of the project with the extension found in the
        // Base64 encoding string
        String platformString = contextObject.eResource().getURI().toPlatformString(true);

        IFile airdFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformString));

        IFolder imageFolder = airdFile.getProject().getFolder(ImageManager.IMAGE_FOLDER_NAME);
        String wsImageName = ImageManager.IMAGE_FOLDER_NAME + SLASH + simpleImageName;
        if (!imageFolder.exists()) {
            try {
                imageFolder.create(true, true, null);
            } catch (CoreException e) {
                SiriusPlugin.getDefault().error(MessageFormat.format(Messages.UpdateBase64ImageEncodingPreCommitListener_imageCreationFailure, wsImageName), e);
            }
        }
        IFile imageFile = airdFile.getProject().getFile(wsImageName);

        if (createIFile(base64, imageFile)) {
            String imageFullPath = imageFile.getFullPath().toString().replaceFirst("^" + SLASH, ""); //$NON-NLS-1$//$NON-NLS-2$
            createdFile.put(base64, imageFullPath);
        }
        return createdFile;
    }

    private boolean createIFile(String base64, IFile imageFile) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        try (ByteArrayInputStream input = new ByteArrayInputStream(decodedBytes)) {
            imageFile.create(input, true, null);
        } catch (IOException | CoreException e) {
            SiriusPlugin.getDefault().error(MessageFormat.format(Messages.UpdateBase64ImageEncodingPreCommitListener_imageCreationFailure, imageFile.getFullPath()), e);
            return false;
        }
        return true;
    }

    @Override
    public void undoCreatedFiles(Session session, Map<Object, String> createdFiles) {
        for (String createFileName : createdFiles.values()) {
            IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(createFileName));
            if (file != null) {
                try {
                    file.delete(true, null);
                } catch (CoreException e) {
                    SiriusPlugin.getDefault().error(MessageFormat.format(Messages.UpdateBase64ImageEncodingPreCommitListener_imageCreationFailure, file.getFullPath()), e);
                }
            }
        }
    }

    @Override
    public void redoCreateFiles(Session session, Map<Object, String> createdFiles) {
        for (Object object : createdFiles.keySet()) {
            if (object instanceof String) {
                String base64String = (String) object;
                String createFileName = createdFiles.get(base64String);

                IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(createFileName));
                createIFile(base64String, file);
            }
        }
    }

    @Override
    public String computeAndConvertPathsToHtmlFromOriginal(EObject contextObject, String textWithOriginalImagePaths) {
        String returnedString = textWithOriginalImagePaths;
        Pattern pattern = Pattern.compile(ImageManager.HTML_IMAGE_PATH_PATTERN);
        Matcher matcher = pattern.matcher(textWithOriginalImagePaths);

        while (matcher.find()) {
            String originalPath = matcher.group(1);
            // The path is made relative to the current project
            String workspaceRelativePath = "../" + originalPath; //$NON-NLS-1$
            htmlToOriginalImagePath.put(workspaceRelativePath, matcher.group(1));
            returnedString = replaceString(returnedString, originalPath, workspaceRelativePath);
        }

        return returnedString;
    }

    /**
     * Replace a string included in quotes. Adding QUOTE ensures that the path will be replaced as a whole and not only
     * a part of the path.
     * 
     * @param string
     *            the string to modify
     * @param oldPart
     *            part to be replaced
     * @param newPart
     *            part to replace
     * @return the modified string
     */
    protected String replaceString(String string, String oldPart, String newPart) {
        return string.replace(QUOTE + oldPart + QUOTE, QUOTE + newPart + QUOTE);
    }

    @Override
    public String convertToOriginalPathFromPathUsedForHtml(EObject contextObject, String textWithPathForHtml) {
        String returnedString = textWithPathForHtml;
        Pattern pattern = Pattern.compile(ImageManager.HTML_IMAGE_PATH_PATTERN);
        Matcher matcher = pattern.matcher(textWithPathForHtml);

        while (matcher.find()) {
            String strToReplace = matcher.group(1);
            String originalPath = htmlToOriginalImagePath.get(strToReplace);
            if (originalPath != null) {
                returnedString = replaceString(returnedString, strToReplace, originalPath);
            } else {
                if (!strToReplace.contains(BASE64)) {
                    SiriusPlugin.getDefault().getLog()
                            .error(MessageFormat.format(Messages.ImageManagerForWorkspaceResource_errorGettingTheOriginalPath, strToReplace, new EObjectQuery(contextObject).getGenericDecription()));
                }
            }
        }
        return returnedString;
    }

}
