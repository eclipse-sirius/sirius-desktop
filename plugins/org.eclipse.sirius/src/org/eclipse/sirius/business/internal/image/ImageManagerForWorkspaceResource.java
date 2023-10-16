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
package org.eclipse.sirius.business.internal.image;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
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
     * HTTP protocol.
     */
    protected static final String HTTP = "http"; //$NON-NLS-1$

    /**
     * HTTPS protocol.
     */
    protected static final String HTTPS = "https"; //$NON-NLS-1$

    /**
     * QUOTE.
     */
    private static final CharSequence QUOTE = "\""; //$NON-NLS-1$

    /**
     * Map use to keep the link between the original image that is serialized and the path used as html.
     */
    protected Map<String, String> htmlToOriginalImagePath = new LinkedHashMap<>();

    @Override
    public CreateImageFileProvider getCreateFileFunc(EObject contextObject, String simpleImageName, String base64) throws CoreException, IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        try (var dataStream = new ByteArrayInputStream(decodedBytes)) {
            return getCreateFileFunc(contextObject, simpleImageName, dataStream);
        }
    }

    @Override
    public CreateImageFileProvider getCreateFileFunc(EObject contextObject, String simpleImageName, InputStream data) throws CoreException, IOException {
        // Create an image file in the images folder of the project
        String platformString = contextObject.eResource().getURI().toPlatformString(true);
        IFile airdFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformString));
        String imagesFolderName = getImagesFolderName(airdFile);
        IFolder imageFolder = airdFile.getProject().getFolder(imagesFolderName);

        if (!imageFolder.exists()) {
            imageFolder.create(true, true, null);
        }

        IFile imageFile = imageFolder.getFile(simpleImageName);
        String filename = imageFile.getFullPath().toString().replaceFirst("^" + SLASH, ""); //$NON-NLS-1$//$NON-NLS-2$

        // in the lambda we return exception because we can throw it
        Supplier<Optional<CoreException>> createFileFunction = () -> {
            try {
                imageFile.create(data, true, null);
                return Optional.empty();
            } catch (CoreException e) {
                return Optional.of(e);
            }
        };
        // we return function because we need to execute image creation in command while keeping filename.
        return new CreateImageFileProvider(createFileFunction, filename);
    }

    /**
     * Get the name of the existing images folder name if it exists; {@link #IMAGE_FOLDER_NAME} otherwise.
     * 
     * @param airdFile
     *            the file used to retrieve an existing images folder in the project
     * @return the name of the existing images folder name if it exists; {@link #IMAGE_FOLDER_NAME} otherwise.
     */
    private String getImagesFolderName(IResource airdFile) {
        String imageFolderName = ImageManager.IMAGE_FOLDER_NAME;
        IProject project = airdFile.getProject();
        Optional<IFolder> optImageFolder;
        try {
            optImageFolder = Arrays.stream(project.members()) //
                    .filter(IFolder.class::isInstance) //
                    .map(IFolder.class::cast) //
                    .filter(folder -> ImageManager.IMAGE_FOLDER_NAME.equals(folder.getName().toLowerCase())) //
                    .findFirst();
        } catch (CoreException e) {
            optImageFolder = Optional.empty();
            SiriusPlugin.getDefault().error(MessageFormat.format(Messages.ImageManager_projectMembersFailure, project.getName()), e);
        }
        if (optImageFolder.isEmpty()) {
            imageFolderName = ImageManager.IMAGE_FOLDER_NAME;
        } else {
            imageFolderName = optImageFolder.get().getName();
        }
        return imageFolderName;
    }

    @Override
    public void undoCreatedFiles(Session session, Map<String, String> createdFiles) {
        for (String createFileName : createdFiles.values()) {
            IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(createFileName));
            if (file != null) {
                try {
                    file.delete(true, null);
                } catch (CoreException e) {
                    SiriusPlugin.getDefault().error(MessageFormat.format(Messages.ImageManager_imageCreationFailure, file.getFullPath()), e);
                }
            }
        }
    }

    @Override
    public void redoCreateFiles(Session session, Map<String, String> createdFiles) {
        for (Object object : createdFiles.keySet()) {
            if (object instanceof String) {
                String base64String = (String) object;
                String createFileName = createdFiles.get(base64String);

                IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(createFileName));

                byte[] decodedBytes = Base64.getDecoder().decode(base64String);
                try (var dataStream = new ByteArrayInputStream(decodedBytes)) {
                    file.create(dataStream, true, null);
                } catch (CoreException | IOException e) {
                    SiriusPlugin.getDefault().error(MessageFormat.format(Messages.ImageManager_imageCreationFailure, file.getFullPath()), e);
                }
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
            if (!originalPath.startsWith(HTTP) && !originalPath.startsWith(HTTPS)) {
                IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
                Resource eResource = contextObject.eResource();
                if (eResource != null) {
                    IResource member = workspaceRoot.findMember(originalPath);
                    if (member != null && member.getProject() != null) {
                        String computedImagePath;
                        // It should be made relative to the current project when the image is in the same project
                        // than the EObject but making it absolute allows to fix an issue in Capella and display
                        // correctly the image in the rich text editor when embedded in a dialog box
                        computedImagePath = member.getLocation().toString();

                        htmlToOriginalImagePath.put(computedImagePath, matcher.group(1));
                        returnedString = replaceString(returnedString, originalPath, computedImagePath);
                    }
                }
            }
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
